package com.wangs.service;

import com.wangs.domain.LoginLog;
import com.wangs.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Date;

import static org.testng.AssertJUnit.assertTrue;

@ContextConfiguration("classpath*:/wangs-context.xml")
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    private UserService userService;


    @Test
    public void hasMatchUser(){
        System.out.println(userService);
        boolean b1 = userService.hasMatchUser("admin","123456");
        boolean b2 = userService.hasMatchUser("admin","1234561");
        assertTrue(b1);
        assertTrue(!b2);
    }

    @Test
    public void findUserByUserName(){
        System.out.println(userService);
        User user = userService.findUserByUserName("admin");
        System.out.println(user.getUserId());
        System.out.println(user.getUserName());
        System.out.println(user.getCredits());
        System.out.println(user.getLastIp());
        System.out.println(user.getLastVisit());
    }

    @Test
    public void updateLoginInfo(){
        User user = new User();
        user.setLastVisit(new Date());
        user.setLastIp("127.0.0.1");
        user.setCredits(50);
        user.setUserId(1);
        userService.updateLoginInfo(user);
    }

    @Test
    public void insertLoginLog(){
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(1);
        loginLog.setLoginDate(new Date());
        loginLog.setIp("127.0.0.1");

        userService.insertLoginLog(loginLog);
    }

    @Test
    public void loginSuccess(){
        User user = new User();
        user.setUserName("admin");
        user.setCredits(50);
        user.setLastIp("127.0.0.1");
        user.setLastVisit(new Date());
        userService.loginSuccess(user);
    }
}
