package com.minty.deck.adapters;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.minty.deck.R;
import com.minty.deck.interfaces.IHandler;
import com.minty.deck.models.HashtagModel;
import com.minty.deck.ui.browse.BrowseFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashtagAdapter extends RecyclerView.Adapter<HashtagAdapter.ViewHolder> {

    private Context context;
    private List<HashtagModel> mHashtagList;

    Map<TextView, Integer> map = new HashMap<>();

    private IHandler mHandler;

    public HashtagAdapter(Context context, List<HashtagModel> hashtagList, IHandler handler) {
        this.context = context;
        this.mHashtagList = hashtagList;
        this.mHandler = handler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.hashtag_item, parent, false);
        return new HashtagAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HashtagModel hashtagModel = mHashtagList.get(position);
        holder.hashtag.setText(hashtagModel.getHashtag());

        if (position == mHashtagList.size() - 1) {
            HashtagModel lastHashtag = mHashtagList.get(position);


            holder.hashtag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((ViewGroup) holder.hashTagView.getParent() != null) {
                        ((ViewGroup) holder.hashTagView.getParent()).removeView(holder.hashTagView);
                    }
                    EditText editText = (EditText) holder.hashTagView
                            .findViewById(R.id.input_hashtag);
                    new MaterialAlertDialogBuilder(context).setView(holder.hashTagView)
                            .setMessage("Enter a new hashtag")
                            .setTitle("Hashtag")
                            .setNegativeButton("Cancel", (dialog, which) -> {
                                dialog.dismiss();
                            })
                            .setPositiveButton("Add", (dialog, which) -> {
                                //Add the new hashtag
                                mHashtagList.remove(0);
                                mHashtagList.remove(mHashtagList.size() - 1);
                                String newHashtag = editText.getText().toString();
                                mHashtagList.add(new HashtagModel(newHashtag, 0));
                                mHashtagList.add(lastHashtag);
                                notifyDataSetChanged();

                            })
                            .show();
                }
            });
        } else {
            holder.hashtag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (TextView textView : map.keySet()) {
                        textView.setBackgroundColor(map.get(textView));
                    }
                    int color = 0x00000000;
                    map.put(holder.hashtag, color);
                    holder.hashtag
                            .setBackgroundColor(context.getResources()
                                    .getColor(R.color.purple));
                    mHandler.onHashtagClicked(hashtagModel.getHashtag());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mHashtagList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView hashtag;
        private View hashTagView;

        public ViewHolder(android.view.View itemView) {
            super(itemView);
            hashtag = itemView.findViewById(R.id.hashtag);
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            hashTagView = layoutInflater.inflate(R.layout.hashtag_input,
                    (ViewGroup) itemView.getParent(), false);
        }
    }
}
