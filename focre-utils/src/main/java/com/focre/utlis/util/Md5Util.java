package com.focre.utlis.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @title Md5Util
 * @description [MD5 encrypt]
 * @author ye21st ye21st@gmail.com
 * @date 2019/11/7
 * @time 2:29 PM
 **/
public class Md5Util {

	public static final int ENCRYPT_COUNT = 3;

	/**
	 * @description [Encrypt]
	 * @title Encrypt
	 * @author ye21st ye21st@gmail.com
	 * @date 2019/11/7
	 * @time 2:30 PM
	 * @param source [Encrypt String]
	 * @return java.lang.String
	 **/
	public static String encrypt(String source) {
		return encodeMd5(source.getBytes());
	}

	/**
	 * @Title: Encrypt
	 * @author ye21st ye21st@gmail.com
	 * @date 2019年1月29日 下午5:54:12
	 * @Description: Encrypt
	 * @param count: Encrypt Count
	 * @param source：Encrypt String
	 * @return String
	 */
	public static String encrypt(int count, String source) {
		for (int i = 1; i <= count; i++) {
			source = encodeMd5(source.getBytes());
		}
		return source;
	}

	private static String encodeMd5(byte[] source) {
		try {
			return encodeHex(MessageDigest.getInstance("MD5").digest(source));
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	private static String encodeHex(byte[] bytes) {
		StringBuffer buffer = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buffer.append("0");
			}
			buffer.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buffer.toString();
	}
}
