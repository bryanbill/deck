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
import com.minty.deck.models.Status;

import java.util.List;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {
    private Context context;
    private List<Status> tweetList;

    public TweetAdapter(Context context, List<Status> tweetList) {
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
        //Check if the tweet is a retweet
        if (tweetList.get(position).getRetweetedStatus() != null) {
            //If it is a retweet, set the text to the retweeted status
            holder.tweet.setText(tweetList.get(position).getRetweetedStatus().getText());
            //Set the retweeted status image
            Glide.with(context)
                    .load(tweetList.get(position).getRetweetedStatus().getUser().getProfileImageUrl())
                    .fitCenter()
                    .into(holder.avatar);
            //Set the retweeted status user name
            holder.username.setText("@" + tweetList.get(position).getRetweetedStatus().getUser().getScreenName());
            //Set the retweeted status display name
            holder.displayName.setText(tweetList.get(position).getRetweetedStatus().getUser().getName());
        } else {
            //If it is not a retweet, set the text to the original status
            holder.tweet.setText(tweetList.get(position).getText());
            //Set the original status image
            Glide.with(context)
                    .load(tweetList.get(position).getUser().getProfileImageUrl())
                    .fitCenter()
                    .into(holder.avatar);
            //Set the original status user name
            holder.username.setText("@" + tweetList.get(position).getUser().getScreenName());
            //Set the original status display name
            holder.displayName.setText(tweetList.get(position).getUser().getName());
        }
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
