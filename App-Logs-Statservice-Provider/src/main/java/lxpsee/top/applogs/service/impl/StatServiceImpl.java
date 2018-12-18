package lxpsee.top.applogs.service.impl;

import lxpsee.top.applogs.dao.impl.StatDao;
import lxpsee.top.domain.StatBean;
import lxpsee.top.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/17 15:54.
 */
@Service("statService")
public class StatServiceImpl extends BaseServiceImpl<StatBean> implements StatService {
    @Autowired
    private StatDao statDao;

    public StatBean findNewUsers() {
        return statDao.findNewUsers();
    }

    public long todayNewUsers(String appid) {
        return statDao.todayNewUsers(appid);
    }
}
