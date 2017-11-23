package com.example.anas.jobsharingapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by Anas on 11/11/2017.
 */

public class Job {
    @SerializedName("user_id")
    @Expose
    private Integer userId;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("organization")
    @Expose
    private String organization;

    @SerializedName("type")
    @Expose
    private String type;

    @SerializedName("salary")
    @Expose
    private Integer salary;

    @SerializedName("date")
    @Expose
    private String date;

    public Job() {
    }

    public Job(Integer userId, String title, String description, String organization, String type, Integer salary, String date) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.organization = organization;
        this.type = type;
        this.salary = salary;
        this.date = date;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
