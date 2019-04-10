package com.example.android.todoapp.db;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TaskViewModel extends AndroidViewModel {

    private LiveData<List<Task>> taskList;
    private TaskDao taskDao;

    public TaskViewModel(@NonNull Application application) {
        super(application);

        AppDatabase db = AppDatabase.getInstance(application);
        taskDao = db.getTaskDao();
        taskList = taskDao.loadAllTasks();
    }

    public LiveData<List<Task>> getTaskList() {
        return taskList;
    }

    public void insertTask(Task task) {
        new InsertAsyncTask().execute(task);
    }

    public void deleteTask(Task task) {
        new DeleteAsyncTask().execute(task);
    }

    public Task getTask(int position) {
        Task task = taskList.getValue().get(position);
        return task;
    }


    private class InsertAsyncTask extends AsyncTask<Task, Void, Void> {

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insertTask(tasks[0]);
            return null;
        }
    }

    private class DeleteAsyncTask extends AsyncTask<Task, Void, Void> {

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.deleteTask(tasks[0]);
            return null;
        }
    }

}
