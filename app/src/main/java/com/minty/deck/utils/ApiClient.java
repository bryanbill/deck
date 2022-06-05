package com.minty.deck.utils;

import com.minty.deck.interfaces.ITwitterApi;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;
    public static ITwitterApi getClient() {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request newRequest  = chain.request().newBuilder()
                                    .addHeader("Authorization", "Bearer AAAAAAAAAAAAAAAAAAAAAFrjcwEAAAAACV6LKOdnWT6c4G0EJ%2FY6dcWFSMA%3DIFOSAReRd54kZLGPjYVy6Sv6mYfSFdxmVWgyaH1pQmCexKUJ7d")
                                    .build();
                            return chain.proceed(newRequest);
                        }


                    })
                    .build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.twitter.com/1.1/")
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ITwitterApi.class);
    }
}
