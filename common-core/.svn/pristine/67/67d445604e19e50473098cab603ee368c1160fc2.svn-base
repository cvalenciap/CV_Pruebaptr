package pe.com.sedapal.common.core.utils;

import java.util.Locale;

public class DateTimeUtils {
	
	public static final String TIME_FORMAT = "HH:mm:ss";
	
	private DateTimeUtils() {
	    throw new IllegalStateException("Utility class");
	}

    public static String dateFormatFromLocale(Locale locale) {
        if (locale.equals(Locale.US)) {
            return "mm/dd/yyyy";
        }

        return "yyyy-MM-dd";
    }

    public static String dateTimeFormatFromLocale(Locale locale) {
        return dateFormatFromLocale(locale) + " " + TIME_FORMAT;
    }
}
