package com.wangs.dao.impl;

import com.wangs.dao.UserDao;
import com.wangs.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDaoImpl implements UserDao {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String userName, String password){
        String sqlStr = "SELECT count(*) FROM t_user "+
                " WHERE user_name = ? and password = ?";
        return jdbcTemplate.queryForObject(sqlStr, new Object[]{userName,password}, Integer.class);
    }

    public User findUserByUserName(String userName) {
        final User user = new User();
        String sqlStr = " SELECT user_id,user_name,credits,last_ip, last_visit "
                + " FROM t_user WHERE user_name =? ";
        jdbcTemplate.query(sqlStr, new Object[]{userName}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("user_id"));
                user.setUserName(resultSet.getString("user_name"));
                user.setCredits(resultSet.getInt("credits"));
                user.setLastIp(resultSet.getString("last_ip"));
                user.setLastVisit(resultSet.getTimestamp("last_visit"));
            }
        });
        return user;
    }

    public void updateLoginInfo(User user) {
        String sqlStr = "UPDATE t_user SET last_visit = ?, last_ip = ?, credits = ? WHERE user_id = ?";
        jdbcTemplate.update(sqlStr, new Object[]{user.getLastVisit(), user.getLastIp(), user.getCredits(), user.getUserId()});
    }
}
