package lxpsee.top.udf;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/14 16:10.
 */
public class TestUdf {

    @Test
    public void testUdf() {
        Date zeroDate = DateUtil.getZeroDate(new Date());
        System.out.println(zeroDate.getTime());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        try {
            Date parse = simpleDateFormat.parse("2018/12/14 00:00:00");
            System.out.println(parse.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算某天的起始时刻(毫秒数)
     */
    @Test
    public void testStartTime() {
        Date d = new Date();
        long ms = DateUtil.getZeroDate(d).getTime();
        System.out.println(ms);
    }

    /**
     * 计算某天的结束时刻(毫秒数)
     */
    @Test
    public void testEndTime() {
        Date date = new Date();
        Date zeroDate = DateUtil.getZeroDate(date);
        Calendar c = Calendar.getInstance();
        c.setTime(zeroDate);
        c.add(Calendar.DAY_OF_MONTH, 1);
        printInfo(c);
    }

    /**
     * 测试周起始时间
     */
    @Test
    public void testWeekStartTime() {
        Date nowZeroDate = DateUtil.getNowZeroDate();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowZeroDate);
        int n = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DAY_OF_MONTH, -(n - 1));
        printInfo(calendar);
    }

    /**
     * 测试周结束时间
     */
    @Test
    public void testWeekEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.getNowZeroDate());
        int n = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DAY_OF_MONTH, 8 - n);
        printInfo(calendar);
    }

    /**
     * 得到指定date所在的月的第一天
     */
    @Test
    public void testMonthStartDate() {
        Date date = DateUtil.getNowZeroDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-01 00:00:00");
        System.out.println(simpleDateFormat.format(date));
    }

    /**
     * 得到指定date所在的下个月的第一天
     */
    @Test
    public void testMonthEndDate() {
        Date date = DateUtil.getNowZeroDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-01 00:00:00");
        Date date1 = null;
        try {
            date1 = simpleDateFormat.parse(simpleDateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        calendar.add(Calendar.MONTH, 1);
        printInfo(calendar);
    }

    public void printInfo(Calendar calendar) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(DateUtil.getZeroDate(date).getTime());
        System.out.println(calendar.getTimeInMillis());
        System.out.println(simpleDateFormat.format(DateUtil.getZeroDate(date)));
        System.out.println(simpleDateFormat.format(calendar.getTime()));
    }

}
