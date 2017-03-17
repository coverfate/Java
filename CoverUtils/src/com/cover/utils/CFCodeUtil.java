/**   
 * 
 * @Title: CoverCodeUtil.java 
 * @Prject: HcDBCrawlNew
 * @Package: com.cover.utils 
 * @Description: TODO
 * @author: chenchao   
 * @date: 2016年1月28日 下午3:36:20 
 * @version: V1.0   
 */
package com.cover.utils;

import org.apache.log4j.Logger;

/**
 * @ClassName: CoverCodeUtil
 * @Description: TODO
 * @author: chenchao
 * @date: 2016年1月28日 下午3:36:20
 */
public class CFCodeUtil {
	private static Logger logger = Logger.getLogger(CFCodeUtil.class);

	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}

					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	/**** main **************************************************/
	public static void main(String[] args) {
		logger.info(decodeUnicode("\u73af\u7403\u5de8\u5bcc\u7a0b\u5e8f\u5316\u7cbe\u9009\u57fa"));
	}
}
