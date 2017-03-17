/**   
 * 
 * @Title: CoverMathUtil.java 
 * @Prject: HcDBCrawlNew
 * @Package: com.hc.utils.math 
 * @Description: TODO
 * @author: chenchao   
 * @date: 2016年1月6日 下午3:18:55 
 * @version: V1.0   
 */
package com.cover.utils;

import java.math.BigDecimal;

import org.apache.log4j.Logger;

/**
 * @ClassName: CoverMathUtil
 * @Description: TODO
 * @author: chenchao
 * @date: 2016年1月6日 下午3:18:55
 */
public class CFMathUtil {
	private static Logger logger = Logger.getLogger(CFMathUtil.class);

	public static int parseInt(String str) {
		int back = 0;
		try {
			back = Integer.parseInt(str);
		} catch (Exception e) {
		}
		return back;
	}

	public static double parseDouble(String str) {
		double back = 0, multiplier = 1;
		try {
			if (str.contains("万")) {
				multiplier = 10000;
				str = str.replace("万", "");
			}
			back = Double.parseDouble(str) * multiplier;
		} catch (Exception e) {
		}
		return back;
	}

	public static int compareDouble(double d1, double d2) {
		BigDecimal data1 = new BigDecimal(d1);
		BigDecimal data2 = new BigDecimal(d2);
		return data1.compareTo(data2);
	}

	public static void main(String[] args) {
		logger.info(compareDouble(0.101, 0.10200000000000000000000000000000000000001));

	}
}
