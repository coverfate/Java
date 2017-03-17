/**   
 * 
 * @Title: CFRegExpUtil.java 
 * @Prject: HCCRM2
 * @Package: com.cover.utils 
 * @Description: TODO
 * @author: chenchao   
 * @date: 2016年3月29日 下午5:49:31 
 * @version: V1.0   
 */
package com.cover.utils;

import java.util.regex.Pattern;

/**
 * @ClassName: CFRegExpUtil
 * @Description: TODO
 * @author: chenchao
 * @date: 2016年3月29日 下午5:49:31
 */
public class CFRegExpUtil {
	public static boolean checkContain(String totle, String key) {
		return Pattern.compile(totle).matcher(key).find();
	}

}
