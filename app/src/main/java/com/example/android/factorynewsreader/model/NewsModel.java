package com.example.android.factorynewsreader.model;

import java.util.List;

public class NewsModel {

    private List<Articles> articles;

    public NewsModel(List<Articles> articles) {
        this.articles = articles;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }
}
