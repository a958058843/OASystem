package com.zq.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BasePropertyKeyValue {
	private static BasePropertyKeyValue keyValueInstance;
	// 读取文件
	private Properties properties;

	private BasePropertyKeyValue() {
		properties = new Properties();
		InputStream iStream = BasePropertyKeyValue.class.getClassLoader().getResourceAsStream("mail.properties");
		try {
			properties.load(iStream);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (iStream != null) {
				try {
					iStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static BasePropertyKeyValue getInstance() {
		if (keyValueInstance == null) {
			keyValueInstance = new BasePropertyKeyValue();
		}
		return keyValueInstance;
	}

	// 根据key 得到value
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
