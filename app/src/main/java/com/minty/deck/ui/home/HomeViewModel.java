package com.minty.deck.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.minty.deck.models.TweetModel;
import com.minty.deck.models.UserModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

//    private final MutableLiveData<List<UserModel>> mStoryList;
//    private final MutableLiveData<List<TweetModel>> mTweetList;
    private ArrayList<UserModel> storyList = new ArrayList<>();

    private ArrayList<TweetModel> tweetList = new ArrayList<>();

    public HomeViewModel() {
//        UserModel user1 = new UserModel("@ning", "Ning Lu",
//                "https://images.unsplash.com/photo-1653450153590-d4c545b97de2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwxNXx8fGVufDB8fHx8&auto=format&fit=crop&w=500&q=60",
//                "Another Test",
//                "Kenya");
//        UserModel user2 = new UserModel("@james", "James",
//                "https://images.unsplash.com/photo-1518791841217-8f162f1e1131?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwxNXx8fGVufDB8fHx8&auto=format&fit=crop&w=500&q=60",
//                "Another Test",
//                "Kenya");
//        UserModel user3 = new UserModel("@minty", "John Minty",
//                "https://images.unsplash.com/photo-1653471207475-4d4f62e16814?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyfHx8ZW58MHx8fHw%3D&auto=format&fit=crop&w=500&q=60",
//                "This is me",
//                "Kenya");
//        storyList.add(user1);
//        storyList.add(user2);
//        storyList.add(user3);
//
//        tweetList.add(new TweetModel("This is a breezee...",
//                "https://images.unsplash.com/photo-1653491888857-6cb8c8f0264c?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwyMnx8fGVufDB8fHx8&auto=format&fit=crop&w=500&q=60",
//                user1));
//        tweetList.add(new TweetModel("We are the best but this should be a long ass \" +\n" +
//                "pragraph, I know programming is hard but I'm not sure how to do it. We are the best but this should be a long ass \" +\n" +
//                "pragraph, I know programming is hard but I'm not sure how to do it",
//                "",
//                user2));
//        tweetList.add(new TweetModel("We are the best but this should be a long ass " +
//                "pragraph, I know programming is hard but I'm not sure how to do it. We are the best but this should be a long ass " +
//                "pragraph, I know programming is hard but I'm not sure how to do it",
//                "https://images.unsplash.com/photo-1653587193484-92463e78f7b4?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHw1NXx8fGVufDB8fHx8&auto=format&fit=crop&w=500&q=60",
//                user3));
//        mStoryList = new MutableLiveData<>();
//        mStoryList.setValue(storyList);
//        mTweetList = new MutableLiveData<>();
//        mTweetList.setValue(tweetList);
    }

//    public LiveData<List<UserModel>> getStoryList() {
//        return mStoryList;
//    }
//
//    public LiveData<List<TweetModel>> getTweetList() {
//        return mTweetList;
//    }
}