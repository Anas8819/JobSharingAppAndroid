package com.example.anas.jobsharingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.anas.jobsharingapp.Model.Job;
import com.google.gson.Gson;

public class AddActivity extends AppCompatActivity {
    EditText title;
    EditText description;
    EditText organization;
    EditText salary;
    EditText type;
    EditText date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);






        final Job job = new Job();

        final Gson gson = new Gson();
        Button submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                title = (EditText) findViewById(R.id.title);
                description = (EditText) findViewById(R.id.description);
                organization = (EditText) findViewById(R.id.organization);
                salary = (EditText) findViewById(R.id.salary);
                type = (EditText) findViewById(R.id.type);
                date = (EditText) findViewById(R.id.date);


                final String titl = title.getText().toString();
                final String descriptio = description.getText().toString();
                final String organizatio =organization.getText().toString();
                final String salar = salary.getText().toString();
                final String typ = type.getText().toString();
                final String  dat = date.getText().toString();




                job.setUserId(1000);
                job.setTitle(titl);
                job.setDate(dat);
                job.setDescription(descriptio);
                job.setOrganization(organizatio);
                job.setSalary(salar);
                job.setType(typ);
                String string = gson.toJson(job);
                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                intent.putExtra("Job",string);
                startActivity(intent);


            }
        });


    }
}
