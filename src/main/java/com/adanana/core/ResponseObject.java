package com.adanana.core;

/**
 * 统一返回格式
 */
public class ResponseObject {
    //请求失败
    public static  final  String CODE_FAIL = "F";
    public static  final  String CODE_SUCCESS = "S";
    public static  final  String NO_LOGIN = "NO_LOGIN";
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
    //未登录
    public static  ResponseObject noLogin(){
        ResponseObject responseObject = new ResponseObject();
        responseObject.setCode(ResponseObject.NO_LOGIN);
        responseObject.setInfo("未登录");
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
