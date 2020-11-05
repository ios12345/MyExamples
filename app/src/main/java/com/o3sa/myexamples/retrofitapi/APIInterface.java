package com.o3sa.myexamples.retrofitapi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIInterface {

    @POST("app/get_orders.php?")
    Call<ResponseBody> getorders(@Query("vendor_id") String vendor_id);
}
