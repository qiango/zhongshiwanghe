package com.hongzhi.zswh.util.basic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DbFile {

	private static String getPath(String filename){
		return "/mnt/resource/dbfile/"+filename;
//		return "D:/export/"+filename;
	}

	public static String getFileContext(String filename){
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(getPath(filename))) );
			String sout = "";
			String s_curr = "";
			while( null != s_curr ) {
				sout +=s_curr;
				s_curr = br.readLine();
			}

			return sout;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	public static void writeFile(String str , String filename) {
		try {
			FileWriter fw = new FileWriter(getPath(filename)) ;
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(str);
			bw.flush();
			fw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
