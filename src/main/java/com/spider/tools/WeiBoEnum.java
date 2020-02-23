package com.spider.tools;
/**
 * 微博有关的枚举
 */
public enum WeiBoEnum {

    WEBO_FORWARD("W_ficon.ficon_forward.S_ficon","转发","number_forward"),
    WEBO_FAVORITE ("W_ficon.ficon_favorite.S_ficon","收藏","number_favorite"),
    WEBO_REPEAT("W_ficon.ficon_repeat.S_ficon","评论","number_repeat"),
    WEBO_PRAISED("W_ficon.ficon_praised.S_txt2","赞","number_praised");

    private String code;

    private String value;

    private String columnName;

    WeiBoEnum(String code, String value,String columnName) {
        this.code = code;
        this.value = value;
        this.columnName = columnName;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public String getColumnName() {
        return columnName;
    }
}