package lxpsee.top.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.udf.UDFType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/16 15:07.
 * <p>
 * 计算day所在月起始毫秒数
 */
@Description(name = "lpAppLos_getmonthbegin",
        value = "getmonthbegin",
        extended = "getmonthbegin() ;\r\n" +
                " getmonthbegin(2) \r\n" +
                " getmonthbegin('2017/06/29 01:02:03') \r\n" +
                " getmonthbegin('2017/06/29 01:02:03',2) \r\n" +
                " getmonthbegin(date_obj) \r\n" +
                " getmonthbegin(date_obj,2)")
@UDFType(deterministic = true, stateful = false)
public class MonthBeginUDF extends UDF {
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    public long evaluate(Date date) {
        return DateUtil.getMonthBeginTime(date).getTime();
    }

    public long evaluate(Date date, int offset) {
        return DateUtil.getMonthBeginTime(date, offset).getTime();
    }

    public long evaluate() {
        return evaluate(new Date());
    }

    public long evaluate(int offset) {
        return evaluate(new Date(), offset);
    }

    public long evaluate(String dateStr) {
        try {
            Date date = simpleDateFormat.parse(dateStr);
            return evaluate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long evaluate(String dateStr, int offset) {
        try {
            Date date = simpleDateFormat.parse(dateStr);
            return evaluate(date, offset);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public long evaluate(String dateStr, String format) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return evaluate(dateFormat.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

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
