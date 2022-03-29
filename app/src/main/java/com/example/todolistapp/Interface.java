package com.example.todolistapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Interface {

    @Query("SELECT * FROM Tasks")
    List<Tasks> getAllTasksList();

    @Insert
    void insertDataIntoTaskList(Tasks inserttask);

    @Query("DELETE FROM Tasks WHERE taskId = :taskId")
    void deleteTaskFromId(int taskId);


}
