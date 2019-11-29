package com.example.roomwordssample.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.roomwordssample.dao.WordDao;
import com.example.roomwordssample.dao.WordRoomDatabase;
import com.example.roomwordssample.pojo.Word;

import java.util.List;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mWordList;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getWordRoomDatabase(application);
        mWordDao = db.wordDao();
        mWordList = mWordDao.getAllWord();
    }

    public LiveData<List<Word>> getAllwords() {
        return mWordList;
    }

    public void insertWord(Word word) {
        new insretAsynTask(mWordDao).execute(word);
    }

    private static class insretAsynTask extends AsyncTask<Word, Void, Void> {
        private WordDao wordDao;

        private insretAsynTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insert(words[0]);
            return null;
        }
    }
}
