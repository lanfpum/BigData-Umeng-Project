package lxpsee.top.applogs.dao.impl;


import lxpsee.top.applogs.dao.BaseDao;
import lxpsee.top.domain.StatBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * The world always makes way for the dreamer
 * Created by 努力常态化 on 2018/12/17 16:15.
 */
@Repository("statDao")
public class StatDao extends SqlSessionDaoSupport implements BaseDao<StatBean> {

    public StatBean findNewUsers() {
        return getSqlSession().selectOne("stats.newusers");
    }

    public long todayNewUsers(String appid) {
        return getSqlSession().selectOne("stats.selectAPPTodayNewUsers", appid);
    }

    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}
