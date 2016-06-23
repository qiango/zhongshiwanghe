package com.hongzhi.zswh.front;

import java.util.regex.Pattern;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 11, 2016    7:34:18 PM
 */
public class TestGson {
	public static void main(String[] args) {
	    Pattern p = Pattern.compile("\\d+");
	    String reg = "\\d+";
	    String str = "2342345";
	    
	    System.out.println(str.matches("\\d+"));
	   
	}
}
