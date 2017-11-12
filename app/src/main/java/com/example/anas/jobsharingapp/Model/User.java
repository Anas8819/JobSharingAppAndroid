package com.example.anas.jobsharingapp.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Anas on 11/11/2017.
 */

public class User {

    @SerializedName("user_id")
    @Expose
    private Integer userId;

    @SerializedName("user_name")
    @Expose
    private String userName;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("role")
    @Expose
    private String role;

    public Integer userId() {
        return userId;
    }

    public void userId(String userName) {
        this.userId = userId;
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

    public String getRole() {
        return role;
    }

    public void setRole(String userName) {
        this.role = role;
    }


}
