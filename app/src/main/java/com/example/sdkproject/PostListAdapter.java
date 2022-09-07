package com.example.sdkproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        Button btnItemPostReadMore;
        Context context;

        public postViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItemPostTitle = itemView.findViewById(R.id.tvItemPostTitle);
            tvItemPostBody = itemView.findViewById(R.id.tvItemPostBody);
            tvItemPostUser = itemView.findViewById(R.id.tvItemPostUser);
            tvItemPostTag = itemView.findViewById(R.id.tvItemPostTag);
            btnItemPostReadMore = itemView.findViewById(R.id.btnLirePlus);
            context = itemView.getContext();



            btnItemPostReadMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView tvItemPostBody = itemView.findViewById(R.id.tvItemPostBody);
                    ViewGroup.LayoutParams params = tvItemPostBody.getLayoutParams();
                    //Log.d("testBtn",btnItemPostReadMore..getText().toString());
                    //ne marche pas, "Lire plus" est différent de "Lire plus"
                    //if (btnItemPostReadMore.getText().toString() == "Lire plus"){
                    if (tvItemPostBody.getMaxLines() == 2) {
                        tvItemPostBody.setMaxLines(100);
                        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                        tvItemPostBody.requestLayout();
                        btnItemPostReadMore.setText("Lire moins");
                        btnItemPostReadMore.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.ic_menu_revert, 0);
                    } else {
                        tvItemPostBody.setMaxLines(2);
                        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                        tvItemPostBody.requestLayout();
                        btnItemPostReadMore.setText("Lire plus");
                        btnItemPostReadMore.setCompoundDrawablesWithIntrinsicBounds(0, 0, android.R.drawable.ic_menu_more, 0);
                    }
                    Log.d("testBtn", "coucou");
                }

            });

                tvItemPostUser.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick (View view){
                    Intent intent = new Intent(context, ViewProfilActivity.class);
                    intent.putExtra("idUser", tvItemPostUser.getText());
                    context.startActivity(intent);
                }
                });


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
