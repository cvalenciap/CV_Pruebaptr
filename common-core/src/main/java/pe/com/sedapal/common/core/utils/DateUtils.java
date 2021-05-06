package pe.com.sedapal.common.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {

    private DateUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final DateTimeFormatter simpleDateFormat =
            DateTimeFormat.forPattern("yyyy-MM-dd");
    private static final DateTimeFormatter simpleDateTimeFormat =
            DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter simpleTimeFormat = DateTimeFormat.forPattern("HH:mm");
    private static final DateTimeFormatter yyyyMMdd = DateTimeFormat.forPattern("yyyyMMdd");
    private static final DateTimeFormatter yyyy = DateTimeFormat.forPattern("yyyy");
    private static final DateTimeFormatter yyyyMM = DateTimeFormat.forPattern("yyyyMM");
    private static final DateTimeFormatter yyyyMmddHHmmss =
            DateTimeFormat.forPattern("yyyyMMddHHmmss");
    private static final DateTimeFormatter ddMMMyyyyhhmmss12 =
            DateTimeFormat.forPattern("dd MMM yyyy, hh:mm:ss a");
    private static final String TODAY_LAST_TIME = "235959";
    private static final String FMT_YYYYMMDD = "yyyyMMdd";

    private static final DateTimeFormatter ddMMYYYY = DateTimeFormat.forPattern("dd-MM-yyyy");
    private static final DateTimeFormatter hhmmss = DateTimeFormat.forPattern("HH:mm:ss");
    
    private static final DateTimeFormatter ddMMYYYY_SLASH = DateTimeFormat.forPattern("dd/MM/yyyy");
    private static final DateTimeFormatter hhmmss_AM = DateTimeFormat.forPattern("HH:mm:ss a");


    public static Date now() {
        return new Date();
    }

    public static Date parseDate(String applyDt) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return simpleDateFormat.parse(applyDt + "000000");
    }

    public static String formatToDateString(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static String getDateYyyyMmdd() {
        DateTime dateTime = new DateTime(new Date());
        return dateTime.toString(simpleDateFormat);
    }

    public static String getHHmm() {
        DateTime dateTime = new DateTime(new Date());
        return dateTime.toString(simpleTimeFormat);
    }

    public static String getHHmmss() {
        return new SimpleDateFormat("HHmmss").format(new Date());
    }

    public static String getDateYyyyMmddWithoutDash() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FMT_YYYYMMDD);
        return simpleDateFormat.format(new Date());
    }

    public static String getDateYyyyMmWithoutDash() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FMT_YYYYMMDD);
        return simpleDateFormat.format(new Date());
    }

    public static String getTimeHHmmssWithoutDash() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
        return simpleDateFormat.format(new Date());
    }

    public static String yesterDay() {
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, -1);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FMT_YYYYMMDD);
        return simpleDateFormat.format(calendar.getTime());
    }

    public static String getIIACTranDateTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss.SSS");
        return simpleDateFormat.format(new Date());
    }

    public static String getYyyyMMddHHmmssWithDate() {
        DateTime dateTime = new DateTime(new Date());
        return dateTime.toString(simpleDateTimeFormat);
    }

    public static String getYyyyMMddHHmmssWithoutDate() {
        DateTime dateTime = new DateTime(new Date());
        return dateTime.toString(yyyyMmddHHmmss);
    }

    public static String getYyyyMMddWithoutDate() {
        DateTime dateTime = new DateTime(new Date());
        return dateTime.toString(yyyyMMdd);
    }

    public static String dateToddMMyyyyhhmmss12(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(ddMMMyyyyhhmmss12);
    }

    public static String dateToYyyyMmddString(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(simpleDateFormat);
    }

    public static Integer currentYear() {
        DateTime dateTime = new DateTime(new Date());
        return Integer.parseInt(dateTime.toString(yyyy));
    }

    public static String currentMonth() {
        DateTime dateTime = new DateTime(new Date());
        return dateTime.toString(yyyyMM);
    }

    public static Integer currentDay() {
        Calendar calendar = getCalendar();
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static Integer currentHourOfDay() {
        Calendar calendar = getCalendar();
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static Integer nextYear() {
        DateTime dateTime = new DateTime(new Date());
        return Integer.parseInt(dateTime.toString(yyyy)) + 1;
    }

    public static Boolean isValidDate(String input) {
        try {
            yyyyMMdd.parseDateTime(input);
        } catch (Exception e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public static Calendar getCalendar() {
        return Calendar.getInstance(TimeZone.getTimeZone("America/Lima"));
    }

    public static Date toYyyyMMddToDate(String date) {
        DateTime dateTime = yyyyMMdd.parseDateTime(date);
        return dateTime.toDate();
    }

    public static Date toyyyyMMddHHmmssToDate(String date) {
        DateTime dateTime = yyyyMmddHHmmss.parseDateTime(date);
        return dateTime.toDate();
    }

    public static String getTodayLastTime() {
        return TODAY_LAST_TIME;
    }

    public static String formatToDDMMYYYY(Date date) {
        DateTime dt = new DateTime(date);
        return dt.toString(ddMMYYYY);
    }

    public static String formatToHHMMSS(Date date) {
        DateTime dt = new DateTime(date);
        return dt.toString(hhmmss);
    }
    
    public static String formatToDDMMYYYY2(Date date) {
        DateTime dt = new DateTime(date);
        return dt.toString(ddMMYYYY_SLASH);
    }

    public static String formatToHHMMSS2(Date date) {
        DateTime dt = new DateTime(date);
        return dt.toString(hhmmss_AM);
    }
}
