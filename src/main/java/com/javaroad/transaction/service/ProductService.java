package com.javaroad.transaction.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.javaroad.transaction.domain.Order;
import com.javaroad.transaction.domain.Product;
import com.javaroad.transaction.dto.ProductInfoDto;
import com.javaroad.transaction.mapper.OrderMapper;
import com.javaroad.transaction.mapper.ProductMapper;

import javax.annotation.Resource;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-25 15:03
 * @version: 1.0
 */
@Service
public class ProductService {
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private ProductMapper productMapper;

    @Resource
    private ProductService productService;
    private Lock lock = new ReentrantLock(true);

    /**
     * 错误的用法。锁释放后，事务才提交。导致超卖。
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public void createOrderWrongOne(Integer productId)  {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"；抢到锁了，进入方法");
            Product product = productMapper.selectById(productId);
            Integer productCount = product.getProductCount();
            System.out.println(Thread.currentThread().getName()+":当前库存="+productCount);
            if(productCount>0){
                Product updateProduct = new Product();
                updateProduct.setId(product.getId());
                updateProduct.setProductCount(productCount-1);
                productMapper.updateById(updateProduct);
                Order  order = new Order();
                order.setProductId(product.getId());
                order.setBuyerGoods(product.getProductName());
                order.setBuyerName(Thread.currentThread().getName());
                orderMapper.insert(order);
                System.out.println(Thread.currentThread().getName()+":减库存，创建订单完毕！");
            }else {
                System.out.println(Thread.currentThread().getName()+"；没库存了");
            }
            System.out.println(Thread.currentThread().getName()+"；释放锁");
        }finally {
            lock.unlock();
        }
    }
    /**
     * 1被@Transactional标注的方法必须是public
     * 原因是：@see org.springframework.com.javaroad.transaction.interceptor.AbstractFallbackTransactionAttributeSource#computeTransactionAttribute(Method, Class)
     * 2 默认只会对RuntimeException的子类和Error会回滚。{@link org.springframework.transaction.interceptor.DefaultTransactionAttribute#rollbackOn(Throwable)}
     *
     */
    @Transactional(rollbackFor = RuntimeException.class)
    public void doCreateOrder(Integer productId){
        System.out.println(Thread.currentThread().getName()+"；抢到锁了，进入方法");
        Product product = productMapper.selectById(1);
        Integer productCount = product.getProductCount();
        System.out.println(Thread.currentThread().getName()+":当前库存="+productCount);
        if(productCount>0){
            Product updateProduct = new Product();
            updateProduct.setId(product.getId());
            updateProduct.setProductCount(productCount-1);
            productMapper.updateById(updateProduct);
            int i = 1/0;//主动产生异常。事务没回滚
            Order  order = new Order();
            order.setProductId(product.getId());
            order.setBuyerGoods(product.getProductName());
            order.setBuyerName(Thread.currentThread().getName());
            orderMapper.insert(order);
            System.out.println(Thread.currentThread().getName()+":减库存，创建订单完毕！");
        }else {
            System.out.println(Thread.currentThread().getName()+"；没库存了");
        }
        System.out.println(Thread.currentThread().getName()+"；释放锁");
    }


    /**
     * 提交事务后解锁，但是事务是失效的。失效原因：事务方法被当前类之外调用时，才会由spring生成的代理对象来管理
     */
    public void createOrderWrongTwo(Integer productId)  {
        lock.lock();
        try {
            doCreateOrder(productId);
        }finally {
            lock.unlock();
        }
    }

    /**
     * 提交事务后解锁。事务方法被当前类之外调用时，才会由spring生成的代理对象来管理.
     * 这个写法是正确的，但是比较丑陋，一般都是项目结构设计不合理才产生。
     */
    public void createOrderRightOne(Integer productId)  {
        lock.lock();
        try {
            productService.doCreateOrder(productId);
        }finally {
            lock.unlock();
        }
    }

    /**
     * 抛出RuntimeException，会回滚吗？会！！！ rollbackFor优先级更高
     * {@link  org.springframework.transaction.annotation.SpringTransactionAnnotationParser#parseTransactionAnnotation(Transactional ann)}
     * @see org.springframework.transaction.interceptor.RuleBasedTransactionAttribute#rollbackOn(java.lang.Throwable)
     */
    @Transactional(rollbackFor = RuntimeException.class,noRollbackFor = RuntimeException.class)
    public void sellProductBizPriority(Integer productId){
        System.out.println(Thread.currentThread().getName()+"；抢到锁了，进入方法");
        Product product = productMapper.selectById(productId);
        Integer productCount = product.getProductCount();
        System.out.println(Thread.currentThread().getName()+":当前库存="+productCount);
        if(productCount>0){
            Product updateProduct = new Product();
            updateProduct.setId(product.getId());
            updateProduct.setProductCount(productCount-1);
            productMapper.updateById(updateProduct);
            int i = 1/0;//主动产生异常。事务没回滚
            Order  order = new Order();
            order.setProductId(product.getId());
            order.setBuyerGoods(product.getProductName());
            order.setBuyerName(Thread.currentThread().getName());
            orderMapper.insert(order);
            System.out.println(Thread.currentThread().getName()+":减库存，创建订单完毕！");
        }else {
            System.out.println(Thread.currentThread().getName()+"；没库存了");
        }
        System.out.println(Thread.currentThread().getName()+"；释放锁");
    }

