package com.hongzhi.zswh.util.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import com.hongzhi.zswh.util.basic.ObjectUtil;

/**   Twitter : @taylorwang789 
 * Creat time : Apr 12, 2016    6:19:21 PM
 */
public class SHA256 {
	
	private static List<String> list = Arrays.asList("1","2","3","4" ,"5","6","7","8" ,"9","0","q","w" ,"e","r","t","y" ,"u","i","o","p" ,"a","s","d","f" ,"g","h","j","k" ,"l","z","x","c" ,"v","b","n","m" ,"Q","W","E","R" ,"T","Y","U","I" ,"O","P","A","S" ,"D","F","G","H" ,"J","K","L","Z" ,"X","C","V","B" ,"N","M");
	
	
	private  static  String  sha(String str){
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			return DatatypeConverter.printHexBinary(digest.digest(str.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getSalt() {
		Random random = new Random();
		Collections.shuffle(list);
		String salt =  list.subList(0, random.nextInt(5)+6).toString().replaceAll(" ", "").replaceAll(",", "").replace("[", "").replace("]", "") ;
		return salt;
	}
	
	public static String encode(String str, String salt){
		return sha(salt + sha(str));
	}
	
	public static boolean  verifyPassword(String inputPassword,String passwordHash,Object salt){
		if(ObjectUtil.isEmpty(salt)){
			return  inputPassword.equals(passwordHash) ? true :false ;
		}else{
			return  encode(inputPassword, salt.toString()).equals(passwordHash) ? true : false ; 
		}
	}
	

}
