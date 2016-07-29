package com.hongzhi.zswh.util.date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 1, 2016    1:16:51 PM
 */
public class DateFormat {
	
	private static ArrayList<ArrayList<String>>  formats = new ArrayList<>();
	
	static {
	   ArrayList<String> date8List = new ArrayList<>();
		date8List.add("yyyy-MM-dd");
		date8List.add("zh") ;
	  ArrayList<String> date10ListSlash =new ArrayList<>();
		date10ListSlash.add("MM/dd/yyyy");
		date10ListSlash.add("us");

		// add list 
		formats.add(date8List);
		formats.add(date10ListSlash);
	}

	public static String getFormat(String language) {
		 for(int i=0;i<formats.size();i++){
			 if(formats.get(i).contains(language.trim().toLowerCase())){
				 return formats.get(i).get(0);
			 }
		 }
		 return "yyyy-MM-dd";
	}
	
	public static String getFormatWithTime(String language ) {
		 for(int i=0;i<formats.size();i++){
			 if(formats.get(i).contains(language.trim().toLowerCase())){
				 return formats.get(i).get(0)+" HH:mm:ss";
			 }
		 }
		 return "yyyy-MM-dd HH:mm:ss";
	}
	
	public static String getDateFromMillis(String language,Long millis){
//        Date date = new Date(millis);
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 # HH:mm");
//        SimpleDateFormat sdf_week = new SimpleDateFormat("E");
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(millis);
        String out = sdf.format(cal.getTime());
        if(language.equals("zh")){
           int week_name = cal.get(Calendar.DAY_OF_WEEK);
           switch (week_name) {
           case Calendar.MONDAY: out = out.replaceAll("#", "周一"); break;
           case Calendar.TUESDAY: out = out.replaceAll("#", "周二"); break;
           case Calendar.WEDNESDAY: out = out.replaceAll("#", "周三"); break;
           case Calendar.THURSDAY: out = out.replaceAll("#", "周四"); break;
           case Calendar.FRIDAY: out = out.replaceAll("#", "周五"); break;
           case Calendar.SATURDAY: out = out.replaceAll("#", "周六"); break;
           case Calendar.SUNDAY: out = out.replaceAll("#", "周日"); break;
           default: break;
           }
        }
	    return out ;
	}

}
