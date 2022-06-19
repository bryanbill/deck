package com.minty.deck.ui.browse;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.minty.deck.adapters.HashtagAdapter;
import com.minty.deck.adapters.TweetAdapter;
import com.minty.deck.databinding.FragmentBrowseBinding;
import com.minty.deck.interfaces.IHandler;
import com.minty.deck.interfaces.ITwitterApi;
import com.minty.deck.models.TweetResponse;
import com.minty.deck.ui.home.HomeViewModel;
import com.minty.deck.utils.ApiClient;

import retrofit2.Call;

public class BrowseFragment extends Fragment implements IHandler {

    private FragmentBrowseBinding binding;
    ITwitterApi twitterApi;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        BrowseViewModel browseViewModel =
                new ViewModelProvider(this).get(BrowseViewModel.class);

        binding = FragmentBrowseBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);

        binding.recyclerHash.setLayoutManager(gridLayoutManager);
        browseViewModel.getHashtag().observe(getViewLifecycleOwner(), hashtagList -> {
            Log.d("BrowseFragment", "hashtagList: " + hashtagList.size());
            binding.recyclerHash.setAdapter(new HashtagAdapter(getContext(), hashtagList, this));
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewTweets.setLayoutManager(linearLayoutManager);
        browseTweets("Kenya");

        binding.recyclerViewTweets.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
              //  Toast.makeText(getContext(), "Scrolled: " + dy, Toast.LENGTH_SHORT).show();
                if (dy > 0) {
                    binding.hashtagLayout.setVisibility(View.GONE);
                } else if(!recyclerView.canScrollVertically(-1)) {
                    binding.hashtagLayout.setVisibility(View.VISIBLE);
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onHashtagClicked(String hashtag) {
        browseTweets(hashtag);
    }

    public void browseTweets(String hashTag){
        twitterApi = ApiClient.getClient();
        Call<TweetResponse> call = twitterApi.browseTweets(hashTag);
        call.enqueue(new retrofit2.Callback<TweetResponse>() {
            @Override
            public void onResponse(Call<TweetResponse> call, retrofit2.Response<TweetResponse> response) {
                if (response.isSuccessful()) {
                    TweetResponse tweetResponse = response.body();
                    if(binding !=null){
                        binding.recyclerViewTweets.setAdapter(new TweetAdapter(getContext(), tweetResponse.getStatuses()));
                    }
                } else {
                    Log.d("BrowseFragment", "onResponse: Status Code" + response.code());
                }
            }

            @Override
            public void onFailure(Call<TweetResponse> call, Throwable t) {
                Log.d("BrowseFragment", "onFailure: " + t.getMessage());
            }
        });
    }
}