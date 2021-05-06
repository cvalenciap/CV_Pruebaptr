package pe.com.sedapal.common.core.utils;


import java.net.URLEncoder;

public class EncodeUtils {
	
	private EncodeUtils() {
	    throw new IllegalStateException("Utility class");
	}

	public static String encodeDownloadFileName(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
		} catch (Exception e) {
			// ignore
		}
		return "";
	}
}

