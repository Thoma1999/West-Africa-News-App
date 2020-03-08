package com.ca.wannews;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ca.wannews.API.Articles;
import com.ca.wannews.API.Headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SportsFragment extends Fragment {
    RecyclerView recyclerViewTop;
    RecyclerView recyclerViewBottom;
    AdapterV adapterV;
    AdapterH adapterH;
    List<Articles> articles = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sports, container, false);
        recyclerViewTop = view.findViewById(R.id.recyclerTopSports);
        recyclerViewBottom = view.findViewById(R.id.recyclerSports);
        final LinearLayoutManager layoutManagerV = new LinearLayoutManager(getActivity());
        final LinearLayoutManager layoutManagerH = new LinearLayoutManager(getActivity());
        layoutManagerV.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManagerH.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewBottom.setLayoutManager(layoutManagerV);
        recyclerViewTop.setLayoutManager(layoutManagerH);

        TopFiveGetter tvg = new TopFiveGetter(this, articles, adapterH,recyclerViewTop);
        tvg.retrieveJson();
        ArticleGetter ag = new ArticleGetter(this, articles, adapterV,recyclerViewTop);
        ag.retrieveJson();
        recyclerViewTop.setItemAnimator(new DefaultItemAnimator());
        recyclerViewBottom.setItemAnimator(new DefaultItemAnimator());
        return view;
    }








}
