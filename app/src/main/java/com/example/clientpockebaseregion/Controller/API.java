package com.example.clientpockebaseregion.Controller;

import com.example.clientpockebaseregion.Model.Classroom;
import com.example.clientpockebaseregion.Model.ResponseClassrooms;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {

    @GET("collections/class/records")
    Call<ResponseClassrooms> getClassrooms();

    @POST("collections/class/records")
    Call<Classroom> createClassroom(@Body Classroom classroom);

}
