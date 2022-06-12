package com.minty.deck.ui.sheets;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.minty.deck.R;
import com.minty.deck.interfaces.ITwitterApi;
import com.minty.deck.models.Status;
import com.minty.deck.utils.ApiClient;

import retrofit2.Call;

public class ModalBottomSheet extends BottomSheetDialogFragment {
    private ITwitterApi twitterApi;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.bottom_sheet, container, false);
        twitterApi = ApiClient.getClient();
        EditText tweetInput = ((EditText) root.findViewById(R.id.tweet_input_text));

        root.findViewById(R.id.tweet_button).setOnClickListener(v -> {
            String status = tweetInput
                    .getText()
                    .toString();
            if(status == null || status.isEmpty()) {
                Toast.makeText(getContext(), "Please enter a tweet", Toast.LENGTH_SHORT).show();
                return;
            }else{
                // Post tweet
                Call<Status> call = twitterApi.tweet(status);
                call.enqueue(new retrofit2.Callback<Status>() {
                    @Override
                    public void onResponse(Call<Status> call, retrofit2.Response<Status> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getContext(), "Tweet posted", Toast.LENGTH_SHORT).show();
                            // reload adapter
                            dismiss();
                        }else{
                            Log.d("ModalBottomSheet", "onResponse: " + response.message());
                            Toast.makeText(getContext(), response.message()+" Error posting tweet, Code: "+response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Status> call, Throwable t) {
                        Toast.makeText(getContext(), "Failed to post tweet", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
