package com.anjuxing.platform.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CodeUtils {
	
	private static int initid = 100001;
	public static synchronized String getPrimaryKey(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
		String id = sdf.format(new Date());
		id = id + initid + getRandom(6);
		initid += 1;
		if (initid >= 999999) {
			initid = 100001;
		}
		return id;
	}
	
	private static int initLid = 1001;
	public static synchronized Long getSequenceId(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmssSSS");
		String sid = sdf.format(new Date()) + initLid;
		initLid += 1;
		if(initLid >= 9999){
			initLid = 1001;
		}
		return Long.parseLong(sid);
	}
	
	public static synchronized String getRandom(int num){
		String random = "";
	    for (int i = 0; i < num; i++) {
	      int t = (int)(Math.random() * 10.0D);
	      random = random + String.valueOf(t);
	    }
	    return random;
	}
	
	public static synchronized String getUUID(){
		return UUID.randomUUID().toString();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getPrimaryKey());
		System.out.println(getSequenceId());
	}
}