    public void createOrderRightTwo(Integer productId) {
        lock.lock();
        try {
            productService.sellProductBizPriority(productId);
        }finally {
            lock.unlock();
        }
    }

    /**
     * 事务嵌套调用。catch了异常也会回滚
     */
    @Transactional
    public void createOrderNestOne(Integer productId){
        try {
            productService.doCreateOrderNestOne(productId);//由于这里回滚了，事务的全局范围设置有异常发生。
        } catch (Exception exception){
            System.out.println("异常啦:"+exception.getMessage());
        }
        Product product = productMapper.selectById(productId);
        Integer productCount = product.getProductCount();
        Product updateProduct = new Product();
        updateProduct.setId(product.getId());
        updateProduct.setProductCount(productCount-1);
        productMapper.updateById(updateProduct);
        //提交的時候product的update操作也會回滚的。Transaction rolled back because it has been marked as rollback-only
    }
    @Transactional
    public  void doCreateOrderNestOne(Integer productId){
        //这样抛出异常，会导致order和product一起回滚
        Order  order = new Order();
        order.setProductId(productId);
        order.setBuyerGoods("哈嘍");
        order.setBuyerName(Thread.currentThread().getName());
        orderMapper.insert(order);
        int i = 1/0;//主动产生异常
    }


    /**
     * 事务嵌套调用。新开一个事务，product不回滚，order回滚
     * @param productId
     */
    @Transactional
    public void createOrderNestTwo(Integer productId){
        Product product = productMapper.selectById(productId);
        Integer productCount = product.getProductCount();
        Product updateProduct = new Product();
        updateProduct.setId(product.getId());
        updateProduct.setProductCount(productCount-1);
        productMapper.updateById(updateProduct);
        try {
            productService.doCreateOrderNestTwo(productId);
        }catch (Exception exception){
            System.out.println("异常啦:"+exception.getMessage());
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW) //开一个新事务。order会回滚。product不回滚
    public  void doCreateOrderNestTwo(Integer productId) {
        //这样抛出异常，会导致createOrderNestTwo也回滚
        Order order = new Order();
        order.setProductId(productId);
        order.setBuyerGoods("哈嘍");
        order.setBuyerName(Thread.currentThread().getName());
        orderMapper.insert(order);
        int i = 1 / 0;//主动产生异常
    }

    /**
     * 事务嵌套调用。子方法内部将异常try catch了，所以子方法没发生回滚。product不回滚，order也不回滚
     * @param productId
     */
    @Transactional
    public void createOrderNestThree(Integer productId){
        Product product = productMapper.selectById(productId);
        Integer productCount = product.getProductCount();
        Product updateProduct = new Product();
        updateProduct.setId(product.getId());
        updateProduct.setProductCount(productCount-1);
        productMapper.updateById(updateProduct);
        productService.doCreateOrderNestThree(productId);

    }
    @Transactional
    public  void doCreateOrderNestThree(Integer productId) {
        //把异常抓住，不抛出，则order和product都不回滚
        try {
            Order  order = new Order();
            order.setProductId(productId);
            order.setBuyerGoods("哈嘍");
            order.setBuyerName(Thread.currentThread().getName());
            orderMapper.insert(order);
            int i = 1/0;//主动产生异常
        } catch (RuntimeException ex){
            System.out.println(ex);
        }

    }
    public ProductInfoDto getInfo(Integer productId) {
        ProductInfoDto productInfo = new ProductInfoDto();
        productInfo.setProductId(productId);
        productInfo.setProductCount(productMapper.selectById(productId).getProductCount());
        productInfo.setOrderCount(orderMapper.selectCount(Wrappers.<Order>lambdaQuery()
                .eq(Order::getProductId, productId)));
        return productInfo;
    }


    /**
     * 事务嵌套调用。子方法抛出异常，主方法捕获异常。product回滚，order也回滚
     * @param productId
     */
    @Transactional
    public void createOrderNestFour(Integer productId){
        Product product = productMapper.selectById(productId);
        Integer productCount = product.getProductCount();
        Product updateProduct = new Product();
        updateProduct.setId(product.getId());
        updateProduct.setProductCount(productCount-1);
        productMapper.updateById(updateProduct);
        productService.doCreateOrderNestFour(productId);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW) //开一个新事务。order会回滚。product不回滚
    public  void doCreateOrderNestFour(Integer productId) {
        //这样抛出异常，会导致createOrderNestTwo也回滚
        Order order = new Order();
        order.setProductId(productId);
        order.setBuyerGoods("哈嘍");
        order.setBuyerName(Thread.currentThread().getName());
        orderMapper.insert(order);
        int i = 1 / 0;//主动产生异常
    }
}
