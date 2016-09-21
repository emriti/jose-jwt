package com.ecomindo.common.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class AESencrp {

	private static final String ALGO = "AES/CBC/PKCS5Padding";

	private static Key key;
	private static Cipher decryptor;
	private static Cipher encryptor;
	private static String keyValueStr = "testkey1testkey2";
	private static String ivStr = "12345678901234567890123456789012";

	public static void init() throws Exception {

		key = generateKey();
		encryptor = Cipher.getInstance(ALGO);

		// with apache Hex library
		// IvParameterSpec iv = new
		// IvParameterSpec(Hex.decodeHex(ivStr.toCharArray()));

		// without apache Hex library
		IvParameterSpec iv = new IvParameterSpec(DatatypeConverter.parseHexBinary(ivStr));

		decryptor = Cipher.getInstance(ALGO);
		encryptor.init(Cipher.ENCRYPT_MODE, key, iv);
		decryptor.init(Cipher.DECRYPT_MODE, key, iv);
	}

	public static String encrypt(String Data) throws Exception {
		init();
		byte[] encVal = encryptor.doFinal(Data.getBytes());

		// with base64 encoding
		// String encryptedValue = new BASE64Encoder().encode(encVal);

		// with apache Hex library
		// String encryptedValue = Hex.encodeHexString(encVal);

		// without apache Hex library
		String encryptedValue = DatatypeConverter.printHexBinary(encVal);
		return encryptedValue.toUpperCase();
	}

	public static String decrypt(String encryptedData) throws Exception {
		// with base64 encoding
		// byte[] decordedValue = new
		// BASE64Decoder().decodeBuffer(encryptedData);

		// with apache Hex library
		// byte[] decordedValue =
		// Hex.decodeHex(encryptedData.toLowerCase().toCharArray());

		// without apache Hex library
		init();
		byte[] decordedValue = DatatypeConverter.parseHexBinary(encryptedData.toLowerCase());
		byte[] decValue = decryptor.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	private static Key generateKey() throws Exception {
		if (key == null)
			key = new SecretKeySpec(keyValueStr.getBytes(), "AES");
		return key;
	}

	public static void main(String[] args) throws Exception {

		init();
		String password = "Ika Yuni Lestari";
		String passwordEnc = AESencrp.encrypt(password);
		String passwordDec = AESencrp.decrypt(passwordEnc);

		System.out.println("Plain Text : " + password);
		System.out.println("Encrypted Text : " + passwordEnc);
		System.out.println("Decrypted Text : " + passwordDec);
	}

	public static String getKeyValueStr() {
		return keyValueStr;
	}

	public static void setKeyValueStr(String keyValueStr) {
		AESencrp.keyValueStr = keyValueStr;
	}

	public static String getIvStr() {
		return ivStr;
	}

	public static void setIvStr(String ivStr) {
		AESencrp.ivStr = ivStr;
	}
}