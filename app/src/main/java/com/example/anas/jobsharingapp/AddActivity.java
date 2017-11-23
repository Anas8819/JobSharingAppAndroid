package com.example.anas.jobsharingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.anas.jobsharingapp.Model.Job;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddActivity extends AppCompatActivity {
    private static final String TAG = "MTAG";


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
        final String id = getIntent().getStringExtra("user");





        final Job job = new Job();

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


                final String titl = title.getText().toString().trim();
                final String descriptio = description.getText().toString().trim();
                final String organizatio =organization.getText().toString().trim();
                final String salar = salary.getText().toString().trim();
                final String typ = type.getText().toString().trim();
                final String  dat = date.getText().toString().trim();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.10.3:8000/api/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                JobDetail service = retrofit.create(JobDetail.class);

                Call<Job> JobList = service.saveJob(titl,descriptio,organizatio,typ, Integer.parseInt(salar),dat);
                JobList.enqueue(new Callback<Job>() {
                    @Override
                    public void onResponse(Call<Job> call, Response<Job> response) {
                        Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");

                        Intent intent = new Intent(AddActivity.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Job> call, Throwable t) {
                        Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
                    }

                });


                Intent intent = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intent);


            }
        });


    }
}
