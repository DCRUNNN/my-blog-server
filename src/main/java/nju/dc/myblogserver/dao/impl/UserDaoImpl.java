package nju.dc.myblogserver.dao.impl;

import nju.dc.myblogserver.dao.UserDao;
import nju.dc.myblogserver.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int addUser(UserPO userPO) {
        String sql = "insert into user(userID,userName,password,email,phoneNumber,signUpDate) values "
                + "("
                + '"' + userPO.getUserID() + '"' + "," + '"' + userPO.getUserName() + '"' + "," + '"' + userPO.getPassword() + '"'
                + "," + '"' + userPO.getEmail() + '"' + ',' + '"' + userPO.getPhoneNumber() + '"' + "," + '"' + userPO.getSignUpDate() + '"'
                + ")";
        return jdbcTemplate.update(sql);
    }

    @Override
    public int deleteUser(UserPO userPO) {
        String sql = "delete from user where userID = " + '"' + userPO.getUserID() + '"';
        return jdbcTemplate.update(sql);
    }

    @Override
    public UserPO getUserPO(String userName) {

        String checkExistSql = "Select count(1) from user where userName = " + '"' + userName + '"';
        int checkExists = jdbcTemplate.queryForObject(checkExistSql, new Object[]{}, Integer.class);
        if (checkExists == 0) {
            return null;
        }

        String sql = "Select * from user where userName = " + '"' + userName + '"';
        UserPO po = jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
            UserPO tempPO = new UserPO();
            tempPO.setUserName(resultSet.getString("userName"));
            tempPO.setUserID(resultSet.getString("userID"));
            tempPO.setPassword(resultSet.getString("password"));
            tempPO.setPhoneNumber(resultSet.getString("phoneNumber"));
            tempPO.setEmail(resultSet.getString("email"));
            tempPO.setSignUpDate(resultSet.getString("signUpDate"));
            return tempPO;
        });
        return po;
    }

    @Override
    public String getUserID(String userName) {
        String sql = "select userID from user where userName = ?" ;
        return jdbcTemplate.queryForObject(sql, new Object[] { userName }, String.class);
    }

    @Override
    public String getUserName(String userID) {
        String sql = "select userName from user where userID = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{ userID }, String.class);

    }

    @Override
    public List<String> getAllUserNames() {
        String sql = "select userName from user";
        List<String> allNames=jdbcTemplate.query(sql, ((resultSet, i) -> resultSet.getString("userName")));
        return allNames.isEmpty() ? new ArrayList<>() : allNames;
    }

    @Override
    public List<UserPO> getAllUsersPO() {
        String sql = "select * from user";
        List<UserPO> userPOS = jdbcTemplate.query(sql, getUserMapper());
        return userPOS.isEmpty() ? new ArrayList<>() : userPOS;

    }

    private RowMapper<UserPO> getUserMapper() {
        return (resultSet, i) -> {
            UserPO userPO = new UserPO();
            userPO.setUserID(resultSet.getString("userID"));
            userPO.setUserName(resultSet.getString("userName"));
            userPO.setPhoneNumber(resultSet.getString("phoneNumber"));
            userPO.setSignUpDate(resultSet.getString("signUpDate"));
            userPO.setEmail(resultSet.getString("email"));
            userPO.setPassword(resultSet.getString("password"));
            return userPO;
        };
    }
}
