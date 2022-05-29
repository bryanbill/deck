package com.minty.deck.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.minty.deck.R;
import com.minty.deck.models.TweetModel;

import java.util.List;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {
    private Context context;
    private List<TweetModel> tweetList;

    public TweetAdapter(Context context, List<TweetModel> tweetList) {
        this.context = context;
        this.tweetList = tweetList;
    }

    @NonNull
    @Override
    public TweetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tweet_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url = tweetList.get(position).getImageUrl();
        if(url != null && !url.isEmpty()) {
            Glide.with(context).load(url).centerCrop().into(holder.imageView);
        }else{
            ViewGroup.LayoutParams params = holder.relativeLayout.getLayoutParams();
            holder.imageView.setVisibility(View.GONE);
            params.height = params.WRAP_CONTENT;
        }
        Glide.with(context).load(tweetList.get(position).getUser().getProfileImageUrl()).centerCrop()
                .into(holder.avatar);
        holder.username.setText(tweetList.get(position).getUser().getUserName());
        holder.displayName.setText(tweetList.get(position).getUser().getDisplayName());
        holder.tweet.setText(tweetList.get(position).getTweet());




    }

    @Override
    public int getItemCount() {
        return tweetList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView, avatar;

        private TextView username, displayName, tweet;
        private RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.tweet_item);
            imageView = itemView.findViewById(R.id.imageView);
            avatar = itemView.findViewById(R.id.avatar);
            username = itemView.findViewById(R.id.user_name);
            displayName = itemView.findViewById(R.id.display_name);
            tweet = itemView.findViewById(R.id.tweet_text);
        }
    }
}
