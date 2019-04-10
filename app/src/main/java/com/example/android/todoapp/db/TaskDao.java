package com.example.android.todoapp.db;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TaskDao {

    @Query("SELECT* FROM Task ORDER BY priority ASC")
    LiveData<List<Task>> loadAllTasks();

    @Query("SELECT* FROM TASK WHERE id = :taskId")
    LiveData<Task> loadTaskById(long taskId);

    // void, Void, long or Long ==> These data types can be used
    // The Id of the row inserted.
    @Insert
    Void insertTask(Task task);

    // void, Void, int, Integer ==> These data types can be used
    // The number of rows effected
    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateTask(Task task);

    // void, Void, int, Integer ==> These data types can be used
    // The number of rows effected
    @Delete
    Integer deleteTask(Task task);
}
