package com.example.roomwordssample.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.roomwordssample.pojo.Word;

import java.util.List;

@Dao
interface WordDao {

    @Insert
    void insert(Word word);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("select * from word_table order by word asc")
    LiveData<List<Word>> getAllWord();
}