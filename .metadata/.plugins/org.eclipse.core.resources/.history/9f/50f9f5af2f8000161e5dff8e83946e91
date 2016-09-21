package com.ecomindo.common.util;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

import com.ecomindo.common.exception.DAOException;

import sun.misc.BASE64Encoder;


public class HashUtil {

	private static Logger logger = Logger.getLogger(HashUtil.class);

	public static String MD5Hash(byte[] bytesInputStream) {
		String result = null;
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");

			byte[] digest = md.digest(bytesInputStream);

			// converting byte array to Hexadecimal String
			StringBuilder sb = new StringBuilder(2 * digest.length);
			for (byte b : digest) {
				sb.append(String.format("%02x", b & 0xff));
			}
			// hold checksum value
			result = sb.toString();

		} catch (Exception e) {
			throw new DAOException(logger, "Failed to get checksum.", e);
		}
		return result;
	}

	public static String hashUserPassword(String username, String password){
		String toBehash = AESencrp.getKeyValueStr() + username.toLowerCase()  + 
				password + AESencrp.getIvStr();
		return MD5Hash(toBehash.getBytes());
	}
	
	public static String SHAHash(String password) {
        String sEncrypted = password;
        if ((password != null) && (password.length() > 0)) {
                try {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(password.getBytes("UTF-8"));
                    sEncrypted = "{SHA}" + (new BASE64Encoder()).encode(md.digest());
                } catch (Exception e) {
                    sEncrypted = null;
                    logger.error(e, e);
                }
        }
        return sEncrypted;
    }
	
}
