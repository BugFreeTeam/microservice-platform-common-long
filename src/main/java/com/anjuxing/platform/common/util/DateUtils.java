package com.anjuxing.platform.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat yyyyMMddHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat yyyyMMddHHMMSSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
	
	/**
	 * 获取当前日期
	 * @return String
	 */
	public static synchronized String getCurrentDateStr(){
		return yyyyMMdd.format(new Date());
	}
	/**
	 * 获取当前日期
	 * @return Date
	 */
	public static synchronized Date getCurrentDate(){
		Date date = null;
		try {
			date = yyyyMMdd.parse(getCurrentDateStr());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 获取当前时间
	 * @return String
	 */
	public static synchronized String getCurrentTimeStr(){
		return yyyyMMddHHMMSS.format(new Date());
	}
	/**
	 * 获取当前时间
	 * @return Date
	 */
	public static synchronized Date getCurrentTime(){
		Date date = null;
		try {
			date = yyyyMMddHHMMSS.parse(getCurrentTimeStr());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 获取指定日期增加天数后的日期
	 * @param date
	 * @param day
	 * @return Date
	 */
	public static synchronized Date getAddDaysDate(Date date, int day){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(c.DAY_OF_MONTH, day); //增加天数
		return c.getTime(); 
	}
	/**
	 * 获取指定日期增加天数后的日期
	 * @param date
	 * @param day
	 * @return String
	 */
	public static synchronized String getAddDaysDateStr(Date date, int day){
		Date addDate = getAddDaysDate(date, day);
		return yyyyMMddHHMMSS.format(addDate); 
	}
	/**
	 * 获取指定时间增加分钟后的时间
	 * @param time
	 * @param minute
	 * @return Date
	 */
	public static synchronized Date getAddMinuteTime(Date time, int minute){
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(c.MINUTE, minute); //增加分钟
		return c.getTime(); 
	}
	/**
	 * 获取指定时间增加分钟后的时间
	 * @param time
	 * @param minute
	 * @return String
	 */
	public static synchronized String getAddMinuteTimeStr(Date time, int minute){
		Date date = getAddMinuteTime(time, minute);
		return yyyyMMddHHMMSS.format(date); 
	}
	/**
	 * 获取指定时间减去分钟后的时间
	 * @param time
	 * @param minute
	 * @return Date
	 */
	public static synchronized Date getSubtractMinuteTime(Date time, int minute){
		Calendar c = Calendar.getInstance();
		c.setTime(time);
		c.add(c.MINUTE, -minute); //减少分钟
		return c.getTime(); 
	}
	/**
	 * 获取指定时间减去分钟后的时间
	 * @param time
	 * @param minute
	 * @return String
	 */
	public static synchronized String getSubtractMinuteTimeStr(Date time, int minute){
		Date date = getSubtractMinuteTime(time, minute);
		return yyyyMMddHHMMSS.format(date);
	}
	/**
	 * 与当前时间相比：0相等；1大于；-1小于；
	 * @param time
	 * @return
	 */
	public static synchronized int compareCurrentTime(Date time){
		int result = 0;
		Date currentTime = getCurrentTime();
		if (time.getTime() > currentTime.getTime()) { //大于当前时间
			result = 1;
		} else if (time.getTime() < currentTime.getTime()) {
			result = -1;
		}
		return result;
	}
	/**
	 * 比较两个时间大小
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static synchronized int compareTime(Date time1, Date time2){
		int result = 0;
		if (time1.getTime() > time2.getTime()) { //大于当前时间
			result = 1;
		} else if (time1.getTime() < time2.getTime()) {
			result = -1;
		}
		return result;
	}
	
	
	/**
	 * 获取格式化时间  yyyy-MM-dd HH:MM:SS
	 * @param time
	 * @return
	 */
	public static synchronized Date getParseTime(String time){
		Date date = null;
		if (ValidateUtils.isNotEmpty(time)) {
			try {
				if (time.indexOf("/") > 0) {
					time = time.replaceAll("/", "-");
				}
				date = yyyyMMddHHMMSS.parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
	/**
	 * 获取格式化时间  yyyy-MM-dd HH:MM:SS
	 * @param time
	 * @return
	 */
	public static synchronized String getFormatTime(Date time){
		String str = "";
		if (ValidateUtils.isNotEmpty(time)) {
			try {
				str = yyyyMMddHHMMSS.format(time);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}
	
	public static synchronized Date parseTime(String time){
		Date date = null;
		if(ValidateUtils.isNotEmpty(time)){
			try {
				date = yyyyMMddHHMMSS.parse(time);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return date == null ? getCurrentTime() : date;
	}
	
	/**
	 * 获取格式化日期  yyyy-MM-dd
	 * @param dateStr
	 * @return
	 */
	public static synchronized Date getParseDate(String dateStr){
		Date date = null;
		if (ValidateUtils.isNotEmpty(dateStr)) {
			try {
				date = yyyyMMdd.parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}
	/**
	 * 获取格式化日期  yyyy-MM-dd
	 * @param dateStr
	 * @return
	 */
	public static synchronized String getFormatDate(Date date){
		String str = "";
		if (date != null) {
			try {
				str = yyyyMMdd.format(date);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return str;
	}
	/**
	 * 获取当前年份
	 * @return
	 */
	public static synchronized String getCurrentYear(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return String.valueOf(year);
	}
	/**
	 * 获取当前月份
	 * @return
	 */
	public static synchronized String getCurrentMonth(){
		String result = "";
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH)+1;
		if (month < 10) {
			result = "0" + month;
		} else {
			result = String.valueOf(month);
		}
		return result;
	}
	/**
	 * 获取当前天数
	 * @return
	 */
	public static synchronized String getCurrentDay(){
		String result = "";
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DATE);
		if (day < 10) {
			result = "0" + day;
		} else {
			result = String.valueOf(day);
		}
		return result;
	}
	
	public static synchronized int getCurrentHour(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public static synchronized int getCurrentMinute(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MINUTE);
	}
	
	public static synchronized int getCurrentSecond(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.SECOND);
	}
	
	public static void main(String[] args) {
		System.out.println(getAddDaysDateStr(getCurrentDate(), -7));
		System.out.println(getCurrentDateStr());
		System.out.println(getCurrentTimeStr());
		System.out.println(getAddMinuteTimeStr(getCurrentTime(), 3));
		System.out.println(compareCurrentTime(getAddMinuteTime(getCurrentTime(), -3)));
		
		System.out.println(getCurrentYear());
		System.out.println(getCurrentMonth());
		System.out.println(getCurrentDay());
		
		System.out.println(getCurrentHour());
		System.out.println(getCurrentMinute());
		System.out.println(getCurrentSecond());
	}

}
