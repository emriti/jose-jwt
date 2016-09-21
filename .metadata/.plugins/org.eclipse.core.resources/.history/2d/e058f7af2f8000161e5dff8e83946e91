package com.ecomindo.common.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

public class ObjectConverterHelper {

	public static String dataHeadertoLine(Class<?> type, String delimiter, List<Integer> columnToSkip)
			throws Exception {
		String delimiterLine = "";
		try {
			Field[] fieldsObj1 = type.getDeclaredFields();
			int i = 1;
			for (Field field : fieldsObj1) {
				if (!columnToSkip.contains(new Integer(i))) {
					String value = field.getName();
					delimiterLine += String.format("%s%s", delimiter, value);
				}
				i++;
			}
		} catch (Exception ex) {
			throw new Exception("Cannot parse data to object.", ex);
		}

		return delimiterLine.substring(1);
	}

	public static String dataHeadertoLine(Class<?> type, String delimiter, int columnToSkip) throws Exception {
		String delimiterLine = "";
		try {
			Field[] fieldsObj1 = type.getDeclaredFields();
			int i = 1;
			for (Field field : fieldsObj1) {
				if (i != columnToSkip) {
					String value = field.getName();
					delimiterLine += String.format("%s%s", delimiter, value);
				}
				i++;
			}
		} catch (Exception ex) {
			throw new Exception("Cannot parse data to object.", ex);
		}

		return delimiterLine.substring(1);
	}

	public static String resultSetToDelimiterDataLine(Class<?> type, ResultSet resultSet, String delimiter,
			List<Integer> columnToSkip) throws Exception {
		String delimiterLine = "";
		try {
			Field[] fieldsObj1 = type.getDeclaredFields();
			int i = 1;
			for (Field field : fieldsObj1) {
				if (!columnToSkip.contains(new Integer(i))) {
					String value = field.getName();
					String fieldTypeName = field.getType().getName().toString();

					Object itemObj1 = resultSet.getObject(i);

					switch (fieldTypeName) {
					case "java.lang.String":
						if (itemObj1 == null) {
							value = "";
						} else {
							value = "\"" + itemObj1.toString() + "\"";
						}

						break;
					case "int":
						if (itemObj1 == null) {
							value = "";
						} else {
							value = itemObj1.toString();
						}
						break;
					case "java.math.BigDecimal":
						if (itemObj1 == null) {
							value = "";
						} else {
							value = itemObj1.toString();
						}
						break;
					case "java.util.Date":
						if (itemObj1 == null) {
							value = "";
						} else {
							value = DateUtil.toFormat((Date) itemObj1, "yyyyMMdd");
						}
						break;
					default:
						// TODO: add another type, ex : int, double
						throw new Exception("field not supported");
					}
					delimiterLine += String.format("%s%s", delimiter, value);
				}
				i++;
			}
		} catch (Exception ex) {
			throw new Exception("Cannot parse data to object.", ex);
		}
		return delimiterLine.substring(1);
	}

	public static String resultSetToDelimiterDataLine(Class<?> type, ResultSet resultSet, String delimiter,
			int columnToSkip) throws Exception {
		String delimiterLine = "";
		try {
			Field[] fieldsObj1 = type.getDeclaredFields();
			int i = 1;
			for (Field field : fieldsObj1) {
				if (i != columnToSkip) {
					String value = field.getName();
					String fieldTypeName = field.getType().getName().toString();

					Object itemObj1 = resultSet.getObject(i);

					switch (fieldTypeName) {
					case "java.lang.String":
						if (itemObj1 == null) {
							value = "";
						} else {
							value = "\"" + itemObj1.toString() + "\"";
						}

						break;
					case "int":
						if (itemObj1 == null) {
							value = "";
						} else {
							value = itemObj1.toString();
						}
						break;
					case "java.math.BigDecimal":
						if (itemObj1 == null) {
							value = "";
						} else {
							value = itemObj1.toString();
						}
						break;
					case "java.util.Date":
						if (itemObj1 == null) {
							value = "";
						} else {
							value = DateUtil.toFormat((Date) itemObj1, "yyyyMMdd");
						}
						break;
					default:
						// TODO: add another type, ex : int, double
						throw new Exception("field not supported");
					}
					delimiterLine += String.format("%s%s", delimiter, value);
				}
				i++;
			}
		} catch (Exception ex) {
			throw new Exception("Cannot parse data to object.", ex);
		}
		return delimiterLine.substring(1);
	}

	public static String dataRowToDelimiterLine(Object dr, String delimiter) throws Exception {
		String delimiterLine = "";

		// Initial object
		Field[] fieldsObj1 = dr.getClass().getDeclaredFields();

		try {
			for (Field field : fieldsObj1) {

				field.setAccessible(true);

				Object itemObj1 = field.get(dr);
				String value = "";
				if (itemObj1 != null) {
					value = itemObj1.toString();
				} else {
					value = "";
				}

				delimiterLine += String.format("%s%s", delimiter, value);
			}
		} catch (Exception ex) {
			throw new Exception("Cannot parse data to object.", ex);
		}
		return delimiterLine.substring(1);

	}

	public static String dataRowToDelimiterDataLine(Object dr, String delimiter) throws Exception {
		String delimiterLine = "";

		// Initial object
		Field[] fieldsObj1 = dr.getClass().getDeclaredFields();

		try {
			for (Field field : fieldsObj1) {

				field.setAccessible(true);

				Object itemObj1 = field.get(dr);
				String fieldTypeName = field.getType().getName().toString();
				String value = "";

				switch (fieldTypeName) {
				case "java.lang.String":
					if (itemObj1 == null) {
						value = "";
					} else {
						value = "\"" + itemObj1.toString() + "\"";
					}

					break;
				case "int":
					if (itemObj1 == null) {
						value = "";
					} else {
						value = itemObj1.toString();
					}
					break;
				case "java.math.BigDecimal":
					if (itemObj1 == null) {
						value = "";
					} else {
						value = itemObj1.toString();
					}
					break;
				case "java.util.Date":
					if (itemObj1 == null) {
						value = "";
					} else {
						value = DateUtil.toFormat((Date) itemObj1, "yyyyMMdd");
					}
					break;
				default:
					// TODO: add another type, ex : int, double
					throw new Exception("field not supported");
				}

				delimiterLine += String.format("%s%s", delimiter, value);
			}
		} catch (Exception ex) {
			throw new Exception("", ex);
		}
		return delimiterLine.substring(1);
	}
}
