package com.adanana.core;

/**
 * 统一返回格式
 */
public class ResponseObject {
    //请求失败
    public static  final  String CODE_FAIL = "T";
    public static  final  String CODE_SUCCESS = "S";
    private String code;
    private String info;
    private Object content;

    public static  ResponseObject success(){
        ResponseObject responseObject = new ResponseObject();
        responseObject.setCode(ResponseObject.CODE_SUCCESS);
        responseObject.setInfo("success");
        return responseObject;
    }

    public static  ResponseObject success(Object content){
        ResponseObject responseObject = ResponseObject.success();
        responseObject.setContent (content);
        return responseObject;
    }

    public static  ResponseObject fail(){
        ResponseObject responseObject = new ResponseObject();
        responseObject.setCode(ResponseObject.CODE_FAIL);
        responseObject.setInfo("fail");
        return responseObject;
    }
    public static  ResponseObject fail(String info){
        ResponseObject responseObject = ResponseObject.fail();
        responseObject.setInfo(info);
        return responseObject;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
