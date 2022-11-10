package com.example.myapplication.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.myapplication.data.entity.Reply;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface ReplyDao {
    @Query("SELECT * FROM Reply WHERE boardNo = :boardNo")
    Single<List<Reply>> getAll(int boardNo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable createOrUpdate(Reply reply);

}
