package com.example.anas.jobsharingapp.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anas.jobsharingapp.DetailsActivity;
import com.example.anas.jobsharingapp.Model.Job;
import com.example.anas.jobsharingapp.R;
import com.google.gson.Gson;

import java.util.List;

/**
 * Created by Anas on 11/11/2017.
 */

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.MyViewHolder> {

    public List<Job> jobsDetailList;
    Activity context;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView add;
        public Button remove;
        public RelativeLayout item;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            remove = itemView.findViewById(R.id.remove);
            item = itemView.findViewById(R.id.item);


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
        final Job job = jobsDetailList.get(position);
        holder.title.setText(job.getTitle());
        holder.item.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Job job = jobsDetailList.get(position);
                Gson gson = new Gson();
                String string = gson.toJson(job);
                int y=jobsDetailList.size();
                Intent intent = new Intent(context,DetailsActivity.class);
                intent.putExtra("Job",string);
                context.startActivity(intent);
            }
        });
        holder.remove.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Job job = jobsDetailList.get(position);
                jobsDetailList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,jobsDetailList.size());
                Toast.makeText(context, "Removed : " + job, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return jobsDetailList.size();
    }
}
