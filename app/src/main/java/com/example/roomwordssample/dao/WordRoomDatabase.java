package com.example.roomwordssample.dao;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.roomwordssample.pojo.Word;

@Database(entities = {Word.class},version = 1,exportSchema = false)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();
    private static WordRoomDatabase INSTANCE;

    public static WordRoomDatabase getWordRoomDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (WordRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class,"word_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void,Void,Void>{

        private final WordDao wordDao;
        String[] words = {"dolphin","crocodile","cobra"};

        PopulateDbAsync(WordRoomDatabase db){
            wordDao = db.wordDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
           wordDao.deleteAll();
           for(int i=0;i<words.length;i++){
               Word word = new Word(words[i]);
               wordDao.insert(word);
           }
            return null;
        }
    }

}
