package com.example.anas.jobsharingapp.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anas.jobsharingapp.DetailsActivity;
import com.example.anas.jobsharingapp.EditActivity;
import com.example.anas.jobsharingapp.JobDetail;
import com.example.anas.jobsharingapp.Model.Job;
import com.example.anas.jobsharingapp.R;
import com.example.anas.jobsharingapp.Service.ServiceGenerator;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Anas on 11/11/2017.
 */

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.MyViewHolder> {

    private static final String TAG = "MTAG";
    public List<Job> jobsDetailList;
    Activity context;
    public Call<Job> JobList;
    private JobDetail service = ServiceGenerator.createService(JobDetail.class);

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView add;
        public Button remove;
        public  Button edit;
        public RelativeLayout item;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            remove = itemView.findViewById(R.id.remove);
            item = itemView.findViewById(R.id.item);
            edit = itemView.findViewById(R.id.edit);
        }
    }

    public JobAdapter(List<Job> jobDetailList, Activity context) {

        this.jobsDetailList = jobDetailList;
        int x=jobDetailList.size();
        this.context = context;
    }
    public List<Job> changeset(List<Job> jobDetailList){
        this.jobsDetailList = jobDetailList;
        int x=this.jobsDetailList.size();
        this.notifyDataSetChanged();
        return this.jobsDetailList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        List<Job> jobsDetailList1 = jobsDetailList;
        final Job job = jobsDetailList.get(position);
        holder.title.setText(job.getTitle());
        holder.item.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Job job = jobsDetailList.get(position);
                Gson gson = new Gson();
                String string = gson.toJson(job);
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtra("Job",string);
                context.startActivity(intent);
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                final Job job = jobsDetailList.get(position);
                Integer temp = job.getUserId();
                JobList = service.deleteJob(temp);
                JobList.enqueue(new Callback<Job>() {
                    @Override
                    public void onResponse(Call<Job> call, Response<Job> response) {
                        Log.d(TAG, "onResponse: delete");
                        Toast.makeText(context, "Removed : " + job, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Job> call, Throwable t) {
                        Toast.makeText(context, "Error while Removing : " + job, Toast.LENGTH_SHORT).show();
                    }
                });
                jobsDetailList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,jobsDetailList.size());

            }
        });
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Job job = jobsDetailList.get(position);
                Gson gson = new Gson();
                String string = gson.toJson(job);
                Intent intent = new Intent(context,EditActivity.class);
                intent.putExtra("Edit",string);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return jobsDetailList.size();
    }
}
