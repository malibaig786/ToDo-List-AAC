package com.example.android.todoapp.db;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String description;
    private int priority;
    private Date date;   // Creation or update date of task

    public Task(long id, String description, int priority, Date date) {
        this.id = id;
        this.description = description;
        this.priority = priority;
        this.date = date;
    }

    @Ignore
    public Task(String description, int priority, Date date) {
        this.description = description;
        this.priority = priority;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getPriority() {
        return priority;
    }

    public Date getDate() {
        return date;
    }

    @NonNull
    @Override
    public String toString() {
        return "ID: " + id + '\n'
                + "Description: " + description + '\n'
                + "Priority: " + priority + '\n';
    }
}
