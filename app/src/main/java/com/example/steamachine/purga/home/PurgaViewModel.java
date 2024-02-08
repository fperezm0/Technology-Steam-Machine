package com.example.steamachine.purga.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PurgaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PurgaViewModel() {

    }

    public LiveData<String> getText() {
        return mText;
    }
}