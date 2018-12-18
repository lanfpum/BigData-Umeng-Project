package lxpsee.top.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.udf.UDFType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/16 14:51.
 * <p>
 * 计算day所在周起始毫秒数
 */
@Description
        (name = "lpAppLos_getweekbegin",
                value = "getweekbegin",
                extended = "getweekbegin() ;\r\n"
                        + " getweekbegin(2) \r\n"
                        + " getweekbegin('2017/06/29 01:02:03') \r\n"
                        + " getweekbegin('2017/06/29 01:02:03',2) \r\n"
                        + " getweekbegin(date_obj) \r\n"
                        + " getweekbegin(date_obj,2)")
@UDFType(deterministic = true, stateful = false)
public class WeekBeginUDF extends UDF {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * 计算现在的起始时刻(毫秒数)
     */
    public long evaluate() {
        return evaluate(new Date());
    }

    /**
     * 指定周偏移量，计算现在的起始时刻(毫秒数)
     */
    public long evaluate(int offset) {
        return evaluate(new Date(), offset);
    }

    /**
     * 计算指定日期的起始时刻(毫秒数)
     */
    public long evaluate(Date date) {
        return DateUtil.getWeekBeginTime(date).getTime();
    }

    /**
     * 指定周偏移量，计算指定日期的起始时刻(毫秒数)
     */
    public long evaluate(Date date, int offset) {
        return DateUtil.getWeekBeginTime(date, offset).getTime();
    }

    /**
     * 计算给定日期字符串的起始时刻(毫秒数)
     */
    public long evaluate(String dateStr) {
        try {
            Date date = simpleDateFormat.parse(dateStr);
            return evaluate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 计算给定日期字符串的偏移量起始时刻(毫秒数)
     */
    public long evaluate(String dateStr, int offset) {
        try {
            Date date = simpleDateFormat.parse(dateStr);
            return evaluate(date, offset);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 计算给定日期字符串,指定格式化 的起始时刻(毫秒数)
     */
    public long evaluate(String dateStr, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return evaluate(dateFormat.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 计算给定日期字符串,指定格式化,偏移量 的起始时刻(毫秒数)
     */
    public long evaluate(String dateStr, String format, int offset) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return evaluate(dateFormat.parse(dateStr), offset);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
