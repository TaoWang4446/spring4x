package com.wangs.service;


import com.wangs.domain.LoginLog;
import com.wangs.domain.User;

public interface UserService {
    boolean hasMatchUser(String userName, String password);
    User findUserByUserName(String userName);
    void updateLoginInfo(User user);
    void insertLoginLog(LoginLog loginLog);
    void loginSuccess(User user);
}
