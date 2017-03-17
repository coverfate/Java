/**   
 * 
 * @Title: CFMD5Utils.java 
 * @Prject: HcCRM
 * @Package: com.cover.utils 
 * @Description: TODO
 * @author: chenchao   
 * @date: 2015年12月30日 下午4:26:10 
 * @version: V1.0   
 */
package com.cover.utils;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

/**
 * @ClassName: CFMD5Utils
 * @Description: TODO
 * @author: chenchao
 * @date: 2015年12月30日 下午4:26:10
 */
public class CFMD5Util {
	private static Logger logger = Logger.getLogger(CFMD5Util.class);

	public static String convertToHexString(byte data[]) {
		StringBuffer strBuffer = new StringBuffer();
		int currData;
		for (int i = 0; i < data.length; i++) {
			currData = data[i];
			if (currData < 0)
				currData += 256;
			if (currData < 16)
				strBuffer.append("0");
			strBuffer.append(Integer.toHexString(currData));

		}
		return strBuffer.toString();
	}

	public static String makeMD5(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes("UTF8"));
			byte data[] = md.digest();
			String pwd = convertToHexString(data);
			return pwd;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}

	public static String makeMD5(String account, String password) {
		return makeMD5(makeMD5(password) + account);
	}

	public static void main(String[] args) {
		logger.info(makeMD5("rootAdmin"));
		logger.info(makeMD5("US10000000", "rootAdmin"));

	}
}
