package com.anjuxing.platform.common.exception;

/**
 * 异常信息
 */
public class BaseException extends Exception{

	private static final long serialVersionUID = -7129719745481468060L;
	
	private String code;

    public BaseException() {
        super();
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
