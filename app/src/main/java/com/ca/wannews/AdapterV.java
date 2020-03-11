package com.ca.wannews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ca.wannews.API.Articles;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;



public class AdapterV extends RecyclerView.Adapter<AdapterV.ViewHolder> {
    List<Articles> articles;
    Context context;


    public AdapterV(List<Articles> articles, Context context){
        this.articles = articles.subList(5,20);
        this.context = context;
    }


    @NonNull
    @Override
    public AdapterV.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_card_small,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterV.ViewHolder holder, int position) {
        final Articles a = articles.get(position);
        String imageUrl = a.getUrlToImage();
        String url = a.getUrl();
        holder.tvTitle.setText(a.getTitle());
        holder.tvDate.setText(dateTime(a.getPublishedAt()));
        Glide.with(context).load(imageUrl).into(holder.imageView);

        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context,ViewArticle.class);
            intent.putExtra("title",a.getTitle());
            intent.putExtra("source",a.getSource().getName());
            intent.putExtra("time",dateTime(a.getPublishedAt()));
            intent.putExtra("desc",a.getDescription());
            intent.putExtra("imageUrl",a.getUrlToImage());
            intent.putExtra("url",a.getUrl());
            context.startActivity(intent);
        });

        holder.cardView.setOnLongClickListener(v -> {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, url);
            sendIntent.setType("text/plain");
            Intent shareIntent = Intent.createChooser(sendIntent, null);
            context.startActivity(shareIntent);
            return false;
        });



    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle,tvDate;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitleSmall);
            tvDate = itemView.findViewById(R.id.tvDateSmall);
            imageView = itemView.findViewById(R.id.imageSmall);
            cardView = itemView.findViewById(R.id.cardViewSmall);

        }
    }



    public String getCountry(){
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }

    public String dateTime(String t){
        PrettyTime prettyTime = new PrettyTime(new Locale(getCountry()));
        String time = null;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:",Locale.ENGLISH);
            Date date = simpleDateFormat.parse(t);
            time = prettyTime.format(date);
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return time;

    }


}

