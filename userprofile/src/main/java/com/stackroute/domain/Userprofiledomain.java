package com.stackroute.domain;


public class Userprofiledomain {
    private String userName;
    private String password;

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

    public Userprofiledomain(){
    }

    public Userprofiledomain(String userName, String password){
        this.userName=userName;
        this.password=password;
    }

}
