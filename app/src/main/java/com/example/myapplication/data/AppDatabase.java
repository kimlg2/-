package com.example.myapplication.data;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.data.dao.DiaryDao;
import com.example.myapplication.data.dao.ReplyDao;
import com.example.myapplication.data.entity.Diary;
import com.example.myapplication.data.entity.Reply;


@Database(entities = {Diary.class, Reply.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract DiaryDao diaryDao();
    public abstract ReplyDao replyDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "database-name")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
