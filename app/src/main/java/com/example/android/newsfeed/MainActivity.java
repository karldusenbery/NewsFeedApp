package com.example.android.newsfeed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private TextView emptyStateTextView;
    private View circularLoadingIndicator;

    private ListView newsArticleListView;
    private NewsArticlesArrayAdapter newsArticlesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //empty state items
        emptyStateTextView = findViewById(R.id.empty_state);
        circularLoadingIndicator = findViewById(R.id.circular_loading_indicator);

        //list of news articles
        newsArticleListView = findViewById(R.id.list);
        newsArticlesAdapter = new NewsArticlesArrayAdapter(this, new ArrayList<NewsArticle>());
        newsArticleListView.setAdapter(newsArticlesAdapter);
    }
}
