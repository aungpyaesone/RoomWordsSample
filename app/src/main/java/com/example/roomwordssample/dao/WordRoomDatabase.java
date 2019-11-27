package com.example.roomwordssample.dao;

import android.content.Context;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.roomwordssample.pojo.Word;

@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();
    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getWordRoomDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (WordRoomDatabase.class){
              //  if(INSTANCE)
            }
        }
        return INSTANCE;
    }

}
