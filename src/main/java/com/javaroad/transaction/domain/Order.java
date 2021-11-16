package com.javaroad.transaction.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @description: TODO
 * @author:ggw
 * @createTime: 2021-08-25 14:58
 * @version: 1.0
 */
@TableName("yy_order")
public class Order {
    @TableId(type = IdType.AUTO)
    Integer id;

    @TableField
    Integer productId;

    @TableField
    String buyerName;


    @TableField
    String buyerGoods;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerGoods() {
        return buyerGoods;
    }

    public void setBuyerGoods(String buyerGoods) {
        this.buyerGoods = buyerGoods;
    }
}
