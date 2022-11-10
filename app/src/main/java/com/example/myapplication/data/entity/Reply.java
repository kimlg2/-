package com.example.myapplication.data.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Reply implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public Integer id;

    //내용
   public String content;
    //날짜
    public long time;
    //게시글 번호
    public Integer boardNo;


    public Reply(String content, long time, Integer boardNo) {
        this.content = content;
        this.time = time;
        this.boardNo = boardNo;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", boardNo='" + boardNo + '\'' +
                '}';
    }
}
