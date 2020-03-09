package com.ca.wannews;

import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.ca.wannews.API.Articles;
import com.ca.wannews.API.Headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticleGetter {
    private Fragment fa;
    private List<Articles> articles;
    private AdapterV adapter;
    private RecyclerView recyclerView;
    private String country;
    private String category;
    public static final String apiKey =  "75027d38c2b24007877746164b263116";

    public ArticleGetter(Fragment fa, RecyclerView recyclerView, String category){
        this.fa = fa;
        this.category = category;
        articles = new ArrayList<>();
        this.recyclerView = recyclerView;
        country = "ng";
    }

    public void retrieveJson(){

        Call<Headlines> call;
        call = ApiClient.getInstance().getApi().getHeadlines(country,category,apiKey);

        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles() != null){
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new AdapterV(articles);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                Toast.makeText(fa.getActivity(), "Failed to load", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }
}
