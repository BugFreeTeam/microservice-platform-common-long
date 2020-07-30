package com.anjuxing.platform.common.exception;

public class ControllerException extends BaseException {

	private static final long serialVersionUID = -7019376174829962061L;
	
	public ControllerException() {
		super();
	}
	
	public ControllerException(String code, String message) {
		super(code, message);
	}

}
