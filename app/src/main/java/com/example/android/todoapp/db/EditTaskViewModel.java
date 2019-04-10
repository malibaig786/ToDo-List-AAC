package com.example.android.todoapp.db;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class EditTaskViewModel extends ViewModel {

    private LiveData<Task> task;
    private TaskDao taskDao;

    public EditTaskViewModel(AppDatabase db, long taskId) {
        taskDao = db.getTaskDao();
        task = taskDao.loadTaskById(taskId);
    }

    public LiveData<Task> getTask() {
        return task;
    }

    public void editTask(Task task) {
        new EditAsyncTask().execute(task);
    }

    private class EditAsyncTask extends AsyncTask<Task, Void, Void> {

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.updateTask(tasks[0]);
            return null;
        }
    }
}
