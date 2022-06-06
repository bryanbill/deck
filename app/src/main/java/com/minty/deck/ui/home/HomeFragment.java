package com.minty.deck.ui.home;

import android.os.Build;
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
import com.minty.deck.models.Status;
import com.minty.deck.models.User;
import com.minty.deck.models.UserModel;
import com.minty.deck.models.UserResponse;
import com.minty.deck.utils.ApiClient;
import com.minty.deck.utils.Navigator;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

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
        List<User> users = new ArrayList<>();
        call.enqueue(new retrofit2.Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse userResponse = response.body();
                    users.addAll(userResponse.getUsers());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                            LinearLayoutManager.HORIZONTAL, false);
                    binding.recyclerView.setLayoutManager(linearLayoutManager);
                    binding.recyclerView.setAdapter(new StoryAdapter(getContext(), users));
                    LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

                    for (User user : users) {
                        Call<List<Status>> call2 = twitterApi.getTimelineTweets(user.getScreenName());
                        call2.enqueue(new retrofit2.Callback<List<Status>>() {
                            @Override
                            public void onResponse(Call<List<Status>> call, Response<List<Status>> response) {
                                if (response.isSuccessful()) {
                                    List<Status> statuses = response.body();
                                    binding.recyclerViewTweets.setLayoutManager(linearLayoutManager2);
                                    binding.recyclerViewTweets.setAdapter(new TweetAdapter(getContext(), statuses));
                                }
                            }

                            @Override
                            public void onFailure(Call<List<Status>> call, Throwable t) {
                                Log.d("HomeFragment", "onFailure: " + t.getMessage());
                            }
                        });
                    }
                } else {
                    Log.d("HomeFragment", "onResponse: Status Code" + response.code());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("HomeFragment", "onFailure: " + t.getMessage());
            }
        });


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