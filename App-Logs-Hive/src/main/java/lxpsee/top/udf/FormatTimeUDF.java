package lxpsee.top.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/17 13:16.
 * <p>
 * 将long型的时间片格式化成指定日期格式
 */
@Description(name = "lpapplogs_formattime", value = "formattime",
        extended = "formattime() ;\r\n"
                + " formattime(1234567,'yyyy/MM/01') \r\n"
                + " formattime('1234567','yyyy/MM/dd')")
public class FormatTimeUDF extends UDF {

    /**
     * 格式化时间,long型
     */
    public String evaluate(long ms, String format) {
        Date date = new Date(ms);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 格式化时间，string类型
     */
    public String evaluate(String ms, String format) {
        Date date = new Date(Long.parseLong(ms));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 格式化时间，string类型,添加第三个参数：UDF中只有该方法，为了重载需要
     */
    public String evaluate(long ms, String format, int week) {
        Date date = new Date(ms);
        Date weekBeginTime = DateUtil.getWeekBeginTime(date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(weekBeginTime);
    }
}
