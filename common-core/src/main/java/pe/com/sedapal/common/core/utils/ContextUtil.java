package pe.com.sedapal.common.core.utils;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.sedapal.common.AppContextManager;

public class ContextUtil {
	
	private ContextUtil() {
	    throw new IllegalStateException("Utility class");
	}
	
	private static final Logger log = LoggerFactory.getLogger(ContextUtil.class);

    private static ServletContext varServletContext;

    public static String getBaseApiPath() {
        return getContext() + "/api";
    }

    public static String getContext() {
        try {
            if (varServletContext == null) {
            	varServletContext = AppContextManager.getBean(ServletContext.class);
            }
            return varServletContext.getContextPath().equals("/") ? "" : varServletContext.getContextPath();
        } catch (Exception e) {
        	log.error(e.getMessage());
        }
        return "/";
    }

    public static String getPagePath(String path) {
        return getContext() + path;
    }

    public static String getCookieContextPath() {
        return getContext().equals("") ? "/" : getContext();
    }
}
