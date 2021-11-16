package com.javaroad.transaction.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.javaroad.transaction.common.Results;
import com.javaroad.transaction.dto.ProductInfoDto;
import com.javaroad.transaction.service.ProductService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/product")
//@Slf4j
/**
 * 要进入事务，就会进入下面的方法
 * @see org.springframework.transaction.interceptor.TransactionAspectSupport#invokeWithinTransaction
 * https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/3/19/170f0e025a51a1b4~tplv-t2oaga2asx-watermark.awebp
 */
public class ProductController {
    @Resource
    private ProductService productService;

    /**
     * 看商品的详情：产品数量，下订单数量
     * @param productId
     * @return
     */
    @PostMapping("/info")
    public Results<ProductInfoDto> info(@RequestParam Integer productId)  {
       return Results.success(productService.getInfo(productId));
    }

    /**
     * 对产品下订单,发生超卖
     * @param productId
     */
    @PostMapping("/order/wrong/one/create")
    public Results<Boolean> createOrderWrongOne(@RequestParam Integer productId)  {
        productService.createOrderWrongOne(productId);
       return  Results.success();
    }

    /**
     * 事务不生效，不超卖
     */
    @PostMapping("/order/wrong/two/create")
    public Results<Boolean>  createOrderWrongTwo(@RequestParam Integer productId)  {
        productService.createOrderWrongTwo(productId);
        return  Results.success();
    }
    /**
     * 事务生效，写法丑陋，不超卖
     */
    @PostMapping("/order/right/one/create")
    public Results<Boolean>   createOrderRightOne(@RequestParam Integer productId)  {
        productService.createOrderRightOne(productId);
        return  Results.success();
    }

    /**
     * 事务生效，写法丑陋，不超卖。
     * 对事务属性：noRollback和rollBackFor的优先级测试。rollBackFor优先级更高
     */
    @PostMapping("/order/right/two/create")
    public Results<Boolean> createOrderRightTwo(@RequestParam Integer productId)  {
        productService.createOrderRightTwo(productId);
        return  Results.success();
    }

    /**
     * 事务嵌套调用。catch了异常也会回滚
     */
    @PostMapping("/order/nest/one/create")
    public Results<Boolean>  createOrderNestOne(@RequestParam Integer productId)  {
        productService.createOrderNestOne(productId);
       return Results.success();
    }
    /**
     * 事务嵌套调用。新开一个事务，product不回滚，order回滚
     */
    @PostMapping("/order/nest/two/create")
    public Results<Boolean>  createOrderNestTwo(@RequestParam Integer productId)  {
        productService.createOrderNestTwo(productId);
        return Results.success();
    }

    /**
     * 事务嵌套调用。子方法内部将异常try catch了，所以子方法没发生回滚。product不回滚，order也不回滚
     */
    @PostMapping("/order/nest/three/create")
    public Results<Boolean> createOrderNestThree(@RequestParam Integer productId)  {
        productService.createOrderNestThree(productId);
        return Results.success();
    }

    /**
     * 事务嵌套调用。子方法抛出异常，主方法捕获异常。product回滚，order也回滚
     */
    @PostMapping("/order/nest/four/create")
    public Results<Boolean> createOrderNestFour(@RequestParam Integer productId)  {
        productService.createOrderNestFour(productId);
        return Results.success();
    }


}
