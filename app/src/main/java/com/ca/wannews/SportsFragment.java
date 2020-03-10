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
    LinearLayoutManager layoutManagerH, layoutManagerV;
    final static String CATEGORY = "sports";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sports, container, false);

        recyclerViewTop = view.findViewById(R.id.recyclerTopSports);
        recyclerViewTop.setHasFixedSize(true);
        layoutManagerH = new LinearLayoutManager(getActivity());
        layoutManagerH.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewTop.setLayoutManager(layoutManagerH);
        TopFiveGetter tvg = new TopFiveGetter(this, recyclerViewTop,CATEGORY);
        tvg.retrieveJson();
        recyclerViewTop.setItemAnimator(new DefaultItemAnimator());


        recyclerViewBottom= view.findViewById(R.id.recyclerSports);
        recyclerViewBottom.setHasFixedSize(true);
        layoutManagerV = new LinearLayoutManager(getActivity());
        layoutManagerV.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewBottom.setLayoutManager(layoutManagerV);
        ArticleGetter ag = new ArticleGetter(this, recyclerViewBottom,CATEGORY);
        ag.retrieveJson();
        recyclerViewBottom.setItemAnimator(new DefaultItemAnimator());

        return view;
    }








}
