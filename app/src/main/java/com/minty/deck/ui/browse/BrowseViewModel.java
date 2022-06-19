package com.minty.deck.ui.browse;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.minty.deck.models.HashtagModel;

import java.util.ArrayList;
import java.util.List;

public class BrowseViewModel extends ViewModel {

    private final MutableLiveData<List<HashtagModel>> mHashtag;
    private final List<HashtagModel> mHashtagList = new ArrayList<>();

    public BrowseViewModel() {
        mHashtag = new MutableLiveData<>();
        mHashtagList.add(new HashtagModel("#android", 1));
        mHashtagList.add(new HashtagModel("#java", 1));
        mHashtagList.add(new HashtagModel("#kotlin", 1));
        mHashtagList.add(new HashtagModel("#c", 1));
        mHashtagList.add(new HashtagModel("#c++", 1));
        mHashtagList.add(new HashtagModel("#c#", 1));
        mHashtagList.add(new HashtagModel("#php", 1));
        mHashtagList.add(new HashtagModel("#python", 1));
        mHashtagList.add(new HashtagModel("+", 1));

        mHashtag.setValue(mHashtagList);
    }

    public LiveData<List<HashtagModel>> getHashtag() {
        return mHashtag;
    }
}