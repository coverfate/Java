/**   
 * 
 * @Title: CoverProxyUtil.java 
 * @Prject: HcDBCrawlNew
 * @Package: com.cover.utils 
 * @Description: TODO
 * @author: chenchao   
 * @date: 2016年6月23日 下午1:25:18 
 * @version: V1.0   
 */
package com.cover.utils;

import java.net.InetSocketAddress;
import java.net.Proxy;

import org.apache.log4j.Logger;

/**
 * @ClassName: CoverProxyUtil
 * @Description: TODO
 * @author: chenchao
 * @date: 2016年6月23日 下午1:25:18
 */
public class CFProxyUtil {
	private static Logger logger = Logger.getLogger(CFProxyUtil.class);

	public static boolean checkProxyUsable(String ip, int port) {

		try {
			boolean flag = false;
			String url = "http://1212.ip138.com/ic.asp";
			InetSocketAddress addr = null;
			addr = new InetSocketAddress(ip, port);
			Proxy proxy = new Proxy(Proxy.Type.HTTP, addr);
			String jsonData = CFHttpUtil.getJsonString(url, proxy);
			int l = jsonData.indexOf("[");
			int r = jsonData.indexOf("]");
			if (l > 0 && r > l) {
				jsonData = jsonData.substring(l + 1, r);
				if (jsonData.equals(ip))
					flag = true;
			}
			logger.info(jsonData);
			return flag;
		} catch (Exception e) {
		}
		return false;
	}

	public static void main(String[] args) {
		logger.info(checkProxyUsable("101.226.249.237", 80));
	}
}
