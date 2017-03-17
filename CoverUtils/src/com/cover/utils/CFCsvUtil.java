/**   
 * 
 * @Title: CoverCsvUtil.java 
 * @Prject: CralwSmwang
 * @Package: com.cover.utils 
 * @Description: TODO
 * @author: chenchao   
 * @date: 2016年6月24日 上午11:18:15 
 * @version: V1.0   
 */
package com.cover.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @ClassName: CoverCsvUtil
 * @Description: TODO
 * @author: chenchao
 * @date: 2016年6月24日 上午11:18:15
 */
public class CFCsvUtil {
	private static Logger logger = Logger.getLogger(CFCsvUtil.class);

	public static boolean exportCsv(File file, List<String> dataList) {
		try {

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GBK"));// 附加
			// 添加新的数据行
			for (String s : dataList) {
				bw.write(s);
				bw.newLine();
			}

			bw.close();
			// logger.debug("\n wirite file:" + file);
		} catch (FileNotFoundException e) {
			logger.error(e.toString(), e.fillInStackTrace());
			return false;
		} catch (IOException e) {
			logger.error(e.toString(), e.fillInStackTrace());
			return false;
		}

		return true;
	}

	public static boolean exportFile(File file, String data) {
		try {

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GBK"));// 附加
			// 添加新的数据行
			bw.write(data);
			bw.close();
		} catch (FileNotFoundException e) {
			logger.error(e.toString(), e.fillInStackTrace());
			return false;
		} catch (IOException e) {
			logger.error(e.toString(), e.fillInStackTrace());
			return false;
		}

		return true;
	}

	/**
	 * 导入
	 * 
	 * @param file
	 *            csv文件(路径+文件)
	 * @return
	 */
	public static List<String> importCsv(File file) {
		List<String> list = new ArrayList<String>();

		if (file != null && file.exists() && file.isFile()) {
			try {
				BufferedReader bufferdReader = new BufferedReader(
						new InputStreamReader(new FileInputStream(file), "GBK"));
				String strTem = "";
				while ((strTem = bufferdReader.readLine()) != null) {
					list.add(strTem);
				}
				logger.debug("readfile:" + file);
			} catch (UnsupportedEncodingException e) {
				logger.error(e.toString(), e.fillInStackTrace());
			} catch (FileNotFoundException e) {
				logger.error(e.toString(), e.fillInStackTrace());
			} catch (IOException e) {
				logger.error(e.toString(), e.fillInStackTrace());
			}
		}
		return list;
	}

	public static void main(String[] args) {
	}

}
