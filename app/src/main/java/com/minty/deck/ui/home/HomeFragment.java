package com.minty.deck.ui.home;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
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
import com.minty.deck.utils.ServiceLocator;

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

        UserModel user = ServiceLocator.getInstance().getUserDao().getUser();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerViewTweets.setLayoutManager(new LinearLayoutManager(getContext()));

        twitterApi = ApiClient.getClient();
        Call<UserResponse> call = twitterApi.getFollowers(user.getUserName());
        List<User> users = new ArrayList<>();
        List<Status> statuses = new ArrayList<>();
        call.enqueue(new retrofit2.Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    UserResponse userResponse = response.body();
                    users.addAll(userResponse.getUsers());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                            LinearLayoutManager.HORIZONTAL, false);
                    if (binding != null) {
                        binding.recyclerView.setLayoutManager(linearLayoutManager);
                        binding.recyclerView.setAdapter(new StoryAdapter(getContext(), users));
                        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        for (int i = 0; i < users.size(); i++) {
                            Call<List<Status>> call2 = twitterApi.getTimelineTweets(users.get(i).getScreenName());
                            call2.enqueue(new retrofit2.Callback<List<Status>>() {
                                @Override
                                public void onResponse(Call<List<Status>> call,
                                                       Response<List<Status>> response) {
                                    if (response.isSuccessful()) {
                                        statuses.addAll(response.body());
                                        if (binding != null) {
                                            binding.recyclerViewTweets.getAdapter().notifyDataSetChanged();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Status>> call, Throwable t) {
                                    Log.d("HomeViewModel", "onFailure: " + t.getMessage());
                                }
                            });
                        }
                        binding.recyclerViewTweets.setLayoutManager(linearLayoutManager2);
                        binding.recyclerViewTweets.setAdapter(new TweetAdapter(getContext(), statuses));
                    } else {
                        Log.d("HomeFragment", "onResponse: Status Code" + response.code());
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("HomeFragment", "onFailure: " + t.getMessage());
            }
        });
        List<Status> searchResults = new ArrayList<>();
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchResults.clear();
                for (Status status : statuses) {
                    if (status.getText()
                            .toLowerCase()
                            .contains(query.toLowerCase())
                            || status.getRetweetedStatus()
                            != null && status.getRetweetedStatus()
                            .getText()
                            .toLowerCase()
                            .contains(query.toLowerCase())) {
                        searchResults.add(status);
                    }
                }
                if (searchResults.size() > 0) {
                    binding.recyclerViewTweets.setAdapter(new TweetAdapter(getContext(), searchResults));
                } else {
                    Toast.makeText(getContext(), "No results found", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
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