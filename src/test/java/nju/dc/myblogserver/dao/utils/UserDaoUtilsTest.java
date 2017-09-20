package nju.dc.myblogserver.dao.utils;

import nju.dc.myblogserver.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoUtilsTest {

    @Autowired
    private UserDaoUtils userDaoUtils;

    @Test
    public void createUserID() throws Exception {
        System.out.println(userDaoUtils.createUserID());
    }

    @Test
    public void setSignUpDate() throws Exception {
        System.out.println(userDaoUtils.setSignUpDate());
    }

}