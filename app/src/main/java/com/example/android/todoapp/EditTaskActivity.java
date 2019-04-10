package com.example.android.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.android.todoapp.db.AppDatabase;
import com.example.android.todoapp.db.EditTaskViewModel;
import com.example.android.todoapp.db.EditTaskViewModelFactory;
import com.example.android.todoapp.db.Task;

import java.util.Date;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import static com.example.android.todoapp.Constants.PRIORITY_HIGH;
import static com.example.android.todoapp.Constants.PRIORITY_LOW;
import static com.example.android.todoapp.Constants.PRIORITY_MEDIUM;

public class EditTaskActivity extends AppCompatActivity {
    private static final String TAG = EditTaskActivity.class.getSimpleName();

    public static final String TASK_ID_KEY = "taskIdKey";
    // Constant for default task id to be used when not in update mode
    private static final int DEFAULT_TASK_ID = -1;
    // Extra for the task ID to be received after rotation
    public static final String INSTANCE_TASK_ID = "instanceTaskId";

    EditTaskViewModel viewModel;
    private Task task;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        long taskId = DEFAULT_TASK_ID;

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_TASK_ID)) {
            taskId = savedInstanceState.getLong(INSTANCE_TASK_ID, DEFAULT_TASK_ID);
        }

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(TASK_ID_KEY)) {
            taskId = intent.getLongExtra(TASK_ID_KEY, DEFAULT_TASK_ID);

            // We have to declare db here because it required context and our ViewModel does
            // not have context to initialize it and it is not good idea to pass context
            // to ViewModel through constructor as it can leak memory.
            AppDatabase db = AppDatabase.getInstance(this);
            EditTaskViewModelFactory factory = new EditTaskViewModelFactory(db, taskId);

            viewModel = ViewModelProviders.of(this, factory).get(EditTaskViewModel.class);
            viewModel.getTask().observe(this, new Observer<Task>() {
                @Override
                public void onChanged(Task task) {
                    viewModel.getTask().removeObserver(this);
                    populateUI(task);
                }
            });
        }
    }

    // If the process is killed by the system
    // Q: Can previous activity intent survive the process death?
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong(INSTANCE_TASK_ID, task.getId());
        super.onSaveInstanceState(outState);
    }

    private void populateUI(Task task) {
        this.task = task;

        EditText editText = findViewById(R.id.et_task_description);
        editText.setText(task.getDescription());


        RadioGroup radioGroup = findViewById(R.id.rg_priority);
        switch (task.getPriority()) {
            case PRIORITY_HIGH:
                radioGroup.check(R.id.priority_high);
                break;
            case PRIORITY_MEDIUM:
                radioGroup.check(R.id.priority_medium);
                break;
            case PRIORITY_LOW:
                radioGroup.check(R.id.priority_low);
                break;
            default:
                String message = "Priority value is not between 1-3";
                Log.e(TAG, message + task.getPriority());
                break;
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        saveTaskIfEdited();
    }

    private void saveTaskIfEdited() {
        EditText editText = findViewById(R.id.et_task_description);
        String newDescription = editText.getText().toString();
        int newPriority = getSelectedPriority();

        // Checking if user has edited task description or task priority
        if (!newDescription.equals(task.getDescription()) ||
                newPriority != task.getPriority()) {
            Task newTask = new Task(task.getId(), newDescription, newPriority, new Date());
            viewModel.editTask(newTask);

            Toast.makeText(this, "Task is Updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Noting is updated!!!", Toast.LENGTH_SHORT).show();
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
