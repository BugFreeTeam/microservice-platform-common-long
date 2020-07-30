package com.anjuxing.platform.common.base;

import java.io.Serializable;

public class BaseModel<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static final Integer STATUS_VALID = 1;
	public static final Integer STATUS_INVALID = 0;
	public static final Integer FLAG_NORMAL = 1;
	public static final Integer FLAG_CANCEL = 0;
	
}
