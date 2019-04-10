package com.example.android.todoapp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.android.todoapp.db.Task;
import com.example.android.todoapp.db.TaskViewModel;

import java.util.Date;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import static com.example.android.todoapp.Constants.PRIORITY_HIGH;
import static com.example.android.todoapp.Constants.PRIORITY_LOW;
import static com.example.android.todoapp.Constants.PRIORITY_MEDIUM;

public class AddTaskActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveTask();
        finish();
    }

    private void saveTask() {
        EditText descriptionField = findViewById(R.id.et_task_description);
        String description = descriptionField.getText().toString();

        if (!TextUtils.isEmpty(description.trim())) {
            int priority = getSelectedPriority();
            Task task = new Task(description, priority, new Date());

            TaskViewModel viewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
            viewModel.insertTask(task);

            Toast.makeText(this, "Task Saved!!!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Task Not Saved???", Toast.LENGTH_SHORT).show();
        }

    }

    private int getSelectedPriority() {
        RadioGroup rb = findViewById(R.id.rg_priority);

        int checkedButtonId = rb.getCheckedRadioButtonId();
        switch (checkedButtonId) {
            case R.id.priority_high:
                return PRIORITY_HIGH;
            case R.id.priority_medium:
                return PRIORITY_MEDIUM;
            case R.id.priority_low:
                return PRIORITY_LOW;
            default:
                return PRIORITY_HIGH;
        }
    }
}
