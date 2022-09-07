package com.example.sdkproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewProfilActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profil);


        //récupération de l'identifiant user
        Intent intent = getIntent();
        String idUser = intent.getStringExtra("idUser");
        TextView tvItemProfileUserFirstName = findViewById(R.id.tvItemProfilUserFirstName);
        TextView tvItemProfileUserLastName = findViewById(R.id.tvItemProfilUserLastName);
        TextView tvItemProfileUserAge = findViewById(R.id.tvItemProfilUseAge);
        TextView tvItemProfileUserEmail = findViewById(R.id.tvItemProfilUserEmail);
        // je retire "écrit par: " que j'ai rajouté dans l'affichage pour récupérer l'idUser
        idUser = idUser.substring(11);
        Log.d("TestIdUser",idUser);




        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                "https://dummyjson.com/users/"+idUser,
                response -> {
                    try {
                        tvItemProfileUserFirstName.setText(response.getString("firstName"));
                        tvItemProfileUserLastName.setText(response.getString("lastName"));
                        Integer TempAge = response.getInt("age");
                        tvItemProfileUserAge.setText(TempAge.toString());
                        tvItemProfileUserEmail.setText(response.getString("email"));


                    } catch (JSONException e){
                        e.printStackTrace();
                    }
                },
                error -> Log.d("volley",error.toString())
        );

        RequestManager.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }
}