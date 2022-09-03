package com.example.navigationdrawer.ui.other;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OtherViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public OtherViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is the other fragment na gawa ko lang");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
