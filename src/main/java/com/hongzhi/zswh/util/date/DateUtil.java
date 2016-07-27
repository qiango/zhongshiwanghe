package com.hongzhi.zswh.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Saxon create date: 2016年3月1日 下午3:07:34
 * 
 */
public class DateUtil {

	/**
	 * @return yyyyMMddHHmmss
	 */
	public static String getSysAllDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式
		return df.format(new Date()).toString();
	}

	/**
	 * @return yyyyMMdd
	 */
	public static String getSysDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式
		return df.format(new Date()).toString();
	}

	/**
	 * @return HHmmss
	 */
	public static String getSysTime() {
		SimpleDateFormat df = new SimpleDateFormat("HHmmss");// 设置日期格式
		return df.format(new Date()).toString();
	}

	/**
	 * yyyyMMddHHmmss 转 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param str
	 * @return
	 */
	public static String formatDateTime(String str, String language) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}

		DateFormat format2 = null;
		if ("zh".equals(language)) {
			format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} else if ("en".equals(language)) {
			format2 = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		}
		String str2 = format2.format(date);
		return str2;
	}

	public static void main(String[] args) {
		System.out.println(DateUtil.formatDateTimeNoHToDb("2016-10-03 10:50"));
	}

	/**
	 * yyyyMMdd 转 yyyy年MM月dd日
	 * 
	 * @param str
	 * @return
	 */
	public static String formatInternationalizationDate(String str, String language) {
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}

		DateFormat format2 = null;
		if ("zh".equals(language)) {
			format2 = new SimpleDateFormat("yyyy-MM-dd");
		} else if ("en".equals(language)) {
			format2 = new SimpleDateFormat("dd/MM/yyyy");
		}
		String str2 = format2.format(date);
		return str2;
	}

	/**
	 * HHmmss 转 HH小时mm分ss秒
	 * 
	 * @param str
	 * @return
	 */
	public static String formatInternationalizationTime(String str) {
		DateFormat format = new SimpleDateFormat("HHmmss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}

		DateFormat format2 = null;
		format2 = new SimpleDateFormat("HH:mm:ss");
		String str2 = format2.format(date);
		return str2;
	}

	/**
	 * yyyy-MM-dd 转 yyyyMMdd
	 * 
	 * @param str
	 * @return
	 */
	public static String formatDate(String str) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}

		DateFormat format2 = null;
		format2 = new SimpleDateFormat("yyyyMMdd");
		String str2 = format2.format(date);
		return str2;
	}
	/**
	 * "yyyy-MM-dd HH:mm:ss 转 yyyyMMddHHmmss
	 * 
	 * @param str
	 * @return
	 */
	public static String formatDateTimeToDB(String str) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DateFormat format2 = null;
		format2 = new SimpleDateFormat("yyyyMMddHHmmss");
		String str2 = format2.format(date);
		return str2;
	}
	/**
	 * "yyyy-MM-dd HH:mm 转 yyyyMMddHHmm
	 * 
	 * @param str
	 * @return
	 */
	public static String formatDateTimeNoSecondToDB(String str) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DateFormat format2 = null;
		format2 = new SimpleDateFormat("yyyyMMddHHmm");
		String str2 = format2.format(date);
		return str2;
	}
	
	
	/**
	 * yyyyMMddHHmm 转 yyyy-MM-dd HH:mm
	 * 
	 * @param str
	 * @return
	 */
	public static String formatDBToDateTimeNoSecond(String str, String language) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}

		DateFormat format2 = null;
		if ("zh".equals(language)) {
			format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		} else if ("en".equals(language)) {
			format2 = new SimpleDateFormat("HH:mm dd/MM/yyyy");
		}
		String str2 = format2.format(date);
		return str2;
	}

	public static String formatDateTimeNoHToDb(String str){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		DateFormat format2 = null;
		format2 = new SimpleDateFormat("yyyyMMdd");
		String str2 = format2.format(date);
		return str2;
	}


	/**
	 * 根据当前日期获取当前日期所在周的周一和周日的日期
	 * @param date
	 * @return
     */
	public static Map<String, String> getWeekDay(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		if (date == null){
			date =	sdf.format(new Date());
		}
		Map<String, String> map = new HashMap<String, String>();

		Calendar cal = Calendar.getInstance();
		Date time = null;
		try {
			time = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		cal.setTime(time);


		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}

		cal.setFirstDayOfWeek(Calendar.MONDAY);

		int day = cal.get(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);

		map.put("monday_date", sdf.format(cal.getTime()));
		cal.add(Calendar.DATE, 6);

		map.put("sundays_date", sdf.format(cal.getTime()));

		return map;
	}

}
