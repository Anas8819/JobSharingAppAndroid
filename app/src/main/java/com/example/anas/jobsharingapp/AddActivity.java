package com.example.anas.jobsharingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.anas.jobsharingapp.Model.Job;
import com.google.gson.Gson;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);



        final Activity context = this;
        final String title = findViewById(R.id.title).toString();
        final String description = findViewById(R.id.description).toString();
        final String organization = findViewById(R.id.organization).toString();
        final String salary = findViewById(R.id.salary).toString();
        final String type = findViewById(R.id.type).toString();
        final String  date = findViewById(R.id.date).toString();
        final Job job = new Job();

        final Gson gson = new Gson();
        Button submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                job.setTitle(title);
                job.setDate(date);
                job.setDescription(description);
                job.setOrganization(organization);
                job.setSalary(salary);
                job.setType(type);
                String string = gson.toJson(job);
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("Job",string);
                context.startActivity(intent);
            }
        });


    }
}
