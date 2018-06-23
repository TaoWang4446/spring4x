package com.wangs.dao;


import com.wangs.domain.User;

public interface UserDao {
    int getMatchCount(String userName, String password);
    User findUserByUserName(final String userName);
    void updateLoginInfo(User user);

}
