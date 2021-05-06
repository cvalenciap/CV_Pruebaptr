package pe.com.sedapal.common.core.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtils {
	
	private HashUtils() {
	    throw new IllegalStateException("Utility class");
	}

	public static String md5(String str) {
		byte[] bytes = DigestUtils.md5(str);
		return bytesToString(bytes);
	}

	public static String sha256(String str) {
		byte[] bytes = DigestUtils.sha256(str);
		return bytesToString(bytes);
	}


	public static String sha1(String str) {
		byte[] bytes = DigestUtils.sha1(str);
		return bytesToString(bytes);
	}

	public static String bytesToString(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte _byte : bytes) {
			sb.append(Integer.toString((_byte & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}
}
