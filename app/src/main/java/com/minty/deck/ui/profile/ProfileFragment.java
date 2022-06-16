package com.minty.deck.ui.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.minty.deck.adapters.TweetAdapter;
import com.minty.deck.databinding.FragmentProfileBinding;
import com.minty.deck.interfaces.ITwitterApi;
import com.minty.deck.models.Status;
import com.minty.deck.models.UserModel;
import com.minty.deck.utils.ApiClient;
import com.minty.deck.utils.ServiceLocator;

import java.util.List;

import retrofit2.Call;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    ITwitterApi twitterApi;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UserModel user = ServiceLocator.getInstance().getUserDao().getUser();
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        twitterApi = ApiClient.getClient();

        Call<List<Status>> call = twitterApi.getTimelineTweets(user.getUserName());
        if (user.getUserName() != null) {
            binding.authAlert.setVisibility(View.GONE);
            binding.profileName.setText(user.getDisplayName());
            binding.userName.setText("@" + user.getUserName());

            Glide.with(this)
                    .load(user.getProfileImageUrl())
                    .fitCenter()
                    .into(binding.profileImage);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            binding.recyclerViewTweets.setLayoutManager(layoutManager);
            call.enqueue(new retrofit2.Callback<List<Status>>() {
                @Override
                public void onResponse(Call<List<Status>> call, retrofit2.Response<List<Status>> response) {
                    if (response.isSuccessful()) {
                        List<Status> statuses = response.body();
                        TweetAdapter adapter = new TweetAdapter(getContext(), statuses);
                        binding.recyclerViewTweets.setLayoutManager(layoutManager);
                        binding.recyclerViewTweets.setAdapter(adapter);
                    } else {
                        Log.d("ProfileFragment", "onResponse: Status Code" + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<Status>> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } else {
            binding.materialCardView.setVisibility(View.GONE);
            binding.profileName.setVisibility(View.GONE);
            binding.userName.setVisibility(View.GONE);
            binding.recyclerViewTweets
                    .setVisibility(View.GONE);
        }
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}