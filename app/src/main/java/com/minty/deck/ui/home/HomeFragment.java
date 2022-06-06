package com.minty.deck.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.minty.deck.adapters.StoryAdapter;
import com.minty.deck.adapters.TweetAdapter;
import com.minty.deck.databinding.FragmentHomeBinding;
import com.minty.deck.models.UserModel;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private List<UserModel> storyList = new ArrayList<>();

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

        homeViewModel.getStoryList().observe(getViewLifecycleOwner(), storyList -> {
            Log.d("HomeFragment", "storyList: " + storyList.size());
            binding.recyclerView.setAdapter(new StoryAdapter(getContext(), storyList));
        });

        homeViewModel.getTweetList().observe(getViewLifecycleOwner(), tweetList -> {
            Log.d("HomeFragment", "tweetList: " + tweetList.size());
            binding.recyclerViewTweets.setAdapter(new TweetAdapter(getContext(), tweetList));
        });

        //Remove stories from view on scroll up
        binding.recyclerViewTweets.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    binding.linearLayout.getLayoutParams().height = 0;
                    binding.linearLayout.setVisibility(View.GONE);
                    binding.tweets
                            .getLayoutParams()
                            .height = ViewGroup.LayoutParams.MATCH_PARENT;
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