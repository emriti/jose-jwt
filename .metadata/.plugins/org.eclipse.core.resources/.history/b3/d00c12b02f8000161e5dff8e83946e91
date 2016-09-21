package com.ecomindo.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Settings {

	private static final String configDirectory = "user.home";
	private static final String configFileName = "/config.properties";

	public static String getPropertiesByName(String propertiesName) throws FileNotFoundException, IOException {
		String result = "";
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream(System.getProperty(configDirectory) + configFileName));
			result = (prop.get(propertiesName) != null ? prop.get(propertiesName).toString() : "");
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return result;
	}
}
