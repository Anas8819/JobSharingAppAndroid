package com.example.anas.jobsharingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;


public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String string = getIntent().getStringExtra("Job");
        Event jobEvent = new Event(string);
        EventBus.getDefault().post(jobEvent);
    }
}
