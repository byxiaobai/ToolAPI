package com.toolapi.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
* @author byxiaobai
* 类说明 
*/
public class StringUtil {
	private static Pattern numberPattern = Pattern.compile("[0-9]*");
	/**
	 * 将某字符串中的所有 & 替换为  §
	 * @param str
	 * @return
	 */
	public static String replaceAllColorChar(String str) {
		return str.replaceAll("&", "§");
	}
	public static boolean isNumber(String str) {
        Matcher isNum = numberPattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
	}
}
