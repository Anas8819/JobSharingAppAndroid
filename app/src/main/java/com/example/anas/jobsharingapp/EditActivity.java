package com.example.anas.jobsharingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.anas.jobsharingapp.Model.Job;
import com.example.anas.jobsharingapp.Service.ServiceGenerator;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {


    private static final String TAG = "MTAG";
    public Call<Job> JobList;
    private JobDetail service = ServiceGenerator.createService(JobDetail.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        final EditText title = (EditText) findViewById(R.id.title);
        final EditText description = (EditText) findViewById(R.id.description);
        final EditText organization = (EditText) findViewById(R.id.organization);
        final EditText type = (EditText) findViewById(R.id.type);
        final EditText salary = (EditText) findViewById(R.id.salary);
        final EditText date = (EditText) findViewById(R.id.date);
        Button submit = (Button) findViewById(R.id.submit);

        Gson gson = new Gson();
        final String string = getIntent().getStringExtra("Edit");
        final Job job = gson.fromJson(string, Job.class);

        title.setText(job.getTitle());
        description.setText(job.getDescription());
        organization.setText(job.getOrganization());
        type.setText(job.getType());
        salary.setText(job.getSalary().toString());
        date.setText(job.getDate());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                job.setTitle(title.getText().toString().trim());
                job.setDescription(description.getText().toString().trim());
                job.setOrganization(organization.getText().toString().trim());
                job.setType(type.getText().toString().trim());
                job.setSalary(Integer.valueOf(salary.getText().toString().trim()));
                job.setDate(date.getText().toString().trim());

                int id = job.getUserId();
                String titl=job.getTitle();
                String des=job.getDescription();
                String org=job.getOrganization();
                String typ=job.getType();
                int sal= Integer.parseInt(salary.getText().toString().trim());
                String dat=job.getDate();

                JobList = service.editJob(id,titl,des,org,typ,sal,dat);
                JobList.enqueue(new Callback<Job>() {
                    @Override
                    public void onResponse(Call<Job> call, Response<Job> response) {
                        Log.d(TAG, "onResponse: edit"+ response.body());
                    }

                    @Override
                    public void onFailure(Call<Job> call, Throwable t) {
                        Log.d(TAG, "onFailure: Edit Failed");
                    }
                });
                Intent intent = new Intent(EditActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
