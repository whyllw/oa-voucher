package com.fawvw.ms.voucher.baseservice.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;


public final class DateUtil {

    private DateUtil(){

    }

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String HOUR_PATTERN = DATE_TIME_PATTERN;
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String MONTH_PATTERN = "yyyy-MM";
    public static final String MONTH_DAY_PATTERN = "MM-dd HH:mm";
    public static final String DATE_NO_SPLIT_PATTERN = "yyyyMMddHHmmss";
    public static final String YEAR_PATTERN = "yyyy";
    public static final String MINUTE_ONLY_PATTERN = "mm";
    public static final String HOUR_ONLY_PATTERN = "HH";
    public static final String DATE_TIME_FILENAME_PATTERN = "_yyyy_MM_dd_HH_mm";
    public static final String DATE_TIME_SLANTING_BAR_PATTERN = "yyyy/MM/dd";
    private static final int ONE_THOUSAND = 1000;

    public static String formatDay(Date date) {
        return DateFormatUtils.format(date, "yyyyMMdd");
    }

    public static String formatDateTime(Date date) {
        return DateFormatUtils.format(date, MINUTE_PATTERN);
    }

    /**
     * 日期相加减天数
     *
     * @param date        如果为Null，则为当前时间
     * @param days        加减天数
     * @param includeTime 是否包括时分秒,true表示包含
     * @return Date
     * @throws ParseException
     */
    public static Date dateAdd(Date date, int days, boolean includeTime) throws ParseException {
        Date ndate = date;
        if (date == null) {
            ndate = new Date();
        }
        if (!includeTime) {
            SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.DATE_PATTERN);
            ndate = sdf.parse(sdf.format(ndate));
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(ndate);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 时间格式化成字符串
     *
     * @param date    Date
     * @param pattern StrUtils.DATE_TIME_PATTERN || StrUtils.DATE_PATTERN， 如果为空，则为yyyy-MM-dd
     * @return String
     * @throws ParseException
     */
    public static String dateFormat(Date date, String pattern) throws ParseException {
        String npattern = pattern;
        if (StringUtils.isBlank(npattern)) {
            npattern = DateUtil.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(npattern);
        return sdf.format(date);
    }

    /**
     * 字符串解析成时间对象
     *
     * @param dateTimeString String
     * @param pattern        StrUtils.DATE_TIME_PATTERN || StrUtils.DATE_PATTERN，如果为空，则为yyyy-MM-dd
     * @return Date
     * @throws ParseException
     */
    public static Date dateParse(String dateTimeString, String pattern) throws ParseException {
        String npattern = pattern;
        if (StringUtils.isBlank(dateTimeString)) {
            return null;
        }
        if (StringUtils.isBlank(npattern)) {
            npattern = DateUtil.DATE_PATTERN;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(npattern);
        return sdf.parse(dateTimeString);
    }

    /**
     * 将日期时间格式成只有日期的字符串（可以直接使用dateFormat，Pattern为Null进行格式化）
     *
     * @param dateTime Date
     * @return
     * @throws ParseException
     */

    /**
     * 将日期时间格式成日期对象，和dateParse互用
     *
     * @param dateTime Date
     * @return Date
     * @throws ParseException
     */
    public static Date dateTimeToDate(Date dateTime) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dateTime);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


    /**
     * 时间加减小时
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param hours     加减的小时
     * @return Date
     */
    public static Date dateAddHours(Date startDate, int hours) {
        Date nstartDate = startDate;
        if (nstartDate == null) {
            nstartDate = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nstartDate);
        calendar.set(Calendar.HOUR, calendar.get(Calendar.HOUR) + hours);
        return calendar.getTime();
    }

    /**
     * 时间加减分钟
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param minutes   加减的分钟
     * @return
     */
    public static Date dateAddMinutes(Date startDate, int minutes) {
        Date nstartDate = startDate;
        if (nstartDate == null) {
            nstartDate = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nstartDate);
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minutes);
        return calendar.getTime();
    }

    /**
     * 时间加减秒数
     *
     * @param startDate 要处理的时间，Null则为当前时间
     * @param seconds   加减的秒数
     * @return
     */
    public static Date dateAddSeconds(Date startDate, int seconds) {
        Date nstartDate = startDate;
        if (nstartDate == null) {
            nstartDate = new Date();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nstartDate);
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + seconds);
        return calendar.getTime();
    }


    /**
     * 时间比较（如果myDate>compareDate返回1，(返回-1，相等返回0）
     * @param myDate 时间
     * @param compareDate 要比较的时间
     * @return int
     */
    public static int dateCompare(Date myDate, Date compareDate) {
        Calendar myCal = Calendar.getInstance();
        Calendar compareCal = Calendar.getInstance();
        myCal.setTime(myDate);
        compareCal.setTime(compareDate);
        return myCal.compareTo(compareCal);
    }


    /**
     * 获取两个时间中最大的一个时间
     *
     * @param date
     * @param compareDate
     * @return
     */
    public static Date dateMax(Date date, Date compareDate) {
        if (date == null) {
            return compareDate;
        }
        if (compareDate == null) {
            return date;
        }
        if (1 == dateCompare(date, compareDate)) {
            return date;
        } else if (-1 == dateCompare(date, compareDate)) {
            return compareDate;
        }
        return date;
    }

