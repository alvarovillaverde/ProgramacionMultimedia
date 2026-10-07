package com.example.b005navigationdrawer.ui.accessible;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AccesibleViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public AccesibleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is accesible fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
