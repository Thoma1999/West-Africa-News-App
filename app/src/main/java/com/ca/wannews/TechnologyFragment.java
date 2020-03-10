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


/**
 * A simple {@link Fragment} subclass.
 */
public class TechnologyFragment extends Fragment {
    RecyclerView recyclerViewTop;
    RecyclerView recyclerViewBottom;
    LinearLayoutManager layoutManagerH, layoutManagerV;
    final static String CATEGORY = "technology";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_technology, container, false);
        recyclerViewTop = view.findViewById(R.id.recyclerTopTechnology);
        recyclerViewTop.setHasFixedSize(true);
        layoutManagerH = new LinearLayoutManager(getActivity());
        layoutManagerH.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewTop.setLayoutManager(layoutManagerH);
        TopFiveGetter tvg = new TopFiveGetter(this, recyclerViewTop, CATEGORY);
        tvg.retrieveJson();
        recyclerViewTop.setItemAnimator(new DefaultItemAnimator());


        recyclerViewBottom= view.findViewById(R.id.recyclerTechnology);
        recyclerViewBottom.setHasFixedSize(true);
        layoutManagerV = new LinearLayoutManager(getActivity());
        layoutManagerV.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewBottom.setLayoutManager(layoutManagerV);
        ArticleGetter ag = new ArticleGetter(this, recyclerViewBottom, CATEGORY);
        ag.retrieveJson();
        recyclerViewBottom.setItemAnimator(new DefaultItemAnimator());

        return view;

    }
}
