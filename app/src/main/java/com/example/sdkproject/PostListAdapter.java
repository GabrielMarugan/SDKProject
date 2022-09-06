package com.example.sdkproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.postViewHolder> {

    private ArrayList<Post> postList;


    public static class postViewHolder extends RecyclerView.ViewHolder{

        TextView tvItemPostTitle;
        TextView tvItemPostBody;
        TextView tvItemPostUser;
        TextView tvItemPostTag;

        public postViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemPostTitle = itemView.findViewById(R.id.tvItemPostTitle);
            tvItemPostBody = itemView.findViewById(R.id.tvItemPostBody);
            tvItemPostUser = itemView.findViewById(R.id.tvItemPostUser);
            tvItemPostTag = itemView.findViewById(R.id.tvItemPostTag);
        }
    }

    public PostListAdapter(ArrayList<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public postViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.item_post,parent,false);
        return new postViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull postViewHolder holder, int position) {
        holder.tvItemPostTitle.setText(postList.get(position).getTitle());
        holder.tvItemPostBody.setText(postList.get(position).getBody());
        holder.tvItemPostUser.setText(holder.tvItemPostUser.getText() + postList.get(position).getUserId().toString());
        ArrayList<String> arrayList = postList.get(position).getTags();
        String concatTags = "";
       for (int i=0; i < arrayList.size(); i++){
            concatTags += " #" + arrayList.get(i);
        }
        holder.tvItemPostTag.setText(concatTags);


    }

    @Override
    public int getItemCount() {
        return postList.size();
    }




}
