package com.example.anas.jobsharingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.anas.jobsharingapp.Adapter.JobAdapter;
import com.example.anas.jobsharingapp.Model.Job;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MTAG";

    private RecyclerView recyclerView;
    private JobAdapter mAdapter;
    Gson gson;

    List<Job> jobDetailList1 = new ArrayList<>();

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void test(JobEvent customEvent) {
        jobDetailList1 = customEvent.getMessage();

        mAdapter.changeset(jobDetailList1);

    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);




        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new JobAdapter(jobDetailList1, MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

        Button add = (Button) findViewById(R.id.add);
        final Activity context = this;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddActivity.class);
                context.startActivity(intent);
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.10.3:8000/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JobDetail service = retrofit.create(JobDetail.class);

        Call<List<Job>> JobList = service.getJobList();
        JobList.enqueue(new Callback<List<Job>>() {
            @Override
            public void onResponse(Call<List<Job>> call, Response<List<Job>> response) {
                Log.d(TAG, "onResponse() called with: call = [" + call + "], response = [" + response + "]");

                List<Job> jobDetailList = response.body();
                JobEvent JobEvent = new JobEvent(jobDetailList);
                EventBus.getDefault().post(JobEvent);
            }

            @Override
            public void onFailure(Call<List<Job>> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");

            }
        });



        Log.d(TAG, "end of oncreate method: ");
    }



}
