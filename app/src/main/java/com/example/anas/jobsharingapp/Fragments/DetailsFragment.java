package com.example.anas.jobsharingapp.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anas.jobsharingapp.Event;
import com.example.anas.jobsharingapp.Model.Job;
import com.example.anas.jobsharingapp.R;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class DetailsFragment extends Fragment {
    EventBus test;
    View rootView;
    Gson gson;
    TextView title,description,organization,type,salary,date;
    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        gson = new Gson();
        EventBus.getDefault().register(this);
        rootView = inflater.inflate(R.layout.fragment_details, container, false);
        title = rootView.findViewById(R.id.title);
        description = rootView.findViewById(R.id.description);
        organization = rootView.findViewById(R.id.organization);
        type = rootView.findViewById(R.id.type);
        salary = rootView.findViewById(R.id.salary);
        date = rootView.findViewById(R.id.date);
        return rootView;
    }

    @Subscribe
    public void test(Event event) {
        String str = event.getMessage();
        Job job = gson.fromJson(str, Job.class);
        title.setText("Title: " + job.getTitle().toString());
        description.setText(job.getDescription().toString());
        organization.setText("Organization :" + job.getOrganization().toString());
        salary.setText("Salary: " + job.getSalary().toString());
        type.setText("Job Type: " + job.getType().toString());
        date.setText("Last Date: " + job.getDate().toString());
    }

}
