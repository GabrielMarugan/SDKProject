package com.example.sdkproject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Post {

    private Integer id;
    private String title;
    private String body;
    private Integer userId;
    private ArrayList<String> tags;

    public Post(Integer id, String title, String body, Integer userId, ArrayList<String> tags) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.tags = tags;
    }

    public Post(JSONObject json){

        try {
            this.id = json.getInt("id");
            this.title = json.getString("title");
            this.body = json.getString("body");
            this.userId = json.getInt("userId");
            JSONArray jsonArray = json.getJSONArray("tags");
            this.tags = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++){
                this.tags.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            this.id = 0;
            this.title = "";
            this.body = "";
            this.userId = 0;
            this.tags = new ArrayList<>();
        }
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}
