package com.neo.controller.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@SuppressWarnings("All")
public class DateUtils {
    public static final SimpleDateFormat YYYY = new SimpleDateFormat("yyyy");
    public static final SimpleDateFormat YYYYMM = new SimpleDateFormat("yyyy-MM");
    public static final SimpleDateFormat YYYYMM_NO = new SimpleDateFormat("yyyyMM");
    public static final SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat HHMMSS = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat YYYYMMDD_CH = new SimpleDateFormat("yyyy月MM日dd");
    public static final SimpleDateFormat YYYYMMDDHHMMSS_CH = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
    public static final SimpleDateFormat YYYYMMDDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat YYYYMMDDHHMM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat YYYYMMDDHHMM_CH = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");

    /**
     * 将date转为long字符串
     *
     * @param date 日期
     * @return String 时间long值
     * @date 2014-8-1 下午12:04:16
     * @author MCR
     */
    public static String getLongStrFromDate(Date date) {
        if (date != null) {
            return date.getTime() + "";
        }
        return "";
    }

    /**
     * 格式化日期
     *
     * @param date Date类型
     * @return String 日期
     * @date 2014-8-14 上午11:11:20
     * @author MCR
     */
    public static String formatDate(Date date, SimpleDateFormat dateFormat) {
        return dateFormat.format(date);
    }

    /**
     * 获取当前日期Date
     *
     * @return Date 当前日期
     * @date 2014-8-14 上午11:14:48
     * @author MCR
     */
    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    public static Date getStringDate(String time) {
        java.sql.Date sdate = null; //初始化
        try {

            Date udate = YYYYMMDDHHMMSS.parse(time);

            sdate = new java.sql.Date(udate.getTime()); //2013-01-14 00:00:00

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdate;
    }

    /**
     * 获取当前日期Timestamp
     *
     * @return Timestamp 日期
     * @date 2014-8-14 上午11:15:37
     * @author MCR
     */
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 将 Timestamp 转为 String
     *
     * @param timestamp
     * @param displayTime 是否显示时间部分
     * @return String 日期
     * @date 2014-8-14 上午11:16:39
     * @author MCR
     */
    public static String timestampToString(Timestamp timestamp, boolean displayTime) {
        if (timestamp == null) {
            return "";
        }
        if (displayTime) {
            return timestamp.toString().substring(0, 16);
        }
        return timestamp.toString().substring(0, 10);
    }

    /**
     * 将 Timestamp 转为 String (包括秒)
     *
     * @param timestamp
     * @param displayTime 是否显示时间部分(包括秒)
     * @return String 日期
     * @date 2014-8-14 上午11:17:43
     * @author MCR
     */
    public static String timestampToStringSecond(Timestamp timestamp, boolean displayTime) {
        if (timestamp == null) {
            return "";
        }
        if (displayTime) {
            return timestamp.toString().substring(0, 19);
        } else {
            return timestamp.toString().substring(0, 10);
        }
    }

    /**
     * 用于计算加了天数后的日期
     *
     * @param date   原来的日期
     * @param amount 要增加的天数
     * @return Date 加了天数后的日期
     * @date 2014-8-14 上午11:18:33
     * @author MCR
     */
    public static Date calculatedays(Date date, int amount) {
        GregorianCalendar g = new GregorianCalendar();
        g.setGregorianChange(date);
        g.add(GregorianCalendar.DATE, amount);
        Date d = g.getTime();
        return d;
    }

    /**
     * 用于计算加了天月数后的日期
     *
     * @param date   原来日期
     * @param amount 增加的月数
     * @return Date 增加月数后的日期
     * @date 2014-8-14 上午11:19:20
     * @author MCR
     */
    public static Date calculatemonths(Date date, int amount) {
        GregorianCalendar g = new GregorianCalendar();
        g.setGregorianChange(date);
        g.add(GregorianCalendar.MONTH, amount);
        Date d = g.getTime();
        return d;
    }

    /**
     * 获取当前日期 - yyyy-MM-dd HH:mm:ss
     *
     * @return String yyyy-MM-dd HH:mm:ss类型日期
     * @date 2014-8-14 上午11:20:58
     * @author MCR
     */
    public static String getCurrentDateTime() {
        String currentTime = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar currentDate = Calendar.getInstance();
            currentTime = format.format(currentDate.getTime());
        } catch (Exception e) {
            return null;
        }
        return currentTime;
    }

