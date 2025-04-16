package com.example.clientpockebaseregion.Presenter;

import static com.example.clientpockebaseregion.Utils.BASE_URL;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.clientpockebaseregion.Controller.API;
import com.example.clientpockebaseregion.Controller.SimpleCallback;
import com.example.clientpockebaseregion.Model.Classroom;
import com.example.clientpockebaseregion.Model.ResponseClassrooms;
import com.example.clientpockebaseregion.R;
import com.example.clientpockebaseregion.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button addClassBtn, resetClassroomsBtn;
    EditText classroomField;
    ListView classroomList;
    ArrayAdapter<String> adapter;
    static List<String> classrooms;
    RetrofitService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);



        addClassBtn = findViewById(R.id.addClassroomButton);
        resetClassroomsBtn = findViewById(R.id.resetClassrooms);
        classroomList = findViewById(R.id.classrooms);
        classroomField = findViewById(R.id.editTextClassroom);

        service = new RetrofitService();

        classrooms = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, classrooms);
        classroomList.setAdapter(adapter);

        resetClassroomsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                service.getAllClassrooms(new SimpleCallback<ResponseClassrooms>() {
                    @Override
                    public void load(ResponseClassrooms data) {
                        classrooms.clear();
                        for (Classroom c : data.classrooms) {
                            classrooms.add(c.name + " " + c.created);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });

            }
        });

        addClassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!classroomField.getText().toString().isEmpty()){
                    Classroom classroom = new Classroom(classroomField.getText().toString());
                    service.createClassroom(classroom, new SimpleCallback<Classroom>() {
                        @Override
                        public void load(Classroom data) {
                            Toast.makeText(MainActivity.this, data.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });


    }
}