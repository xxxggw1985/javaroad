package com.javaroad.designpattern.responsibility;

public class ItemInfoHandler extends AbstractDataHandler<ItemInfoHandler.ItemInfo>{
    @Override
    protected ItemInfo handleRequest(String qry) throws Exception {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.itemId = 1L;
        itemInfo.itemName = "测试item";
        return itemInfo;
    }

    public static  class ItemInfo {
        private Long itemId;
        private String itemName;

        @Override
        public String toString() {
            return "ItemInfo{" +
                    "itemId=" + itemId +
                    ", itemName='" + itemName + '\'' +
                    '}';
        }
    }
}
