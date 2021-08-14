package com.example.taskmaster;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insertTask(Task newTask);

    @Query("select * from Task")
    List<Task> findAll();


    @Delete
    void deleteTask(Task deleteTask);
}
