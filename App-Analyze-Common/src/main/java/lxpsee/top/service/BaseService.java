package lxpsee.top.service;

import lxpsee.top.domain.StatBean;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/17 16:13.
 */
public interface BaseService<T> {
    public StatBean findNewUsers();

    public long todayNewUsers(String apppid);
}
