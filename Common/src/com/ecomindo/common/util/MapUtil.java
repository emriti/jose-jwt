package com.ecomindo.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

public class MapUtil {

	public static Map<String, Object> introspect(Object obj) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		BeanInfo info = Introspector.getBeanInfo(obj.getClass());
		for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
			Method reader = pd.getReadMethod();
			if (reader != null)
				result.put(pd.getName(), reader.invoke(obj));
		}
		return result;
	}

	public static Map<String, Object> getMap(Object o) throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> result = new HashMap<String, Object>();
		Field[] declaredFields = o.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			result.put(field.getName(), field.get(o));
		}
		return result;
	}

	public static Object getObject(Map<String, Object> map, String key, boolean allowNull) throws Exception {
		Object obj = null;

		// Check object map null or empty
		if (map == null || map.isEmpty() || map.size() == 0) {
			return null;
		}

		// try to get object
		obj = map.get(key);

		if (obj == null && !allowNull) {
			// check if data allowing null value
			throw new Exception("Value of " + key + " cannot null");

		}

		return obj;
	}

	public static String getStringObject(Map<String, Object> map, String key, boolean allowNull) throws Exception {
		String result = "";

		Object obj = getObject(map, key, allowNull);
		try {
			if (obj != null)
				result = obj.toString();
		} catch (Exception ex) {
			throw new Exception(String.format("Cannot parse %s into string.", (String) obj));
		}

		return result;
	}

	public static Boolean getBooleanObject(Map<String, Object> map, String key, boolean allowNull) throws Exception {
		Boolean result = null;

		Object obj = getObject(map, key, allowNull);
		try {
			obj = obj == null || "".equals(obj.toString()) ? null : obj;
			if (obj != null) {

				if (obj.getClass() == Boolean.class) {
					result = (Boolean) obj;
				} else if (obj.getClass() == String.class) {
					String strObj = (String) obj;
					if ("true".equals(strObj.toLowerCase())) {
						result = true;
					} else if ("false".equals(strObj.toLowerCase())) {
						result = false;
					} else {
						throw new Exception("Cannot parse String to Boolean");
					}
				} else if (obj.getClass() == Integer.class) {
					result = ((Integer) obj).equals(0) ? true : false;
				}
			}
		} catch (Exception ex) {
			throw new Exception(String.format("Cannot parse %s into integer.", (String) obj));
		}

		return result;
	}

	public static Integer getIntObject(Map<String, Object> map, String key, boolean allowNull) throws Exception {
		Integer result = null;

		Object obj = getObject(map, key, allowNull);
		try {
			obj = obj == null || "".equals(obj.toString()) ? null : obj;
			if (obj != null) {

				if (obj.getClass() == Integer.class) {
					result = (Integer) obj;
				} else if (obj.getClass() == Long.class) {
					result = ((Long) obj).intValue();
				} else {
					result = Integer.parseInt((String) obj);
				}
			}
		} catch (Exception ex) {
			throw new Exception(String.format("Cannot parse %s into integer.", (String) obj));
		}

		return result;
	}

	public static Long getLongObject(Map<String, Object> map, String key, boolean allowNull) throws Exception {
		Long result = null;

		Object obj = getObject(map, key, allowNull);
		try {
			obj = obj == null || "".equals(obj.toString()) ? null : obj;
			if (obj != null) {
				if (obj.getClass() == Long.class) {
					result = (long) obj;
				} else if (obj.getClass() == Integer.class) {
					result = ((Integer) obj).longValue();
				} else {
					result = Long.parseLong((String) obj);
				}
			}
		} catch (Exception ex) {
			throw new Exception(String.format("Cannot parse %s into long.", (String) obj));
		}

		return result;
	}

	public static BigDecimal getBigDecimalObject(Map<String, Object> map, String key, boolean allowNull)
			throws Exception {
		BigDecimal result = null;

		Object obj = getObject(map, key, allowNull);
		try {
			if (obj != null) {
				int intVal = 0;
				long longVal = 0;

				if (obj.getClass() == Integer.class) {
					intVal = (int) obj;
					longVal = Long.parseLong(String.valueOf(intVal));
				} else if (obj.getClass() == Long.class) {
					longVal = (long) obj;
				} else if (obj.getClass() == String.class) {
					longVal = Long.parseLong((String) obj);
				} else {
					longVal = Long.parseLong((String) obj);
				}

				// long longVal = Long.parseLong((String) obj);
				result = BigDecimal.valueOf(longVal);
			}

		} catch (Exception ex) {
			throw new Exception(String.format("Cannot parse %s into Big Decimal.", (String) obj));
		}

		return result;
	}

	public static Date getDateObject(Map<String, Object> map, String key, boolean allowNull) throws Exception {
		Date result = null;
		Object obj = getObject(map, key, allowNull);
		try {
			if (obj != null) {
				String iso8061 = (String) obj;

				// Calendar calendar = GregorianCalendar.getInstance();
				// String s = iso8061.replace("Z", "+00:00");
				//
				// try {
				// s = s.substring(0, 22) + s.substring(23); // to get rid of
				// the ":"
				// } catch (IndexOutOfBoundsException e) {
				// throw new ParseException("Invalid length", 0);
				// }
				// result = new
				// SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(s);

				// Use Joda
				DateTimeZone jakartaTimeZone = DateTimeZone.forID("Asia/Jakarta");
				DateTime dateTimeInJakarta = new DateTime(iso8061, jakartaTimeZone);
				result = dateTimeInJakarta.toDate();

				// Use Java.Time in java 1.8
				// ZonedDateTime zdt = ZonedDateTime.parse(iso8061);
				// result = Date.from(zdt.toInstant());
			}
		} catch (Exception ex) {
			throw new Exception(String.format("Cannot parse %s into date.", (String) obj), ex);
		}

		return result;
	}

	public static List<Long> getListLongObject(Map<String, Object> map, String key, boolean allowNull)
			throws Exception {
		List<Long> listResult = null;

		Object object = getObject(map, key, allowNull);
		try {
			if (object != null) {
				listResult = new ArrayList<Long>();
				List listObj = (List) object;
				for (int i = 0; i < listObj.size(); i++) {
					Object obj = (Object) listObj.get(i);
					long result = 0;
					if (obj.getClass() == Long.class) {
						result = (long) obj;
					} else if (obj.getClass() == Integer.class) {
						result = ((Integer) obj).longValue();
					} else {
						result = Long.parseLong((String) obj);
					}
					listResult.add(result);
				}

				if (listResult.size() == 0)
					listResult = null;

			}

		} catch (Exception ex) {
			throw new Exception(String.format("Cannot parse %s into Big Decimal.", (String) object));
		}

		return listResult;
	}

}
