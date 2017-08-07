package com.jmy.web.utils;

import java.io.UnsupportedEncodingException;

public class SUtils {
	/**
	 * ISO-8859-1 to utf-8
	 * it is suitable for request  coding transfer of get
	 */
	public static String toUtf(String iso_8859_1){
		String newString="";
		try {
			newString = new String(iso_8859_1.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return newString;
	}
}