    /**
     * 获取两个日期（不含时分秒）相差的天数，不包含今天
     *
     * @param startDate
     * @param endDate
     * @throws ParseException
     */
    private static final int NUM_THOUSAND = 1000;
    private static final int NUM_SIXTY = 60;
    private static final int NUM_TWENTY_FOUR = 24;
    private static final int NUM_TWELVE = 12;
    private static final int NUM_TWENTY_THREE = 23;
    private static final int NUM_TWENTY_NINE = 59;
    private static final int NUM_THREE_THOUSAND_SIX_HUNDRED = 3600;

    public static int dateBetween(Date startDate, Date endDate) throws ParseException {
        Date dateStart = dateParse(dateFormat(startDate, DATE_PATTERN), DATE_PATTERN);
        Date dateEnd = dateParse(dateFormat(endDate, DATE_PATTERN), DATE_PATTERN);
        return (int) ((dateEnd.getTime() - dateStart.getTime()) / NUM_THOUSAND / NUM_SIXTY / NUM_SIXTY / NUM_TWENTY_FOUR);
    }

    /**
     * 获取两个日期（不含时分秒）相差的天数，包含今天
     *
     * @param startDate
     * @param endDate
     * @return int
     * @throws ParseException
     */
    public static int dateBetweenIncludeToday(Date startDate, Date endDate) throws ParseException {
        return dateBetween(startDate, endDate) + 1;
    }

    /**
     * 获取日期时间的年份，如2017-02-13，返回2017
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 获取日期时间的月份，如2017年2月13日，返回2
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期时间的第几天（即返回日期的dd），如2017-02-13，返回13
     *
     * @param date
     * @return
     */
    public static int getDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DATE);
    }

    /**
     * 获取日期时间当月的总天数，如2017-02-13，返回28
     *
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DATE);
    }

    /**
     * 获取日期时间当年的总天数，如2017-02-13，返回2017年的总天数
     *
     * @param date
     * @return
     */
    public static int getDaysOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
    }

    /**
     * 将年月日转化成日期对象
     * @param year Integer month, Integer day hour  minute second
     * @return Long
     * @throws ParseException
     */

    public static Long timeTotimeStamp(Integer year, Integer month, Integer day, Integer hour, Integer minute, Integer second) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis() / ONE_THOUSAND;
    }


    public static long dateDiff(Date startTime, Date endTime) {
        if (endTime.after(startTime)) {
            return (endTime.getTime() - startTime.getTime()) / ONE_THOUSAND;
        } else {
            return (startTime.getTime() - endTime.getTime()) / ONE_THOUSAND;
        }
    }

    /**
     * 根据时间获取当月最小的日期，也就是返回当月的1号日期对象
     *
     * @param date Date
     * @return Date
     * @throws Exception
     */
    public static Date minDateOfMonth(Date date) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int value = cal.getActualMinimum(Calendar.DATE);
        return dateParse(dateFormat(date, MONTH_PATTERN) + "-" + value, null);
    }


    /**
     * 格式化one-app项目的发布时间
     * X小时前（示例：2小时前）：发布时间距离当前时间大于12小时
     * mm-dd hh:mm（示例：04-13 09:26 ）：发布时间距离当前时间大于12小时且未跨年
     * yy-mm-dd hh:mm（示例：2018-04-10 09:26 ）：发布时间距离当前时间已经跨年
     *
     * @param date publishTime
     * @return
     */
    public static String formatPublishTime(Date date) throws ParseException {
        if (null == date) {
            return "";
        }
        Date now = new Date();
        long spaceTime = (now.getTime() - date.getTime()) / NUM_THOUSAND / NUM_SIXTY + 1;
        if (spaceTime < NUM_SIXTY) {
            return spaceTime + "分钟前";
        }
        //12小时之内
        if (spaceTime <= NUM_TWELVE * NUM_SIXTY) {
            return spaceTime / NUM_SIXTY + "小时前";
        }
        //发布时间距离当前时间大于12小时且未跨年
        if (getYear(now) == getYear(date)) {
            return dateFormat(date, DateUtil.MONTH_DAY_PATTERN);
        }
        return dateFormat(date, DateUtil.MINUTE_PATTERN);
    }

    public static Date dateFormatToCurDayLastTime(Date date) {
        Date time = DateUtil.dateAddHours(date, NUM_TWENTY_THREE);
        time = DateUtil.dateAddMinutes(time, NUM_TWENTY_NINE);
        time = DateUtil.dateAddSeconds(time, NUM_TWENTY_NINE);
        return time;
    }


    public static long dateDiffDays(Date startTime, Date endTime) {
        if (Objects.isNull(startTime) || Objects.isNull(endTime)) {
            return 0;
        }
        if (endTime.after(startTime)) {
            return (endTime.getTime() - startTime.getTime()) / (NUM_THOUSAND * NUM_THREE_THOUSAND_SIX_HUNDRED * NUM_TWENTY_FOUR);
        } else {
            return (startTime.getTime() - endTime.getTime()) / (NUM_THOUSAND * NUM_THREE_THOUSAND_SIX_HUNDRED * NUM_TWENTY_FOUR);
        }
    }

}
