package com.anjuxing.platform.common.util;

import com.anjuxing.platform.common.base.JsonResult;

public class JsonResultUtils {
    public static final String SUCCESS = "1"; //成功
    public static final String FAILURE = "0"; //失败
    public static final String SUCCESS_CODE = "200"; //操作成功代码
    public static final String SUCCESS_MESSAGE = "操作成功"; //操作成功信息
    public static final String FAILURE_CODE = "500"; //操作成功代码
    public static final String FAILURE_MESSAGE = "操作失败"; //操作成功信息

    public static JsonResult success(){
        JsonResult jsonResult=new JsonResult();
        jsonResult.setCode(SUCCESS_CODE);
        jsonResult.setMessage(SUCCESS_MESSAGE);
        jsonResult.setResult(SUCCESS);
        return jsonResult;
    }

    public static JsonResult success(Object data){
        JsonResult jsonResult=new JsonResult();
        jsonResult.setCode(SUCCESS_CODE);
        jsonResult.setMessage(SUCCESS_MESSAGE);
        jsonResult.setResult(SUCCESS);
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult success(String data){
        JsonResult jsonResult=new JsonResult();
        jsonResult.setCode(SUCCESS_CODE);
        jsonResult.setMessage(SUCCESS_MESSAGE);
        jsonResult.setResult(SUCCESS);
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult success(String message,Object data){
        JsonResult jsonResult=new JsonResult();
        jsonResult.setCode(SUCCESS_CODE);
        jsonResult.setMessage(message);
        jsonResult.setResult(SUCCESS);
        jsonResult.setData(data);
        return jsonResult;
    }

    public static JsonResult error(){
        JsonResult jsonResult=new JsonResult();
        jsonResult.setCode(FAILURE_CODE);
        jsonResult.setMessage(FAILURE_MESSAGE);
        jsonResult.setResult(FAILURE);
        return jsonResult;
    }

    public static JsonResult error(String message){
        JsonResult jsonResult=new JsonResult();
        jsonResult.setCode(FAILURE_CODE);
        jsonResult.setMessage(message);
        jsonResult.setResult(FAILURE);
        return jsonResult;
    }

    public static JsonResult error(String code,String message){
        JsonResult jsonResult=new JsonResult();
        jsonResult.setCode(code);
        jsonResult.setMessage(message);
        jsonResult.setResult(FAILURE);
        return jsonResult;
    }
}
