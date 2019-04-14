package com.example.android.factorynewsreader;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.android.factorynewsreader.adapter.PagerAdapter;
import com.example.android.factorynewsreader.model.Articles;
import com.example.android.factorynewsreader.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DescriptionActivity extends AppCompatActivity{

    private ArrayList<String> mTitle = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mDescription = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        setRetrofit();

    }

    public void setRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<NewsModel> call = api.getNews();

        call.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                List<Articles> articles = response.body().getArticles();


                for (int i = 0; i < articles.size(); i++) {
                    mImages.add(articles.get(i).getUrlToImage());
                    mTitle.add(articles.get(i).getTitle());
                    mDescription.add(articles.get(i).getDescription());
                }
                initViewPager();

            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                openAlertDialog();
            }
        });
    }

    private void initViewPager() {
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new PagerAdapter(mTitle, mImages, mDescription, this));
    }

    private void openAlertDialog(){
        AlertDialog alertDialog = new AlertDialog.Builder(DescriptionActivity.this).create();
        alertDialog.setTitle(getString(R.string.set_title));
        alertDialog.setMessage(getString(R.string.set_message));

        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

}
