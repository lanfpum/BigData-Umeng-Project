package lxpsee.top.udf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/14 16:06.
 * <p>
 * 得到指定date的零时刻.
 */
public class DateUtil {
    public static Date getZeroDate(Date d) {
        Date date = null;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd 00:00:00");
            date = simpleDateFormat.parse(simpleDateFormat.format(d));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static Date getNowZeroDate() {
        Date date = null;

        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd 00:00:00");
            date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
