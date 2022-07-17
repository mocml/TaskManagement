package com.example.taskmanagement.activity.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

public class DBClient {
    private Context mContext;
    private static DBClient mInstance;

    private AppDatabase appDatabase;

    private DBClient(Context mContext) {
        this.mContext = mContext;
        appDatabase = Room.databaseBuilder(mContext, AppDatabase.class, "Task.db")
                .fallbackToDestructiveMigration()
                .build();
    }

    public static synchronized DBClient getInstance(Context mContext) {
        if (mInstance == null) {
            mInstance = new DBClient(mContext);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
