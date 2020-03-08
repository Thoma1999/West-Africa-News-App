package com.ca.wannews;

import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.ca.wannews.API.Articles;
import com.ca.wannews.API.Headlines;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopFiveGetter {
    private Fragment fa;
    private List<Articles> articles;
    private AdapterH adapter;
    private RecyclerView recyclerView;
    private String country;
    public static final String apiKey =  "2956c968ba214518826b4c6a940a877f";

    public TopFiveGetter(Fragment fa, List<Articles> articles, AdapterH adapter, RecyclerView recyclerView){
        this.fa = fa;
        this.articles = articles;
        this.adapter = adapter;
        this.recyclerView = recyclerView;
        country = getCountry();
    }

    public void retrieveJson(){

        Call<Headlines> call;
        call = ApiClient.getInstance().getApi().getHeadlines(country,apiKey);

        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful() && response.body().getArticles() != null){
                    articles.clear();
                    articles = response.body().getArticles();
                    adapter = new AdapterH(articles.subList(0,5));
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