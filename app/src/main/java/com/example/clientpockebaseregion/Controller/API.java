package com.example.clientpockebaseregion.Controller;

import com.example.clientpockebaseregion.Model.ResponseClassrooms;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {

    @GET("collections/class/records")
    Call<ResponseClassrooms> getClassrooms();

}
