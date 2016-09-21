package com.ecomindo.common;

import java.lang.reflect.Field;
import java.util.List;

public class Common {

	public static int getCboSelectedIndexItemInList(Object item, List<Object> list, String propertyName)
			throws Exception {
		int result = -1;
		try {

			// get field from item
			Field fItem = item.getClass().getDeclaredField(propertyName);
			fItem.setAccessible(true);
			Object itemValue = fItem.get(item);

			int i = 0;
			for (Object object : list) {
				// get field from list
				Field fObject = object.getClass().getDeclaredField(propertyName);
				fObject.setAccessible(true);
				Object objectValue = fObject.get(object);

				if (itemValue.equals(objectValue)) {
					result = i;
					continue;
				}
				i++;
			}
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	public static Object getCboSelectedItemInList(Object item, List<Object> list, String propertyName)
			throws Exception {
		Object result = null;
		try {
			for (Object object : list) {
				// get field from list
				Field fObject = object.getClass().getDeclaredField(propertyName);
				fObject.setAccessible(true);
				Object objectValue = fObject.get(object);

				// check text equal with item
				if (item.equals(objectValue)) {
					result = object;
					continue;
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return result;
	}

	public static String setStatus(String status) {
		String result = "";
		switch (status) {
		case "PC":
			result = "Menunggu Persetujuan - Baru";
			break;
		case "PD":
			result = "Menunggu Persetujuan - Hapus";
			break;
		case "PE":
			result = "Menunggu Persetujuan - Ubah";
			break;
		case "AC":
			result = "Disetujui - Baru";
			break;
		case "AD":
			result = "Disetujui - Hapus";
			break;
		case "AE":
			result = "Disetujui - Ubah";
			break;
		case "RC":
			result = "Ditolak - Baru";
			break;
		case "RD":
			result = "Ditolak - Hapus";
			break;
		case "RE":
			result = "Ditolak - Ubah";
			break;
		default:
			result = "Status tidak dikenali";
		}

		return result;
	}
}
