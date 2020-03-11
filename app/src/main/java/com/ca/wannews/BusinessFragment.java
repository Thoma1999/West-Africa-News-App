package com.ca.wannews;

import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BusinessFragment extends Fragment {
    RecyclerView recyclerViewTop;
    RecyclerView recyclerViewBottom;
    LinearLayoutManager layoutManagerH, layoutManagerV;
    final static String CATEGORY = "business";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_business, container, false);
        recyclerViewTop = view.findViewById(R.id.recyclerTopBusiness);
        layoutManagerH = new LinearLayoutManager(getActivity());
        layoutManagerH.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewTop.setLayoutManager(layoutManagerH);
        new ArticleGetter().execute(this, recyclerViewTop,CATEGORY, getActivity(),0);
        recyclerViewTop.setItemAnimator(new DefaultItemAnimator());


        recyclerViewBottom= view.findViewById(R.id.recyclerBusiness);
        layoutManagerV = new LinearLayoutManager(getActivity());
        layoutManagerV.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewBottom.setLayoutManager(layoutManagerV);
        new ArticleGetter().execute(this, recyclerViewBottom,CATEGORY, getActivity(),1);
        recyclerViewBottom.setItemAnimator(new DefaultItemAnimator());

        return view;

    }

}
