package com.stackroute.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "userprofile")
public class Userprofiledomain1 {

//   @Id
    private String sid;

    private String userName;
    private String password;
    private String email;
    private String phonenumber;
    private String applicationname;
    private String dependency;


    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
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

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getApplicationname() {
        return applicationname;
    }

    public void setApplicationname(String applicationname) {
        this.applicationname = applicationname;
    }

    public String getDependency() {
        return dependency;
    }

    public void setDependency(String dependency) {
        this.dependency = dependency;
    }



    public Userprofiledomain1(){
    }

    public Userprofiledomain1(String userid, String userName, String password, String email, String phonenumber, String applicationname, String dependency, String sid){
        this.sid=sid;
        this.userName=userName;
        this.password=password;
        this.email=email;
        this.phonenumber=phonenumber;
        this.applicationname=applicationname;
        this.dependency=dependency;
    }

}
