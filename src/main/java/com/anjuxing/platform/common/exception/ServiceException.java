package com.anjuxing.platform.common.exception;

public class ServiceException extends ControllerException {

	private static final long serialVersionUID = 305036544135837833L;
	
	public ServiceException() {
		super();
	}
	
	public ServiceException(String code, String message) {
		super(code, message);
	}

}
