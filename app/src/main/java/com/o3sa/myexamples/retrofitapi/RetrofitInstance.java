package com.o3sa.myexamples.retrofitapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {


    static  String BaseURL="http://o3sa.co.in/demos/mobipug/";

    public static  Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();





}
