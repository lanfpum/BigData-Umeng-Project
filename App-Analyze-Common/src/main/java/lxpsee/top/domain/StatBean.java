package lxpsee.top.domain;

import java.io.Serializable;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/17 15:59.
 * <p>
 * 统计信息类
 */
public class StatBean implements Serializable {
    private String date;       //统计日期
    private long   count;      //统计数量

    public StatBean() {
    }

    public StatBean(String date, long count) {
        this.date = date;
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
