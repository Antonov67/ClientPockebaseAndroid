package com.example.clientpockebaseregion.service;

import static com.example.clientpockebaseregion.Utils.BASE_URL;

import com.example.clientpockebaseregion.Controller.API;
import com.example.clientpockebaseregion.Controller.SimpleCallback;
import com.example.clientpockebaseregion.Model.Classroom;
import com.example.clientpockebaseregion.Model.ResponseClassrooms;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private final API api;

    public RetrofitService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(API.class);
    }

    public void getAllClassrooms(SimpleCallback<ResponseClassrooms> callback) {
        Call<ResponseClassrooms> call = api.getClassrooms();
        call.enqueue(new Callback<ResponseClassrooms>() {
            @Override
            public void onResponse(Call<ResponseClassrooms> call, Response<ResponseClassrooms> response) {

                if (response.isSuccessful()) {
                    callback.load(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseClassrooms> call, Throwable throwable) {

            }
        });
    }

    public void createClassroom(Classroom classroom, SimpleCallback<Classroom> callback){
        Call<Classroom> call = api.createClassroom(classroom);
        call.enqueue(new Callback<Classroom>() {
            @Override
            public void onResponse(Call<Classroom> call, Response<Classroom> response) {
                if (response.isSuccessful()){
                    callback.load(response.body());
                }
            }

            @Override
            public void onFailure(Call<Classroom> call, Throwable throwable) {

            }
        });
    }
}
