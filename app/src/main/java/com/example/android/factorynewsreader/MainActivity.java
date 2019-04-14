package com.example.android.factorynewsreader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.android.factorynewsreader.adapter.OnItemClickListener;
import com.example.android.factorynewsreader.adapter.RecyclerViewAdapter;
import com.example.android.factorynewsreader.model.Articles;
import com.example.android.factorynewsreader.model.NewsModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mTitle = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                }

                initRecyclerView();
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(mImages, mTitle, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(MainActivity.this);
    }

    @Override
    public void onItemClickListener(int position) {
        Intent intent = new Intent(this, DescriptionActivity.class);
        startActivity(intent);

    }
}
