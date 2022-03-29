package com.example.todolistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AddTask extends AppCompatActivity {

    EditText addTaskTitle,addTaskDescription;
    TextView taskDate;
    int mYear, mMonth, mDay;
    DatePickerDialog datePickerDialog;
    Button addTask;
    private String TAG= getClass().getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        addTaskTitle = findViewById(R.id.addTaskTitle);
        addTaskDescription = findViewById(R.id.addTaskDescription);
        taskDate = findViewById(R.id.taskDate);
        addTask = findViewById(R.id.addTask);




        taskDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(AddTask.this,
                        (view1, year, monthOfYear, dayOfMonth) -> {
                            taskDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            datePickerDialog.dismiss();
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });


        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppDatabase appDatabase = Room.databaseBuilder(AddTask.this,AppDatabase.class,"TodoList").allowMainThreadQueries().build();
                Tasks tasks = new Tasks();

                if(validateFields()) {
                    tasks.taskTitle = addTaskTitle.getText().toString();
                    tasks.taskDescrption = addTaskDescription.getText().toString();
                    tasks.date = taskDate.getText().toString();
                    appDatabase.Interface().insertDataIntoTaskList(tasks);
                    setResult(RESULT_OK);
                    finish();

                    Log.e(TAG, "onClick: "+tasks.taskTitle );
                }
            }
        });




    }

    public boolean validateFields() {
        if(addTaskTitle.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(AddTask.this, "Please enter a valid title", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(addTaskDescription.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(AddTask.this, "Please enter a valid description", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(taskDate.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(AddTask.this, "Please enter date", Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            return true;
        }
    }
}