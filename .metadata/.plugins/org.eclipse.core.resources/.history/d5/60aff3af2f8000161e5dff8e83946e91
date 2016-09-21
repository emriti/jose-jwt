package com.ecomindo.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 * @author irfan.farid
 */
public class DoubleUtil {

	public static final DecimalFormat DEFAULT_DECIMAL_FORMAT = new DecimalFormat("#.00##################");

	/**
	 * add
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static double add(double param1, double param2) {
		Locale.setDefault(Locale.US);

		double result = 0;
		BigDecimal bd1 = new BigDecimal(DEFAULT_DECIMAL_FORMAT.format(param1));
		BigDecimal bd2 = new BigDecimal(DEFAULT_DECIMAL_FORMAT.format(param2));
		result = bd1.add(bd2).doubleValue();
		return result;
	}

	/**
	 * subtract
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static double subtract(double param1, double param2) {
		Locale.setDefault(Locale.US);

		double result = 0;
		BigDecimal bd1 = new BigDecimal(DEFAULT_DECIMAL_FORMAT.format(param1));
		BigDecimal bd2 = new BigDecimal(DEFAULT_DECIMAL_FORMAT.format(param2));
		result = bd1.subtract(bd2).doubleValue();
		return result;
	}

	/**
	 * multiply
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static double multiply(double param1, double param2) {
		Locale.setDefault(Locale.US);

		double result = 0;
		BigDecimal bd1 = new BigDecimal(DEFAULT_DECIMAL_FORMAT.format(param1));
		BigDecimal bd2 = new BigDecimal(DEFAULT_DECIMAL_FORMAT.format(param2));
		result = bd1.multiply(bd2).doubleValue();
		return result;
	}

	/**
	 * divide
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static double divide(double param1, double param2) {
		Locale.setDefault(Locale.US);

		double result = 0;
		BigDecimal bd1 = new BigDecimal(DEFAULT_DECIMAL_FORMAT.format(param1));
		BigDecimal bd2 = new BigDecimal(DEFAULT_DECIMAL_FORMAT.format(param2));
		result = bd1.divide(bd2, 20, BigDecimal.ROUND_HALF_UP).doubleValue();
		return result;
	}

	/**
	 * compareTo -1, 0, or 1 as this param1 is numerically less than, equal to,
	 * or greater than param2
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static int compareTo(double param1, double param2) {
		Locale.setDefault(Locale.US);

		int result = 0;
		BigDecimal bd1 = new BigDecimal(DEFAULT_DECIMAL_FORMAT.format(param1));
		BigDecimal bd2 = new BigDecimal(DEFAULT_DECIMAL_FORMAT.format(param2));
		result = bd1.compareTo(bd2);
		return result;
	}

	/**
	 * return true if param1 = param2, false otherwise
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static boolean isEqual(double param1, double param2) {
		return (compareTo(param1, param2) == 0);
	}

	/**
	 * return true if param1 < param2, false otherwise
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static boolean isLessThan(double param1, double param2) {
		return (compareTo(param1, param2) == -1);
	}

	/**
	 * return true if param1 > param2, false otherwise
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static boolean isGreaterThan(double param1, double param2) {
		return (compareTo(param1, param2) == 1);
	}

	/**
	 * return true if param1 <= param2, false otherwise
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static boolean isLessOrEqual(double param1, double param2) {
		return ((isLessThan(param1, param2)) || (isEqual(param1, param2)));
	}

	/**
	 * return true if param1 >= param2, false otherwise
	 * 
	 * @param param1
	 * @param param2
	 * @return
	 */
	public static boolean isGreaterOrEqual(double param1, double param2) {
		return ((isGreaterThan(param1, param2)) || (isEqual(param1, param2)));
	}

	/**
	 * rounding source value to specified decimal places using specified
	 * rounding method
	 * 
	 * @param param1:
	 *            source value
	 * @param param2:
	 *            decimal places
	 * @param param3:
	 *            rounding method (0: round-down, 1: round-up, 2: round normal,
	 *            3: round-down if less or equal than 0.5, round-up if greater
	 *            than 0.5)
	 * @return
	 */
	public static double round(double param1, int param2, int param3) {
		Locale.setDefault(Locale.US);

		double power = Math.pow(10, param2);
		double amount = multiply(param1, power);

		switch (param3) {
		case 0:
			amount = Math.floor(amount);
			break;
		case 1:
			amount = Math.ceil(amount);
			break;
		case 2:
			amount = Math.round(amount);
			break;
		case 3:
			String strAmount = String.valueOf(amount);
			if (isLessThan(
					Double.parseDouble(
							"0" + String.valueOf(strAmount.substring(strAmount.indexOf("."), strAmount.length()))),
					0.51)) {
				amount = Math.floor(amount);
			} else {
				amount = Math.ceil(amount);
			}
			break;
		}
		amount = divide(amount, power);
		return amount;
	}

	/**
	 * check if numbers' precision and scale valid
	 * 
	 * @param number
	 *            : number to be checked
	 * @param precision
	 *            precision (total number of digits)
	 * @param scale
	 *            scale (number of digits to the right of the decimal point)
	 * @return valid/not
	 */
	public static boolean isNumberValidPrecisionScale(String number, int precision, int scale) {
		if (scale > precision) {
			return false;
		}

		String[] numberArr = number.trim().split("\\.");
		if (numberArr.length > 1) {
			if ((numberArr[0].length() > (precision - scale)) || (numberArr[1].length() > scale)) {
				return false;
			}
		} else {
			if (numberArr[0].length() > (precision - scale)) {
				return false;
			}
		}
		return true;
	}

}
