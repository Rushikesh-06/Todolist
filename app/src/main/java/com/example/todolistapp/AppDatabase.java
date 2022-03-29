package com.example.todolistapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Tasks.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
abstract Interface Interface();
}
