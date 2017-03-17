package com.cover.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.apache.log4j.Logger;

public class CFPathTransform {

	private static Logger logger = Logger.getLogger(CFPathTransform.class);

	public static String path(String path) {
		try {
			path = URLDecoder.decode(path, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			logger.error("path" + e);
		}
		return path;
	}

}
