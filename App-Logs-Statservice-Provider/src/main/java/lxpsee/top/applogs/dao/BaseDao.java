package lxpsee.top.applogs.dao;

import lxpsee.top.domain.StatBean;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/17 16:11.
 * <p>
 * 基本的dao接口
 */
public interface BaseDao<T> {
    public StatBean findNewUsers();

    public long todayNewUsers(String appid);
}
