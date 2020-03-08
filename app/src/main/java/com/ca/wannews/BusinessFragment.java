package com.ca.wannews;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ca.wannews.API.Articles;

import java.util.ArrayList;
import java.util.List;


public class BusinessFragment extends Fragment {
    RecyclerView recyclerView;
    AdapterH adapter;
    List<Articles> articles = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_business, container, false);
        recyclerView = view.findViewById(R.id.recyclerTopBusiness);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        TopFiveGetter ta = new TopFiveGetter(this, articles, adapter,recyclerView);
        ta.retrieveJson();
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return view;

    }

}
