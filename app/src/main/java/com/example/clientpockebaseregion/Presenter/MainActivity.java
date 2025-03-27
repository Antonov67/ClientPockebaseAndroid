package com.example.clientpockebaseregion.Presenter;

import static com.example.clientpockebaseregion.Utils.BASE_URL;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clientpockebaseregion.Controller.API;
import com.example.clientpockebaseregion.Model.Classroom;
import com.example.clientpockebaseregion.Model.ResponseClassrooms;
import com.example.clientpockebaseregion.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button addClassBtn, resetClassroomsBtn;
    EditText classField;
    ListView classroomList;
    ArrayAdapter<String> adapter;
    static List<String> classrooms;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        addClassBtn = findViewById(R.id.addClassroomButton);
        resetClassroomsBtn = findViewById(R.id.resetClassrooms);
        classroomList = findViewById(R.id.classrooms);
        API api = retrofit.create(API.class);
        classrooms = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, classrooms);
        classroomList.setAdapter(adapter);

        resetClassroomsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<ResponseClassrooms> call = api.getClassrooms();
                call.enqueue(new Callback<ResponseClassrooms>() {
                    @Override
                    public void onResponse(Call<ResponseClassrooms> call, Response<ResponseClassrooms> response) {

                        if (response.isSuccessful()) {
                            classrooms.clear();
                            for (Classroom classroom : response.body().classrooms) {
                                classrooms.add(classroom.id + " " + classroom.name + " " + classroom.created);
                            }
                            adapter.notifyDataSetChanged();
                        }

                    }
                    @Override
                    public void onFailure(Call<ResponseClassrooms> call, Throwable throwable) {

                    }
                });

            }
        });


    }
}