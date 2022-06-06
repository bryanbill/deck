package com.minty.deck.interfaces;

import com.minty.deck.models.Status;
import com.minty.deck.models.TweetResponse;
import com.minty.deck.models.UserResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ITwitterApi {


    @Headers("Content-Type: application/json")
    @GET("statuses/user_timeline.json")
    Call<List<Status>> getTimelineTweets(
            @Query("screen_name") String screenName,
            @Query("count") int count
    );

    @GET("search/tweets.json")
    Call<TweetResponse> browseTweets(
            @Query("q") String query
    );

    @GET("followers/list.json")
    Call<UserResponse> getFollowers(
            @Query("screen_name") String screenName
    );

}
