package com.example.electromartmad.ui.rating;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RatingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RatingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Feedback");
    }

    public LiveData<String> getText() {
        return mText;
    }
}