package com.example.myapplication.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.myapplication.data.entity.Diary;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;


@Dao
public interface DiaryDao {
    @Query("SELECT * FROM Diary ORDER BY time DESC")
    Single<List<Diary>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable createOrUpdate(Diary diary);

}
