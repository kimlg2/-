package com.example.myapplication.data.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Diary implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public Integer id;
    //내용
    public String content;
    //이미지 경로
    public String path;
    //날짜
    public long time;

    //좋아요 여부
    public String scrapYn ="N";

    public Diary(String content, String path, long time) {
        this.content = content;
        this.path = path;
        this.time = time;
    }

    public boolean isScrap(){
        return scrapYn.equals("Y");
    }

    public void setScrapYn(String scrapYn) {
        this.scrapYn = scrapYn;
    }

    @Override
    public String toString() {
        return "Diary{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", path='" + path + '\'' +
                ", time=" + time +
                ", scrapYn='" + scrapYn + '\'' +
                '}';
    }
}
