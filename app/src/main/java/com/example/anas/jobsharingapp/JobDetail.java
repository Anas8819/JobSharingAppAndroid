package com.example.anas.jobsharingapp;

import com.example.anas.jobsharingapp.Model.Job;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Anas on 11/11/2017.
 */

public interface JobDetail {
    @GET("job")
    Call<List<Job>> getJobList();

    @GET("job/{job}")
    Call<Job> getjob(@Path("job") int id);

    @POST("job")
    @FormUrlEncoded
    Call<Job> saveJob(@Field("title") String title,
                      @Field("description") String description,
                      @Field("organization") String organization,
                      @Field("type") String type,
                      @Field("salary") int salary,
                      @Field("date") String date
                      //@Field("user_id") Integer user_id
    );
    @POST("job")
    Call<Job> saveJob(@Body Job job);

    @GET("getbytype/{id}")
    Call<List<Job>> getbytype(@Path("id") String id);

    @DELETE("job/{job}")
    Call<Job> deleteJob(@Path("job") int id);

    @PUT("job/{job}")
    @FormUrlEncoded
    Call<Job> editJob(@Path("job") int id,
                      @Field("title") String title,
                      @Field("description") String description,
                      @Field("organization") String organization,
                      @Field("type") String type,
                      @Field("salary") int salary,
                      @Field("date") String date
    );
}
