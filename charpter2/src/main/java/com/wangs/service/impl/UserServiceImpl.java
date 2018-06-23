package com.wangs.service.impl;

import com.wangs.dao.LoginLogDao;
import com.wangs.domain.User;
import com.wangs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wangs.dao.UserDao;
import com.wangs.domain.LoginLog;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginLogDao loginLogDao;

    public boolean hasMatchUser(String userName, String password) {
        int matchCount = userDao.getMatchCount(userName, password);
        return matchCount > 0;
    }

    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    public void updateLoginInfo(User user) {
        userDao.updateLoginInfo(user);
    }

    public void insertLoginLog(LoginLog loginLog) {
        loginLogDao.insertLoginLog(loginLog);
    }

    @Transactional
    public void loginSuccess(User user) {
        user.setCredits(5+user.getCredits());
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(1);
        loginLog.setIp("127.0.0.1");
        loginLog.setLoginDate(user.getLastVisit());
        userDao.updateLoginInfo(user);
        loginLogDao.insertLoginLog(loginLog);
    }
}
