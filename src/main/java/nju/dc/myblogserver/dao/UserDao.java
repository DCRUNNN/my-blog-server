package nju.dc.myblogserver.dao;

import nju.dc.myblogserver.po.UserPO;

import java.util.List;

public interface UserDao {

    int addUser(UserPO userPO);

    int deleteUser(UserPO userPO);

    String getUserID(String userName);

    String getUserName(String userID);

    UserPO getUserPO(String userName);

    List<String> getAllUserNames();

    List<UserPO> getAllUsersPO();


}
