package lxpsee.top.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.udf.UDFType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/14 16:17.
 * <p>
 * 计算day起始毫秒数
 */
@Description(name = "lpAppLos_getdaybegin", value = "getdaybegin",
        extended = "udf() ; \r\n"
                + "udf('2017/06/27 02:03:04') ;\r\n"
                + "udf('2017-06-27 02-03-04','yyyy-MM-dd HH-mm-ss')")
@UDFType(deterministic = true, stateful = false)
public class DayBeginUDF extends UDF {
    /**
     * 计算某天的结束时刻(毫秒数)
     */
    public long evaluate(Date date) {
        return DateUtil.getDayBeginTime(date).getTime();
    }

    /**
     * 计算某天的偏移量结束时刻(毫秒数)
     */
    public long evaluate(Date date, int offset) {
        return DateUtil.getDayBeginTime(date, offset).getTime();
    }

    /**
     * 计算现在的起始时刻(毫秒数)
     */
    public long evaluate() {
        return evaluate(new Date());
    }

    /**
     * 计算现在的偏移量起始时刻(毫秒数)
     */
    public long evaluate(int offset) {
        return DateUtil.getDayBeginTime(new Date(), offset).getTime();
    }

    /**
     * 计算某天的起始时刻(毫秒数)
     */
    public long evaluate(String dateStr) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return evaluate(simpleDateFormat.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 计算某天的偏移量起始时刻(毫秒数)
     */
    public long evaluate(String dateStr, int offset) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            return evaluate(simpleDateFormat.parse(dateStr), offset);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 计算某天的起始时刻(毫秒数)
     */
    public long evaluate(String dateStr, String format) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return evaluate(simpleDateFormat.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 计算某天的偏移量起始时刻(毫秒数)
     */
    public long evaluate(String dateStr, String format, int offset) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return evaluate(simpleDateFormat.parse(dateStr), offset);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }

}
