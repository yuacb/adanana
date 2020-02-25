package com.adanana.spider.tools;
/**
 * 微博有关的枚举
 */
public enum WeiBoEnum {

    WEBO_FORWARD("W_ficon.ficon_forward.S_ficon","转发","number_forward","numberForward"),
    WEBO_FAVORITE ("W_ficon.ficon_favorite.S_ficon","收藏","number_favorite","numberFavorite"),
    WEBO_REPEAT("W_ficon.ficon_repeat.S_ficon","评论","number_repeat","numberRepeat"),
    WEBO_PRAISED("W_ficon.ficon_praised.S_txt2","赞","number_praised","numberPraised");

    //html 中对应的HTML
    private String code;
    //中文名称
    private String value;
    //列明
    private String columnName;
    //字段名
    private String fieldName;
    WeiBoEnum(String code, String value,String columnName,String fieldName) {
        this.code = code;
        this.value = value;
        this.columnName = columnName;
        this.fieldName = fieldName;
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

    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}