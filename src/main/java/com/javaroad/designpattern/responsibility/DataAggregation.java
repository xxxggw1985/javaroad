package com.javaroad.designpattern.responsibility;

import java.util.HashMap;
import java.util.Map;

public class DataAggregation {
    private SkuInfoHandler skuInfoHandler = new SkuInfoHandler();
    private ItemInfoHandler itemInfoHandler = new ItemInfoHandler();

    public Map convertItemDetail() throws Exception {
        Map result = new HashMap();
        result.put("skuInfoHandler", skuInfoHandler.handleRequest("模拟数据请求"));
        result.put("itemInfoHandler",itemInfoHandler.handleRequest("模拟数据请求"));
        return result;
    }

    public static void main(String[] args) throws Exception {
        DataAggregation dataAggregation = new DataAggregation();
        Map map = dataAggregation.convertItemDetail();
        System.out.println(map);
    }
}
