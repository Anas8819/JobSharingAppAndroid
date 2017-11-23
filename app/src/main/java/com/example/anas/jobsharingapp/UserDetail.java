package com.example.anas.jobsharingapp;

import com.example.anas.jobsharingapp.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Anas on 23/11/2017.
 */

public interface UserDetail {

    @GET("login")
    Call<List<User>> getUserList();

    @GET("login/{login}")
    Call<User> getUser(@Path("user") int id);

    @POST("login")
    @FormUrlEncoded
    Call<User> login(@Field("email") String email,
                    @Field("password") String password
    );
    @POST("login")
    Call<User> login(@Body User user);
}
