package com.ca.wannews;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ca.wannews.API.Articles;
import com.ca.wannews.API.Headlines;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleGetter extends AsyncTask<Object, Void, Void> {
    List<Articles> articles = new ArrayList<>();

    @Override
    protected Void doInBackground(Object... objects) {
        Fragment fa = (Fragment) objects[0];
        RecyclerView recyclerView = (RecyclerView) objects[1];
        String category = (String) objects[2];
        Context context = (Context) objects [3];
        int direction = (int) objects [4];
        String apiKey =  "75027d38c2b24007877746164b263116";
        String country = "ng";
        retrieveJson(fa, recyclerView, category, context, apiKey, country, direction);
        return null;
    }


    public void retrieveJson(Fragment fa, RecyclerView recyclerView, String category, Context context, String apiKey, String country, int direction){

        Call<Headlines> call;
        call = ApiClient.getInstance().getApi().getHeadlines(country, category, apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles() != null){
                    articles.clear();
                    articles = response.body().getArticles();
                    if (direction == 0){
                        AdapterH adapter = new AdapterH(articles,context);
                        recyclerView.setAdapter(adapter);
                    } else if (direction == 1){
                        AdapterV adapter = new AdapterV(articles,context);
                        recyclerView.setAdapter(adapter);
                    }


                    SharedPreferences sharedPreferences = context.getSharedPreferences("storedArticles",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(articles);
                    editor.putString(category,json);
                    editor.apply();



                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                SharedPreferences sharedPreferences = context.getSharedPreferences("storedArticles", Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String json = sharedPreferences.getString(category, null);
                Type type = new TypeToken<ArrayList<Articles>>() {}.getType();
                articles = gson.fromJson(json, type);
                if (articles==null) {
                    Toast.makeText(fa.getActivity(), "Failed to load", Toast.LENGTH_SHORT).show();
                }  else {
                    if (direction == 0){
                        AdapterH adapter = new AdapterH(articles,context);
                        recyclerView.setAdapter(adapter);
                    } else if (direction == 1){
                        AdapterV adapter = new AdapterV(articles,context);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }
        });
    }

    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }


}
