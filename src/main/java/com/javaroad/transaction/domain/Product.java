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
@TableName("yy_product")
public class Product {
    @TableId(type = IdType.AUTO)
    Integer id;

    @TableField
    Integer productCount;

    @TableField
    String productName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}

