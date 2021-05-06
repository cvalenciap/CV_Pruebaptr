package pe.com.sedapal.common.core.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class EncDecUtils {
	
	private EncDecUtils() {
	    throw new IllegalStateException("Utility class");
	}
	
	private static final Logger logger = LoggerFactory
			.getLogger(EncDecUtils.class);

    public static String encode(String s) {
        if (!StringUtils.isEmpty(s)) {
            try {
                return URLEncoder.encode(s, ConstantsCommon.UTF8);
            } catch (UnsupportedEncodingException e) {
            	logger.error(e.getMessage(), e);
            }
        }

        return "";
    }

    public static String decode(String s) {
        if (!StringUtils.isEmpty(s)) {
            try {
                return URLDecoder.decode(s, ConstantsCommon.UTF8);
            } catch (UnsupportedEncodingException e) {
            	logger.error(e.getMessage(), e);
            }
        }

        return "";
    }


    public static String encodeDownloadFileName(String s) {
        try {
            return URLEncoder.encode(s, ConstantsCommon.UTF8).replaceAll("\\+", "%20");
        } catch (Exception e) {
            // ignore
        }
        return "";
    }
}
