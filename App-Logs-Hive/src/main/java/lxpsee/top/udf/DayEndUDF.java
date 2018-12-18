package lxpsee.top.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.udf.UDFType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/14 16:29.
 * <p>
 * 计算day结束毫秒数
 */
@Description(name = "lpAppLos_getEndday", value = "getEndTimeInDay",
        extended = "udf() ; udf('2017/06/27 02:03:04') ; udf('2017-06-27 02-03-04','yyyy-MM-dd HH-mm-ss')")
@UDFType(deterministic = true, stateful = false)
public class DayEndUDF extends UDF {

    /**
     * 计算指定日期的结束时刻(毫秒数)
     */
    public long evaluate(Date d) {
        Date zeroDate = DateUtil.getDayBeginTime(d);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(zeroDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTimeInMillis();
    }

    /**
     * 计算今天结束时刻(毫秒数),其实是明天的零时.
     */
    public long evaluate() {
        return evaluate(new Date());
    }

    /**
     * 计算指定日期,使用的格式是yyyy/MM/dd HH:mm:ss的结束时刻(毫秒数)
     */
    public long evaluate(String dateStr) {
        long time = 0;

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            time = evaluate(format.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return time;
    }

    /**
     * 计算指定日期结束时刻,使用的格式自行指定(毫秒数)
     */
    public long evaluate(String dateStr, String format) {
        long time = 0;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            time = evaluate(simpleDateFormat.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return time;
    }

}
