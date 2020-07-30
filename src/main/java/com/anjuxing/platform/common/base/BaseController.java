package com.anjuxing.platform.common.base;

/**
 * 基础Controller
 */
public class BaseController {
	

    private JsonResult jsonResult;

    public JsonResult getJsonResult() {
        this.jsonResult = new JsonResult();
        this.jsonResult.setResult(FAILURE);
        this.jsonResult.setCode(SUCCESS_CODE);
        this.jsonResult.setMessage(SUCCESS_MESSAGE);
        return jsonResult;
    }

    public void setJsonResult(JsonResult jsonResult) {
        this.jsonResult = jsonResult;
    }

    public static final String FAILURE = "0"; //失败
    public static final String SUCCESS = "1"; //成功
    public static final String NO_DATA_EXIST = "2"; //数据不存在
    public static final String NOLOGIN = "0"; //未登录
    public static final String NORMAL = "1"; //正常
    public static final String NOAUTH = "2"; //未授权
    public static final String SUCCESS_CODE = "000000"; //操作成功代码
    public static final String SUCCESS_MESSAGE = "操作成功"; //操作成功信息
    public static final String FAILURE_MESSAGE = "操作失败"; //操作失败信息
    
    public static final String MSG_NO_DATA_EXIST = "数据不存在";
    public static final String MSG_PARAMETERS_EMPTY = "参数不能为空";
    public static final String MSG_PARAMETERS_ERROR = "传入参数错误";
}
