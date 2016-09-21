package com.ecomindo.common.util;

import java.math.BigDecimal;

public class StringFormatUtil implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8358040765093740493L;

	public String fieldFixedConverter(Boolean isNumber, String value, int length) throws Exception {
		try {

			String data = "";

			if (isNumber) {
				data = fixedToBigDecimal(value == null ? "0" : value, length);
			} else {
				data = fixedToString(value == null ? "" : value, length);
			}

			return data;

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public static String fixFormat(Boolean isNumber, int maxLenght, String value, char blankChar) {
		int toFix = maxLenght - value.trim().length();
		String result = "";
		String forFix = "";
		if (isNumber) {
			for (int i = 0; i < toFix; i++) {
				forFix += blankChar;
			}
			result = forFix + value.trim();
		} else {
			for (int i = 0; i < toFix; i++) {
				forFix += blankChar;
			}
			result = value.trim() + forFix;
		}
		return result;
	}

	public String fixedToString(String pString, int lenght) {

		for (int i = pString.length(); i < lenght; i++)
			pString += " ";
		return pString;
	}

	public String fixedToBigDecimal(String pvalue, int lenght) {
		BigDecimal value = new BigDecimal(pvalue);

		String numberAsString = String.format("%0" + (lenght - value.toString().length()) + "d%s", 0, value.toString());
		return numberAsString;
	}

}
