package com.wangs.dao.impl;

import com.wangs.dao.LoginLogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.wangs.domain.LoginLog;

@Repository
public class LoginLogDaoImpl implements LoginLogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void insertLoginLog(LoginLog loginLog) {
        String sqlStr = "insert into t_login_log(user_id,ip,login_datetime) values(?,?,?)";
        jdbcTemplate.update(sqlStr,new Object[]{loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDate()});
    }
}
