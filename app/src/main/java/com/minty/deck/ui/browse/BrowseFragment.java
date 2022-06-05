package com.minty.deck.ui.browse;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.minty.deck.adapters.HashtagAdapter;
import com.minty.deck.adapters.TweetAdapter;
import com.minty.deck.databinding.FragmentBrowseBinding;
import com.minty.deck.ui.home.HomeViewModel;

public class BrowseFragment extends Fragment {

    private FragmentBrowseBinding binding;

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
            binding.recyclerHash.setAdapter(new HashtagAdapter(getContext(), hashtagList));
        });

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewTweets.setLayoutManager(linearLayoutManager);
//        homeViewModel.getTweetList().observe(getViewLifecycleOwner(), tweetList -> {
//            Log.d("HomeFragment", "tweetList: " + tweetList.size());
//            binding.recyclerViewTweets.setAdapter(new TweetAdapter(getContext(), tweetList));
//        });
        final int originalHeight = binding.hashtagTweets.getLayoutParams().height;
        final int originalHeight2 = binding.hashtagLayout.getLayoutParams().height;
        binding.recyclerViewTweets.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    binding.hashtagLayout.setVisibility(View.GONE);
                    binding.hashtagLayout.getLayoutParams().height = 0;
                    binding.hashtagTweets.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
                } else {
                    binding.hashtagLayout.getLayoutParams().height = originalHeight2;
                    binding.hashtagTweets.getLayoutParams().height = originalHeight;
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
}