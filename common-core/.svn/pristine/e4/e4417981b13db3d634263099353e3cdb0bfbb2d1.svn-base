package pe.com.sedapal.common.core.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import eu.bitwalker.useragentutils.Browser;

public class HttpUtils {
	
	private HttpUtils() {
	    throw new IllegalStateException("Utility class");
	}

    public static HttpServletRequest getCurrentRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static HttpServletResponse getCurrentResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }

    public static String getJsonContentType(HttpServletRequest request) {
        Browser browser = AgentUtils.getBrowser(request);

        if (browser != null && browser == Browser.IE) {
            return "text/plain; charset=UTF-8";
        }

        return "application/json; charset=UTF-8";
    }

    public static boolean isMultipartFormData(HttpServletRequest request) {
        try {
            return request.getHeader("content-type").contains("multipart");
        } catch (Exception e) {
            // ignore
        }
        return false;
    }

    public static boolean isMultipartFormData() {
        return isMultipartFormData(getCurrentRequest());
    }

    public static String getRemoteAddress() {
        return getRemoteAddress(getCurrentRequest());
    }

    public static String getRemoteAddress(HttpServletRequest request) {
        String clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");

        if (StringUtils.isEmpty(clientIp) || clientIp.equalsIgnoreCase("unknown")) {
            clientIp = request.getHeader("REMOTE_ADDR");
        }

        if (StringUtils.isEmpty(clientIp) || clientIp.equalsIgnoreCase("unknown")) {
            clientIp = request.getRemoteAddr();
        }

        return clientIp;
    }
}