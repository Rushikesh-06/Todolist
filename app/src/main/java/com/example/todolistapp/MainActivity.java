package com.example.todolistapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView addTask;
    RecyclerView taskRecycler;
    public static int REQUST_ADD_TASK = 100;
    private String TAG=getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTask = findViewById(R.id.addTaskbtn);
        taskRecycler = findViewById(R.id.taskRecycler);

        getdatafromdatabase();



        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, AddTask.class),REQUST_ADD_TASK);

            }
        });


    }

    private void getdatafromdatabase() {
        AppDatabase getdatabasevalue = Room.databaseBuilder(MainActivity.this,AppDatabase.class,"TodoList").allowMainThreadQueries().build();
        List<Tasks> datalist = new ArrayList<>();
        datalist = getdatabasevalue.Interface().getAllTasksList();

        taskRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        taskRecycler.setAdapter(new TaskAdapter(MainActivity.this,datalist));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e(TAG, "onActivityResult: requestCode -"+requestCode );
        Log.e(TAG, "onActivityResult: resultCode -"+resultCode );

        if (requestCode == REQUST_ADD_TASK){
            if (resultCode == -1){
                getdatafromdatabase();
            }
        }
    }
}