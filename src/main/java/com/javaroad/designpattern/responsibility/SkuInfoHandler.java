package com.javaroad.designpattern.responsibility;

public class SkuInfoHandler extends AbstractDataHandler<SkuInfoHandler.SkuInfo>{
    @Override
    protected SkuInfo handleRequest(String qry) throws Exception {
        SkuInfo info = new SkuInfo();
        info.skuId = 1L;
        info.skuName = "测试item";
        return info;
    }


    public static  class SkuInfo {
        @Override
        public String toString() {
            return "SkuInfo{" +
                    "skuId=" + skuId +
                    ", skuName='" + skuName + '\'' +
                    '}';
        }

        private Long skuId;
        private String skuName;
    }
}
