package com.minty.deck.ui.home;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.minty.deck.R;
import com.minty.deck.adapters.StoryAdapter;
import com.minty.deck.adapters.TweetAdapter;
import com.minty.deck.databinding.FragmentHomeBinding;
import com.minty.deck.interfaces.ITwitterApi;
import com.minty.deck.models.User;
import com.minty.deck.models.UserModel;
import com.minty.deck.models.UserResponse;
import com.minty.deck.utils.ApiClient;
import com.minty.deck.utils.Navigator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    ITwitterApi twitterApi;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerViewTweets.setLayoutManager(new LinearLayoutManager(getContext()));

        twitterApi = ApiClient.getClient();
        Call<UserResponse> call = twitterApi.getFollowers("__omondi");

        call.enqueue(new retrofit2.Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, retrofit2.Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse userResponse = response.body();
                    List<User> users = userResponse.getUsers();
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                            LinearLayoutManager.HORIZONTAL, false);
                    binding.recyclerView.setLayoutManager(linearLayoutManager);
                    binding.recyclerView.setAdapter(new StoryAdapter(getContext(), users));
                } else {
                    Log.d("HomeFragment", "onResponse: Status Code" + response.code());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("HomeFragment", "onFailure: " + t.getMessage());
            }
        });


//
//        homeViewModel.getTweetList().observe(getViewLifecycleOwner(), tweetList -> {
//            Log.d("HomeFragment", "tweetList: " + tweetList.size());
//            binding.recyclerViewTweets.setAdapter(new TweetAdapter(getContext(), tweetList));
//        });
        final int originalHeight = binding.recyclerViewTweets.getLayoutParams().height;
        final int originalHeight2 = binding.linearLayout.getLayoutParams().height;
        //Remove stories from view on scroll up
        binding.recyclerViewTweets.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    binding.linearLayout.getLayoutParams().height = 0;
                    binding.linearLayout.setVisibility(View.GONE);
                    binding.recyclerViewTweets
                            .getLayoutParams()
                            .height = ViewGroup.LayoutParams.MATCH_PARENT;
                } else {
                    binding.recyclerViewTweets.getLayoutParams().height = originalHeight;
                    binding.linearLayout.getLayoutParams().height = originalHeight2;
                    binding.linearLayout.setVisibility(View.VISIBLE);
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
}