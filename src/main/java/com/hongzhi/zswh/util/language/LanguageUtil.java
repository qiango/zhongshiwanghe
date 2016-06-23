package com.hongzhi.zswh.util.language;

public class LanguageUtil {
	
	public static String getLanguageAbbreviation(String str) {
		return str.substring(0, 2);
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(LanguageUtil.getLanguageAbbreviation("zh-hant_CN"));
	}
	
}
