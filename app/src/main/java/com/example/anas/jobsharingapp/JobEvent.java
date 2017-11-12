package com.example.anas.jobsharingapp;

import com.example.anas.jobsharingapp.Model.Job;

import java.util.List;

/**
 * Created by Anas on 11/11/2017.
 */

public class JobEvent {
    private List<Job> message;

    public JobEvent(List<Job> message) {
        this.message = message;
    }

    public JobEvent(String string) {

    }


    public List<Job> getMessage() {
        return message;
    }
}
