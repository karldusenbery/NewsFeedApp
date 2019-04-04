package com.example.android.newsfeed;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<List<NewsArticle>>{


    private static final int NEWS_LOADER_ID = 1;
    private static final String THE_GUARDIAN_REQUEST_URL = "http://content.guardianapis.com/search?edition=us&show-tags=contributor&format=json&lang=en&order-by=newest&show-fields=thumbnail&page-size=50&api-key=feb4c2e1-b21f-4f7e-9dec-da3714289022";
    private static final String THE_GUARDIAN_MAIN_URL = "https://www.theguardian.com/us";

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

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (cm != null) {
            networkInfo = cm.getActiveNetworkInfo();
        }
        if(networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        }else{
            circularLoadingIndicator.setVisibility(View.GONE);
            emptyStateTextView.setText(getString(R.string.no_internet_connection));
            emptyStateTextView.setVisibility(View.VISIBLE);
        }

        newsArticleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NewsArticle currentNews = newsArticlesAdapter.getItem(position);
                if (currentNews != null) {
                    String webUrl = currentNews.getWebpageUrl();
                    Intent webIntent = new Intent(Intent.ACTION_VIEW);
                    if(webUrl != null){
                        webIntent.setData(Uri.parse(webUrl));
                    }else{
                        webIntent.setData(Uri.parse(THE_GUARDIAN_MAIN_URL));
                    }
                    startActivity(webIntent);
                }
            }
        });
    }
    @Override
    public Loader<List<NewsArticle>> onCreateLoader(int i, Bundle bundle) {
        return new NewsArticleLoader(this, THE_GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsArticle>> loader, List<NewsArticle> news) {

        circularLoadingIndicator.setVisibility(View.GONE);
        emptyStateTextView.setText(getString(R.string.no_news_found));
        newsArticleListView.setEmptyView(emptyStateTextView);
        newsArticlesAdapter.clear();
        if(news != null && !news.isEmpty()){
            newsArticlesAdapter.addAll(news);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<NewsArticle>> loader) {
        newsArticlesAdapter.clear();
    }

}
