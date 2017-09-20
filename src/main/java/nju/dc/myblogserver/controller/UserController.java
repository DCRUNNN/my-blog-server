package nju.dc.myblogserver.controller;

import nju.dc.myblogserver.dao.UserDao;
import nju.dc.myblogserver.dao.utils.UserDaoUtils;
import nju.dc.myblogserver.dto.BaseResult;
import nju.dc.myblogserver.po.UserPO;
import nju.dc.myblogserver.utils.EncryptHelper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserDaoUtils userDaoUtils;

    @GetMapping("/allNames")
    public BaseResult getAllUserNames() {
        return new BaseResult<>(0, userDao.getAllUserNames());
    }

    @PostMapping
    public BaseResult addUser(@RequestBody UserPO userPO, @RequestHeader("username") String userName) {
        String password = userPO.getPassword();
        password = EncryptHelper.getShaEncryption(password);
        userPO.setPassword(password);
        userPO.setSignUpDate(userDaoUtils.setSignUpDate());
        userPO.setUserID(userDaoUtils.createUserID());
        userDao.addUser(userPO);
        return new BaseResult<>(0,"Sign Up Successfully!");
    }

    @PostMapping("/login")
    public BaseResult login(@RequestBody UserPO userPO) throws Exception {
        String inputPassword = userPO.getPassword();

        UserPO checkPO = userDao.getUserPO(userPO.getUserName());
        if (checkPO == null) {
            return new BaseResult(-1, "account not exists！");
        }

        boolean equal = EncryptHelper.checkPassword(inputPassword, checkPO.getPassword());
        if (equal) {
            return new BaseResult(0, "login successfully!");
        }else{
            return new BaseResult(2, "userName and password do not match!");
        }
    }

}
