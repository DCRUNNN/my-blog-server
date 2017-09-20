package nju.dc.myblogserver.vo;

import nju.dc.myblogserver.po.UserPO;

public class UserVO {

    private String userName;

    private String userID;

    private String password;

    private String email;

    private String phoneNumber;

    private String signUpDate;


    public UserVO() {
    }

    public UserVO(UserPO userPO) {
        this.userName = userPO.getUserName();
        this.userID = userPO.getUserID();
        this.password = userPO.getPassword();
        this.email = userPO.getEmail();
        this.phoneNumber = userPO.getPhoneNumber();
        this.signUpDate = userPO.getSignUpDate();
    }

    public String getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(String signUpDate) {
        this.signUpDate = signUpDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "userName='" + userName + '\'' +
                ", userID='" + userID + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", signUpDate='" + signUpDate + '\'' +
                '}';
    }
}
