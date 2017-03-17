package com.cover.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

public class CFPropertiesUtil {
	private static Logger logger = Logger.getLogger(CFPropertiesUtil.class);
	private Properties props;
	private File file = null;
	private static CFPropertiesUtil personalConfig;
	private static CFPropertiesUtil commonConfig;
	private static CFPropertiesUtil siteConfig;

	public CFPropertiesUtil(URL f) {
		file = new File(CFPathTransform.path(f.getFile()));
		props = new Properties();
		try {
			InputStream in = f.openStream();
			props.load(in);
		} catch (FileNotFoundException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		}
	}

	public CFPropertiesUtil(String fileName) {
		URL url = getClass().getResource(fileName);
		file = new File(CFPathTransform.path(url.getFile()));
		props = new Properties();
		try {
			InputStream in = url.openStream();
			props.load(in);
		} catch (FileNotFoundException e) {
			logger.error("config file not found :" + e);
		} catch (IOException e) {
			logger.error("config file read error :" + e);
		}

	}

	public static CFPropertiesUtil getPersonalConfig() {
		if (personalConfig == null) {
			personalConfig = new CFPropertiesUtil("/personal-config.properties");
		}
		return personalConfig;
	}

	public static CFPropertiesUtil getCommonConfig() {
		if (commonConfig == null) {
			commonConfig = new CFPropertiesUtil("/common-config.properties");
		}
		return commonConfig;
	}

	public static CFPropertiesUtil getSiteConfig() {
		if (siteConfig == null) {
			siteConfig = new CFPropertiesUtil("/siteConfig.properties");
		}
		return siteConfig;
	}

	public Properties getProperties() {
		return props;
	}

	public void removeProperty(String Key) {
		try {
			props.remove(Key);
			props.store(new FileOutputStream(file), "");
		} catch (FileNotFoundException e) {
			logger.error("config file not found :" + e);

		} catch (IOException e) {
			logger.error("config file read error :" + e);
		}
	}

	public void removePropertys(Set<String> set) {
		try {
			for (Object key : set)
				props.remove(key);
			props.store(new FileOutputStream(file), "");
		} catch (FileNotFoundException e) {
			logger.error("config file not found :" + e);

		} catch (IOException e) {
			logger.error("config file read error :" + e);
		}
	}

	public void saveProperty(String Key, String value) {
		try {
			props.setProperty(Key, value);
			props.store(new FileOutputStream(file), "");
		} catch (FileNotFoundException e) {
			logger.error("config file not found :" + e);
		} catch (IOException e) {
			logger.error("config file read error :" + e);
		}
	}

	public void removeAll() {
		props = new Properties();
		try {
			props.store(new FileOutputStream(file), "");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e);
			e.printStackTrace();
		}
	}

	public void saveProperty() {
		try {
			props.store(new FileOutputStream(file), "");
		} catch (FileNotFoundException e) {
			logger.error("config file not found :" + e);

		} catch (IOException e) {
			logger.error("config file read error :" + e);

		}
	}

	public void savePropertys(Hashtable<String, String> ht) {
		try {
			for (Iterator it = ht.keySet().iterator(); it.hasNext();) {
				String key = (String) it.next();
				String value = ht.get(key);
				props.setProperty(key, value);
			}
			props.store(new FileOutputStream(file), "");
		} catch (FileNotFoundException e) {
			logger.error("config file not found :" + e);
		} catch (IOException e) {
			logger.error("config file read error :" + e);
		}
	}
}
