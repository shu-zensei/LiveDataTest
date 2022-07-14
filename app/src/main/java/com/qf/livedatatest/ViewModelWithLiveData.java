package com.qf.livedatatest;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelWithLiveData extends ViewModel {

    private MutableLiveData<Integer> LikedNumber;
    private String TAG = "custom_tag";

    public MutableLiveData<Integer> getLikedNumber(){
        if(LikedNumber == null){
            LikedNumber = new MutableLiveData<>();
            LikedNumber.setValue(0);
        }
        return LikedNumber;
    }

    public void addMutableLiveData(int num){
        Log.d(TAG,"mutableLiveData value is changed");
        LikedNumber.setValue(LikedNumber.getValue() + num);
    }
}
