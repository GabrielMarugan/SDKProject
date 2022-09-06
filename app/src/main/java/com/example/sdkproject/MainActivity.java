package com.example.sdkproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Post> postsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            "https://dummyjson.com/posts",
            response -> {
                postsList = new ArrayList<>();

                try {
                    JSONArray jsonPostList = response.getJSONArray("posts");
                    for (int i = 0; i < jsonPostList.length(); i++) {
                        try {
                            JSONObject json = jsonPostList.getJSONObject(i);
                            Post post = new Post(json);
                            postsList.add(post);
                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                    RecyclerView rvPostList = findViewById(R.id.rvPostList);
                    rvPostList.setLayoutManager(new LinearLayoutManager((this)));
                    rvPostList.setAdapter(new PostListAdapter(postsList));
                } catch (JSONException e){
                    e.printStackTrace();
                }
            },
            error -> Log.d("volley",error.toString())
        );

        RequestManager.getInstance(this).addToRequestQueue(jsonObjectRequest);
        final Button button = findViewById(R.id.btnLirePlus);



    }


}
