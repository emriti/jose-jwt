package com.ecomindo.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteUtil {

	public static byte[] inputStreamToByte(InputStream inputStream) throws IOException {
		int len;
		int size = 1024;
		byte[] buf = null;

		try {
			size = inputStream.available();
			if (inputStream instanceof ByteArrayInputStream) {
				buf = new byte[size];
				len = inputStream.read(buf, 0, size);
			} else {
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				buf = new byte[size];
				while ((len = inputStream.read(buf, 0, size)) != -1)
					bos.write(buf, 0, len);
				buf = bos.toByteArray();
			}
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
		return buf;
	}
}