    public static String getCurrentDateYMD() {
        String currentTime = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar currentDate = Calendar.getInstance();
            currentTime = format.format(currentDate.getTime());
        } catch (Exception e) {
            return null;
        }
        return currentTime;
    }

    /**
     * 格式化日期
     *
     * @param s Timestamp日期
     * @return Timestamp 日期
     * @date 2014-8-14 上午11:20:58
     * @author MCR
     */
    public static Timestamp format(Timestamp s, SimpleDateFormat tempformat) {
        String d = tempformat.format(s);
        return Timestamp.valueOf(d + ".0");
    }

    /**
     * 格式化日期
     *
     * @return Timestamp 日期
     * @date 2014-8-14 上午11:20:58
     * @author MCR
     */
    public static Timestamp format(String date) {
        if (date.contains("/")) {
            date = date.replace("/", "-");
        }
        return Timestamp.valueOf(date);
    }

    /**
     * 格式化日期
     *
     * @param s Timestamp日期
     * @return Timestamp 日期
     * @date 2014-8-14 上午11:20:58
     * @author MCR
     */
    public static Timestamp format(Date s) {
        String d = YYYYMMDDHHMMSS.format(s);
        return Timestamp.valueOf(d);
    }


    // 将String 类型的时间转换为long类型
    public static long StringToLong(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        long millionSeconds = 0;
        try {
            millionSeconds = sdf.parse(date).getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// 毫秒

        return millionSeconds;
    }

    /**
     * 得到本周周一
     *
     * @return yyyy-MM-dd
     */
    public static String getMondayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayofweek == 0)
            dayofweek = 7;
        c.add(Calendar.DATE, -dayofweek + 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(c.getTime());
    }

    /**
     * 得到本周周日
     *
     * @return yyyy-MM-dd
     */
    public static String getSundayOfThisWeek() {
        Calendar c = Calendar.getInstance();
        int dayofweek = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayofweek == 0)
            dayofweek = 7;
        c.add(Calendar.DATE, -dayofweek + 7);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(c.getTime());
    }

    /**
     * 获取本月的第一天
     *
     * @return
     */
    public static String getFirstDayOfMonth() {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(5, 1);
        str = sdf.format(lastDate.getTime());
        return str;
    }


    /*
     * 获取本季度的开始时间
     */
    public static String getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                c.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth >= 10 && currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = YYYYMMDDHHMMSS.parse(YYYYMMDD.format(c.getTime()) + " 00:00:00");
        } catch (Exception e) {
            e.printStackTrace();
        }
        SimpleDateFormat dsf = new SimpleDateFormat("yyyy-MM");
        String dateF = dsf.format(now);
        return dateF;
    }

    /**
     * 获取本年的第一天
     */
    public static String getFirstDayofYeal() {
        String str = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = new GregorianCalendar();
        c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_YEAR, 1);// 本年第一天
        System.out.println();
        str = sdf.format(c.getTime());
        return str;
    }

    public static String getNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = sdf.format(new Date());
        return s;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @return 当前日期是星期几
     */
    public static int getWeekDayOfNow() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return w;
    }


    public static long getDateline() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取某个月的最后一天
     *
     * @param year  年
     * @param month 月
     * @return
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDate = cal.getTime();
        return DateUtils.formatDate(lastDate, YYYYMMDD);
    }

    /**
     * 两个日期差值计算
     *
     * @param early
     * @param late
     * @return
     */
    public static final int daysBetween(Date early, Date late) {

        Calendar calst = Calendar.getInstance();
        Calendar caled = Calendar.getInstance();
        calst.setTime(early);
        caled.setTime(late);
        //设置时间为0时
        calst.set(Calendar.HOUR_OF_DAY, 0);
        calst.set(Calendar.MINUTE, 0);
        calst.set(Calendar.SECOND, 0);
        caled.set(Calendar.HOUR_OF_DAY, 0);
        caled.set(Calendar.MINUTE, 0);
        caled.set(Calendar.SECOND, 0);
        //得到两个日期相差的天数   
        int days = ((int) (caled.getTime().getTime() / 1000) - (int) (calst
                .getTime().getTime() / 1000)) / 3600 / 24;

        return days;
    }

    /**
     * 判断开始日期与结束日期是否为第一天与最后一天
     *
     * @param startTime 开始日期
     * @param endTime   结束日期
     * @return
     */
    public static boolean isFirstAndLast(String startTime, String endTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //获取前月的第一天
        Calendar cal_1 = Calendar.getInstance();//获取当前日期
        try {
            cal_1.setTime(format.parse(startTime));
            cal_1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天
            String firstDay = format.format(cal_1.getTime());
            //获取前月的最后一天
            cal_1.set(Calendar.DAY_OF_MONTH, cal_1.getActualMaximum(Calendar.DAY_OF_MONTH));
            String lastDay = format.format(cal_1.getTime());
            if (firstDay.equals(startTime) && lastDay.equals(endTime)) {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 取得一年的第几周
     *
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int week_of_year = c.get(Calendar.WEEK_OF_YEAR);
        return week_of_year;
    }

    /**
     * 获取当前时间的月份
     *
     * @param time
     * @return
     */
    public static int getMonth(Date time) {
        Calendar now = Calendar.getInstance();
        now.setTime(time);
        return now.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前时间的月份
     *
     * @param time
     * @return
     */
    public static int getYear(Date time) {
        Calendar now = Calendar.getInstance();
        now.setTime(time);
        return now.get(Calendar.YEAR);
    }

    /**
     * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
     *
     * @param date
     * @return
     */
    public static int getSeason(Date date) {

        int season = 0;

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case Calendar.JANUARY:
            case Calendar.FEBRUARY:
            case Calendar.MARCH:
                season = 1;
                break;
            case Calendar.APRIL:
            case Calendar.MAY:
            case Calendar.JUNE:
                season = 2;
                break;
            case Calendar.JULY:
            case Calendar.AUGUST:
            case Calendar.SEPTEMBER:
                season = 3;
                break;
            case Calendar.OCTOBER:
            case Calendar.NOVEMBER:
            case Calendar.DECEMBER:
                season = 4;
                break;
            default:
                break;
        }
        return season;
    }

}
