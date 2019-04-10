package com.example.android.todoapp.db;

import android.content.Context;
import android.os.AsyncTask;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class AppDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "todoList";
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                instance = Room.databaseBuilder(
                        context.getApplicationContext(),
                        AppDatabase.class,
                        DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build();
            }
        }

        return instance;
    }

    public void deleteRoomDB() {
        instance = null;
    }

    public abstract TaskDao getTaskDao();

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask().execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            TaskDao dao = instance.getTaskDao();

            dao.insertTask(new Task("PrePopulated Task 1", 1, new Date()));
            dao.insertTask(new Task("PrePopulated Task 2", 2, new Date()));
            dao.insertTask(new Task("PrePopulated Task 3", 3, new Date()));
            dao.insertTask(new Task("PrePopulated Task 4", 2, new Date()));
            dao.insertTask(new Task("PrePopulated Task 5", 3, new Date()));

            return null;
        }
    }
}
