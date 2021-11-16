package com.javaroad.transaction.controller;

import com.jayway.jsonpath.JsonPath;
import common.ApiTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends ApiTest {

    @Sql(scripts = "classpath:sql/product-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/product-test-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    @DisplayName("超卖。商品的库存(productCount)是100，请求是110,产生的订单数量超过库存，而最后的商品库存竟然显示大于0")
    public void should_over_selling_when_request_gt_productCount_given_transaction_contains_lock() {
        final Integer productId = 1;
        final Integer requestCount = 110;
        CountDownLatch countDownLatch = new CountDownLatch(requestCount);
        for(int i = 0; i < requestCount; i++){
            new Thread(()->{
                try {
                    mockMvc.perform(post(baseUrl + "/product/order/wrong/one/create?productId="+productId));
                    countDownLatch.countDown();
                } catch (Exception e) {
                    countDownLatch.countDown();
                }

            }).start();
        }
        try {
            countDownLatch.await(); // 主线程等待
            String info = mockMvc.perform(post(baseUrl + "/product/info?productId=" + productId))
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
            Integer productCount = JsonPath.parse(info).read("$.data.productCount");
            Integer orderCount = JsonPath.parse(info).read("$.data.orderCount");
            assertThat(productCount).isGreaterThan(0);
            assertThat(orderCount).isEqualTo(requestCount);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Sql(scripts = "classpath:sql/product-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/product-test-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    @DisplayName("锁生效，事务不生效。商品的库存(productCount)是100，请求是110,产生的订单数量为0，商品的库存最终为0")
    public void should_transaction_invalid_given_lock_contains_transaction() {
        final Integer productId = 1;
        final Integer requestCount = 110;
        CountDownLatch countDownLatch = new CountDownLatch(requestCount);
        for(int i = 0; i < requestCount; i++){
            new Thread(()->{
                try {
                    mockMvc.perform(post(baseUrl + "/product/order/wrong/two/create?productId="+productId));
                    System.out.println("out------");
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                    countDownLatch.countDown();
                }

            }).start();
        }
        try {
            countDownLatch.await(); // 主线程等待
            String info = mockMvc.perform(post(baseUrl + "/product/info?productId=" + productId))
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
            Integer productCount = JsonPath.parse(info).read("$.data.productCount");
            Integer orderCount = JsonPath.parse(info).read("$.data.orderCount");
            assertThat(productCount).isEqualTo(0);
            assertThat(orderCount).isEqualTo(0);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Sql(scripts = "classpath:sql/product-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/product-test-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    @DisplayName("锁生效，事务生效。商品的库存(productCount)是100，请求是110,产生的订单数量为0，商品的库存最终为100")
    public void should_transaction_valid_given_lock_contains_transaction() {
        final Integer productId = 1;
        final Integer requestCount = 110;
        CountDownLatch countDownLatch = new CountDownLatch(requestCount);
        for(int i = 0; i < requestCount; i++){
            new Thread(()->{
                try {
                    mockMvc.perform(post(baseUrl + "/product/order/right/one/create?productId="+productId));
                    System.out.println("out------");
                    countDownLatch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                    countDownLatch.countDown();
                }

            }).start();
        }
        try {
            countDownLatch.await(); // 主线程等待
            String info = mockMvc.perform(post(baseUrl + "/product/info?productId=" + productId))
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
            Integer productCount = JsonPath.parse(info).read("$.data.productCount");
            Integer orderCount = JsonPath.parse(info).read("$.data.orderCount");
            assertThat(productCount).isEqualTo(100);
            assertThat(orderCount).isEqualTo(0);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Sql(scripts = "classpath:sql/product-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/product-test-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    @DisplayName("rollbackFor优先级高于noRollbackFor。锁生效，事务生效。商品的库存(productCount)是100，请求是110,产生的订单数量为0，商品的库存最终为100")
    public void should_transaction_valid_when_exists_rollbackFor_and_noRollbackFor_given_lock_contains_transaction() {
        final Integer productId = 1;
        final Integer requestCount = 110;
        CountDownLatch countDownLatch = new CountDownLatch(requestCount);
        for(int i = 0; i < requestCount; i++){
            new Thread(()->{
                try {
                    mockMvc.perform(post(baseUrl + "/product/order/right/two/create?productId="+productId));
                    countDownLatch.countDown();
                } catch (Exception e) {
                    countDownLatch.countDown();
                }

            }).start();
        }
        try {
            countDownLatch.await(); // 主线程等待
            String info = mockMvc.perform(post(baseUrl + "/product/info?productId=" + productId))
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();
            Integer productCount = JsonPath.parse(info).read("$.data.productCount");
            Integer orderCount = JsonPath.parse(info).read("$.data.orderCount");
            assertThat(productCount).isEqualTo(100);
            assertThat(orderCount).isEqualTo(0);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Sql(scripts = "classpath:sql/product-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/product-test-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    @DisplayName("should_rollback_when_nest_method_throw_exception")
    public void should_all_rollback_when_nest_method_throw_exception() throws Exception {
        final Integer productId = 1;
            mockMvc.perform(post(baseUrl + "/product/order/nest/one/create?productId="+productId))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString()
                    ;
        String info  = mockMvc.perform(post(baseUrl + "/product/info?productId=" + productId))
                    .andDo(print())
                    .andReturn().getResponse().getContentAsString();

        Integer productCount = JsonPath.parse(info).read("$.data.productCount");
        Integer orderCount = JsonPath.parse(info).read("$.data.orderCount");
        assertThat(productCount).isEqualTo(100);
        assertThat(orderCount).isEqualTo(0);
    }

    @Sql(scripts = "classpath:sql/product-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/product-test-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    @DisplayName("should_product_rollback_and_order_not_rollback")
    public void should_product_rollback_and_order_not_rollback() throws Exception {
        final Integer productId = 1;
        mockMvc.perform(post(baseUrl + "/product/order/nest/two/create?productId="+productId))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString()
        ;
        String info  = mockMvc.perform(post(baseUrl + "/product/info?productId=" + productId))
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        Integer productCount = JsonPath.parse(info).read("$.data.productCount");
        Integer orderCount = JsonPath.parse(info).read("$.data.orderCount");
        assertThat(productCount).isEqualTo(100-1);
        assertThat(orderCount).isEqualTo(0);
    }

    @Sql(scripts = "classpath:sql/product-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/product-test-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    @DisplayName("should_product_not_rollback_and_order_rollback")
    public void should_product_not_rollback_and_order_rollback() throws Exception {
        final Integer productId = 1;
        mockMvc.perform(post(baseUrl + "/product/order/nest/three/create?productId="+productId))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString()
        ;
        String info  = mockMvc.perform(post(baseUrl + "/product/info?productId=" + productId))
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        Integer productCount = JsonPath.parse(info).read("$.data.productCount");
        Integer orderCount = JsonPath.parse(info).read("$.data.orderCount");
        assertThat(productCount).isEqualTo(99);
        assertThat(orderCount).isEqualTo(1);
    }

    @Sql(scripts = "classpath:sql/product-test-before.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(scripts = "classpath:sql/product-test-after.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Test
    @DisplayName("should_product_rollback_and_order_not_rollback")
    public void should_product_rollback_and_order_rollback() throws Exception {
        final Integer productId = 1;
        mockMvc.perform(post(baseUrl + "/product/order/nest/four/create?productId="+productId))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString()
        ;
        String info  = mockMvc.perform(post(baseUrl + "/product/info?productId=" + productId))
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        Integer productCount = JsonPath.parse(info).read("$.data.productCount");
        Integer orderCount = JsonPath.parse(info).read("$.data.orderCount");
        assertThat(productCount).isEqualTo(100);
        assertThat(orderCount).isEqualTo(0);
    }
}