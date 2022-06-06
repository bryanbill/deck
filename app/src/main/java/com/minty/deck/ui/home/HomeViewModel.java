package com.minty.deck.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.minty.deck.interfaces.ITwitterApi;
import com.minty.deck.models.Status;
import com.minty.deck.models.TweetModel;
import com.minty.deck.models.User;
import com.minty.deck.models.UserModel;
import com.minty.deck.models.UserResponse;
import com.minty.deck.utils.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<Status>> mTweetList;
    private ITwitterApi twitterApi;

    private List<User> userList;

    public HomeViewModel() {
        mTweetList = new MutableLiveData<>();
    }

    public LiveData<List<Status>> getTweetList() {
        return mTweetList;
    }

    public  void setUserResponse(List<User> users) {
        userList = users;
    }

    public List<Status> getTweets() {
        twitterApi = ApiClient.getClient();
        List<Status> statuses = new ArrayList<>();
        for (int i =0; i < userList.size(); i++) {
            Call<List<Status>> call2 = twitterApi.getTimelineTweets(userList.get(i).getScreenName());
            call2.enqueue(new retrofit2.Callback<List<Status>>() {
                @Override
                public void onResponse(Call<List<Status>> call,
                                       Response<List<Status>> response) {
                    if (response.isSuccessful()) {
                        statuses.addAll(response.body());
                    }
                }
                @Override
                public void onFailure(Call<List<Status>> call, Throwable t) {
                    Log.d("HomeViewModel", "onFailure: " + t.getMessage());
                }
            });
            //If end of list
            if (i == userList.size() - 1) {
                mTweetList.setValue(statuses);
            }
        }
        return statuses;
    }

}