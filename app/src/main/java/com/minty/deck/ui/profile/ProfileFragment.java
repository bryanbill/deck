package com.minty.deck.ui.profile;

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

import com.bumptech.glide.Glide;
import com.minty.deck.R;
import com.minty.deck.adapters.TweetAdapter;
import com.minty.deck.databinding.FragmentProfileBinding;
import com.minty.deck.ui.home.HomeViewModel;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel notificationsViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        Glide.with(this)
                .load("https://images.unsplash.com/photo-1653595771646-0c74abc43265?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxlZGl0b3JpYWwtZmVlZHwzN3x8fGVufDB8fHx8&auto=format&fit=crop&w=500&q=60")
                .into(binding.profileImage);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerViewTweets.setLayoutManager(layoutManager);

        HomeViewModel homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        homeViewModel.getTweetList().observe(getViewLifecycleOwner(), tweetList -> {
            TweetAdapter adapter = new TweetAdapter(ProfileFragment.this.getContext(), tweetList);
            binding.recyclerViewTweets.setAdapter(adapter);
        });

        final int recyclerHeight = binding.recyclerViewTweets.getHeight();
        final int materialCardHeight = binding.materialCardView.getHeight();
        final int profileImageHeight = binding.profileImage.getHeight();
        final int userNameHeight = binding.userName.getHeight();
        //Check if recycler view has been scrolled to the bottom
        binding.recyclerViewTweets.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    binding.materialCardView.getLayoutParams().height = 0;
                    binding.profileName.getLayoutParams().height = 0;
                    binding.userName.getLayoutParams().height = 0;
                    binding.materialCardView.setVisibility(View.GONE);
                    binding.profileName.setVisibility(View.GONE);
                    binding.userName.setVisibility(View.GONE);
                    binding.recyclerViewTweets
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