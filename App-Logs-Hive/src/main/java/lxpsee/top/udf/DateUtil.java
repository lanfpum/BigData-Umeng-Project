package lxpsee.top.udf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/14 16:06.
 * <p>
 * 得到指定date的零时刻.
 */
public class DateUtil {
    private static SimpleDateFormat dayBeginTimeFormat   = new SimpleDateFormat("yyyy/MM/dd 00:00:00");
    private static SimpleDateFormat monthBeginTimeFormat = new SimpleDateFormat("yyyy/MM/01 00:00:00");

    /**
     * 得到指定date的零时刻.
     */
    public static Date getDayBeginTime(Date date) {
        try {
            return dayBeginTimeFormat.parse(dayBeginTimeFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 得到当前date的零时刻.
     */
    public static Date getNowDayBeginTime() {
        try {
            return dayBeginTimeFormat.parse(dayBeginTimeFormat.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 得到指定date的偏移量零时刻.
     */
    public static Date getDayBeginTime(Date date, int offset) {
        try {
            Date dayBegin = dayBeginTimeFormat.parse(dayBeginTimeFormat.format(date));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(dayBegin);
            calendar.add(Calendar.DAY_OF_MONTH, offset);
            return calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 得到指定date所在周的起始时刻.
     */
    public static Date getWeekBeginTime(Date date) {
        Calendar calendar = getCalendarOfWeekBeginTime(date);
        return calendar.getTime();
    }

    /**
     * 得到指定date所在周的偏移量起始时刻.
     */
    public static Date getWeekBeginTime(Date date, int offset) {
        Calendar calendar = getCalendarOfWeekBeginTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, offset * 7);
        return calendar.getTime();
    }

    /**
     * 得到指定date所在月的起始时刻.
     */
    public static Date getMonthBeginTime(Date date) {
        try {
            return monthBeginTimeFormat.parse(monthBeginTimeFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 得到指定date所在月的偏移量起始时刻.
     */
    public static Date getMonthBeginTime(Date date, int offset) {
        try {
            Date firstDay = monthBeginTimeFormat.parse(monthBeginTimeFormat.format(date));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(firstDay);
            calendar.add(Calendar.MONTH, offset);
            return calendar.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Calendar getCalendarOfWeekBeginTime(Date date) {
        Date dayBeginTime = getDayBeginTime(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dayBeginTime);
        int n = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DAY_OF_WEEK, -(n - 1));
        return calendar;
    }

}
