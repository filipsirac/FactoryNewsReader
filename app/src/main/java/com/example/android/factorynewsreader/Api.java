package com.example.android.factorynewsreader;

import com.example.android.factorynewsreader.model.Articles;
import com.example.android.factorynewsreader.model.NewsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://newsapi.org/v1/";

    @GET("articles?source=bbc-news&sortBy=top&apiKey=6946d0c07a1c4555a4186bfcade76398")
    Call<NewsModel> getNews();
}
