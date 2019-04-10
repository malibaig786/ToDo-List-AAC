package com.example.android.todoapp.db;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class EditTaskViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final AppDatabase db;
    private final long taskId;

    public EditTaskViewModelFactory(AppDatabase db, long taskId) {
        this.db = db;
        this.taskId = taskId;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new EditTaskViewModel(db, taskId);
    }
}
