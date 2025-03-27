package com.example.clientpockebaseregion.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseClassrooms {


    @SerializedName("items")
    public List<Classroom> classrooms;

}
