package com.example.clientpockebaseregion.Model;

import com.google.gson.annotations.SerializedName;

public class Classroom {

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("created")
    public String created;

    public Classroom(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", created='" + created + '\'' +
                '}';
    }
}
