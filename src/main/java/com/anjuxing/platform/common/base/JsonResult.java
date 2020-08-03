package com.anjuxing.platform.common.base;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

/**
 * RESTful接口返回对象
 */
public class JsonResult<T> implements Serializable{

	private static final long serialVersionUID = -5127145149438726874L;

    public static final String SUCCESS = "1"; //成功
    public static final String FAILURE = "0"; //失败
    public static final String SUCCESS_CODE = "200"; //操作成功代码
    public static final String SUCCESS_MESSAGE = "操作成功"; //操作成功信息
    public static final String FAILURE_CODE = "500"; //操作成功代码
    public static final String FAILURE_MESSAGE = "操作失败"; //操作成功信息


    private String result; //返回结果状态0失败，1成功
    private Object data; //接口返回数据
    private String code; //操作结果返回编码
    private String message; //操作结果返回消息

    public JsonResult() {

    }

    public JsonResult(boolean result){
        if (result){
            this.result = SUCCESS;
            this.code = SUCCESS_CODE;
            this.message = SUCCESS_MESSAGE;
        }else {
            this.result = FAILURE;
            this.code = FAILURE_CODE;
            this.message = FAILURE_MESSAGE;
        }
    }

    public JsonResult(boolean result, T model){
        if (result){
            this.result = SUCCESS;
            this.data = model;
            this.code = SUCCESS_CODE;
            this.message = SUCCESS_MESSAGE;
        }else {
            this.result = FAILURE;
            this.code = FAILURE_CODE;
            this.message = FAILURE_MESSAGE;
        }
    }

    public JsonResult(T model){
        this.result = SUCCESS;
        this.data = model;
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_MESSAGE;
    }

    public JsonResult(List<T> list){
        this.result = SUCCESS;
        this.data = list;
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_MESSAGE;
    }

    public JsonResult(PageInfo<T> pageInfo){
        this.result = SUCCESS;
        this.data = pageInfo;
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_MESSAGE;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getData() {
        return data == null ? "" : data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
