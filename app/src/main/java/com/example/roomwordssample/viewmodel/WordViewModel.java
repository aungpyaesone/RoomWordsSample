package com.example.roomwordssample.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.roomwordssample.pojo.Word;
import com.example.roomwordssample.repository.WordRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepository wordRepository;
    private LiveData<List<Word>> mWordList;
    public WordViewModel(@NonNull Application application) {
        super(application);
        wordRepository = new WordRepository(application);
        mWordList = wordRepository.getAllwords();
    }

    public LiveData<List<Word>> getmWordList() {
        return mWordList;
    }

    public void insert(Word word){
        wordRepository.insertWord(word);
    }
}
