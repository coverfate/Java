/**   
 * 
 * @Title: CoverDateUtil.java 
 * @Prject: HcDBCrawl
 * @Package: com.hc.utils.date 
 * @Description: TODO
 * @author: chenchao   
 * @date: 2015年11月13日 下午2:07:18 
 * @version: V1.0   
 */
package com.cover.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.log4j.Logger;

/**
 * @ClassName: CoverDateUtil
 * @Description: TODO
 * @author: chenchao
 * @date: 2015年11月13日 下午2:07:18
 */
public class CFDateUtil {
	private static Logger logger = Logger.getLogger(CFDateUtil.class);
	private static DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat fmt2 = new SimpleDateFormat("yyyy/MM/dd");
	private static DateFormat cnFmt = new SimpleDateFormat("yyyy年MM月dd日");
	private static DateFormat noneFmt = new SimpleDateFormat("yyyyMMdd");
	private static DateFormat secondFmt = new SimpleDateFormat("yyyyMMddhhmmss");
	private static DateFormat fmttime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static Date defDate = new Date(0);

	public static Date getFriday(Date curr) {
		Calendar ctime = Calendar.getInstance();
		ctime.setTime(curr);
		while (ctime.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
			ctime.add(Calendar.DAY_OF_WEEK, 1);
		}
		return ctime.getTime();
	}

	public static String parseSecondDate2(Date date) {
		String back = "";
		back = fmttime.format(date);
		return back;
	}

	public static Date parseTimeDate(String sDate) {
		Date back;
		try {
			back = fmttime.parse(sDate);
		} catch (ParseException e) {
			back = defDate;
		}
		return back;
	}

	public static Date parseTimeDate(String sDate, DateFormat fmttime) {
		Date back;
		try {
			back = fmttime.parse(sDate);
		} catch (ParseException e) {
			back = defDate;
		}
		return back;
	}

	public static Date getNextFriday(Date curr) {
		Calendar ctime = Calendar.getInstance();
		ctime.setTime(curr);
		if (ctime.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
			ctime.add(Calendar.DAY_OF_WEEK, 7);
		} else
			while (ctime.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
				ctime.add(Calendar.DAY_OF_WEEK, 1);
			}
		return ctime.getTime();
	}

	public static Date getCurrFriday(Date curr) {
		Calendar ctime = Calendar.getInstance();
		ctime.setTime(curr);
		if (ctime.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
			ctime.add(Calendar.DAY_OF_WEEK, -1);
		} else if (ctime.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
			ctime.add(Calendar.DAY_OF_WEEK, -2);
		} else
			while (ctime.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
				ctime.add(Calendar.DAY_OF_WEEK, 1);
			}
		return ctime.getTime();
	}

	public static String getToday() {
		String back = fmt.format(new Date());
		return back;
	}

	public static Date getDate(int diff) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, diff);
		return c.getTime();
	}

	public static Date parseLongDate(String sDate) {
		Date back = defDate;
		try {
			back = new Date(Long.parseLong(sDate));
		} catch (Exception e) {
			back = defDate;
		}
		return back;
	}

	public static String getDateString(int diff) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, diff);
		Date day = c.getTime();
		String back = fmt.format(day);
		return back;
	}

	public static String getDayString(Date date) {
		String back = fmt.format(date);
		return back;
	}

	public static Date parseDate(String sDate) {
		Date back = defDate;
		sDate = sDate.trim();
		try {
			if (sDate.contains("-")) {
				if (sDate.split("-").length == 2)
					sDate = sDate + "-01";
				back = fmt.parse(sDate);
			} else if (sDate.contains("/")) {
				back = fmt2.parse(sDate);
			} else if (sDate.length() == 8) {
				back = noneFmt.parse(sDate);
			}
		} catch (ParseException e) {
			back = defDate;
		}
		return back;
	}

	public static String parseSecondDate(Date date) {
		String back = "";
		back = secondFmt.format(date);
		return back;
	}

	public static Date parseCNDate(String sDate) {
		Date back;
		try {
			back = cnFmt.parse(sDate);
		} catch (ParseException e) {
			back = defDate;
		}
		return back;
	}

	public static int getDateDiff(Date d1, Date d2) {
		try {
			d1 = fmt.parse(fmt.format(d1));
			d2 = fmt.parse(fmt.format(d2));
			Calendar cal = Calendar.getInstance();
			cal.setTime(d1);
			long time1 = cal.getTimeInMillis();
			cal.setTime(d2);
			long time2 = cal.getTimeInMillis();
			long between_days = (time2 - time1) / (1000 * 3600 * 24);
			return Integer.parseInt(String.valueOf(between_days));
		} catch (ParseException e) {
			return 0;
		}
	}

	public static Date getDate(String year, String month, String day) {
		Calendar ctime = Calendar.getInstance();
		int iYear = 1970;
		int iMonth = 0;
		int iDay = 1;
		try {
			iYear = Integer.parseInt(year);
			iMonth = Integer.parseInt(month);
			iDay = Integer.parseInt(day);
		} catch (Exception e) {
		}
		ctime.set(Calendar.YEAR, iYear);
		ctime.set(Calendar.MONTH, iMonth);
		ctime.set(Calendar.DAY_OF_MONTH, iDay);
		return ctime.getTime();
	}

	/**************************************/

	public static void main(String[] args) {
		Date a = parseLongDate("1294444800000");
		Random rand = new Random(new Date().getTime());
		logger.info(getDateDiff(parseDate("2017-02-17"), getCurrFriday(new Date())));// "2017-02-10
																						// 00:00:00"
	}
}
