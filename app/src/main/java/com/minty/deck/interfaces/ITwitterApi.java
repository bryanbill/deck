package com.minty.deck.interfaces;

import com.minty.deck.models.TweetResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ITwitterApi {
    @GET("/1.1/statuses/user_timeline.json")
    Call<TweetResponse> getTimelineTweets(
            @Query("screen_name") String screenName
    );
}
