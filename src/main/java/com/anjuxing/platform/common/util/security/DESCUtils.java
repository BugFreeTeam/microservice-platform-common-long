package com.anjuxing.platform.common.util.security;

import com.anjuxing.platform.common.util.ValidateUtils;

/**
 * 泛海三江门禁机数据加密 Java调用C加密库，只适用于三江门禁机数据加密
 * <br/>descbc64.dll库文件需复制到JAVA_HOME/bin目录下
 */
public class DESCUtils {
	
	static {
		System.loadLibrary("/descbc64");
		init();
	}
	
	/**
	 * 初始化
	 */
	public native static void init();
	/**
	 * 三江门禁机数据加密  明文长度不超过100字符
	 * @param value
	 * @return
	 */
    public native static String encrypt(String value);
    /**
     * 三江门禁机数据解密
     * @param value
     * @return
     */
    public native static String decrypt(String value);
	/**
	 * 三江门禁机数据解密，解密失败时返回传入值
	 * @param value
	 * @return
	 */
	public static String decryptDefault(String value){
		String result = decrypt(value);
		if (ValidateUtils.isEmpty(result)){
			result = value;
		}
		return result;
	}
    
    public static void main(String[] args) {
		long start = System.currentTimeMillis();
		System.out.println(decryptDefault("0136000008574400"));
		System.out.println("========================" + (System.currentTimeMillis() - start));
	}

}
