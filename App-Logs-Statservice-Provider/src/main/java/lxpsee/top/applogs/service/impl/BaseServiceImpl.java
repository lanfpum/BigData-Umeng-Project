package lxpsee.top.applogs.service.impl;

import lxpsee.top.applogs.dao.BaseDao;
import lxpsee.top.service.BaseService;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/17 16:25.
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {
    private BaseDao<T> baseDao;

    public BaseDao<T> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

}
