package nju.dc.myblogserver.dao.impl;

import nju.dc.myblogserver.dao.UserDao;
import nju.dc.myblogserver.dao.utils.UserDaoUtils;
import nju.dc.myblogserver.po.UserPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDaoUtils userDaoUtils;

    @Test
    public void addUser() throws Exception {

        UserPO userPO = new UserPO();
        userPO.setUserName("测试2");
        userPO.setEmail("112233446@qq.com");
        userPO.setPassword("123456");
        userPO.setPhoneNumber("13155566688");
        userPO.setSignUpDate(userDaoUtils.setSignUpDate());
        userPO.setUserID(userDaoUtils.createUserID());
        System.out.println(userPO.toString());
        System.out.println(userDao.addUser(userPO));

    }

    @Test
    public void deleteUser() throws Exception {

        UserPO po = new UserPO();
        po.setUserID("user-0001");
        System.out.println(userDao.deleteUser(po));
    }

    @Test
    public void getUserID() throws Exception {
        System.out.println(userDao.getUserID("测试"));
    }

    @Test
    public void getUserName() throws Exception {
        System.out.println(userDao.getUserName("user-0002"));

    }

    @Test
    public void getAllUserNames() throws Exception {
        System.out.println(userDao.getAllUserNames());
    }

    @Test
    public void getUserPO() throws Exception {
        System.out.println(userDao.getUserPO("testtest") == null);
    }

}