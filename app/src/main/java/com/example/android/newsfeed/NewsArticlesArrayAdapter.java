package com.example.android.newsfeed;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import static android.content.ContentValues.TAG;

public class NewsArticlesArrayAdapter extends ArrayAdapter<NewsArticle> {

    /** Tag for log messages **/
    private static final String LOG_TAG = NewsArticlesArrayAdapter.class.getName();
    private Context context;

    NewsArticlesArrayAdapter(@NonNull Context context, @NonNull List<NewsArticle> objects) {
        super(context, 0, objects);
        this.context = context;
    }

    //adapter that does all the work
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.news_story_list_item, parent, false);
        }
        NewsArticle currentNewsArticle = getItem(position);
        if (currentNewsArticle != null) {
            ImageView contentImage = listItemView.findViewById(R.id.thumbnailImage);
            loadImageFromUrl(currentNewsArticle.getThumbnailImage(), contentImage);

            TextView contentTitle = listItemView.findViewById(R.id.title);
            String title = currentNewsArticle.getTitle();
            if(title != null) {
                contentTitle.setText(title);
            }else{
                contentTitle.setText(context.getString(R.string.no_title_found));
            }

            TextView contentSection = listItemView.findViewById(R.id.column_section);
            String section = currentNewsArticle.getColumnSection();
            if(section != null) {
                contentSection.setText(section);
            }else{
                contentSection.setText(context.getString(R.string.no_column_section_found));
            }

            TextView contentPublishedDate = listItemView.findViewById(R.id.date);
            TextView contentPublishedTime = listItemView.findViewById(R.id.time);
            String currentDateAndTime = currentNewsArticle.getWebPublicationDateAndTime();
            Log.i(TAG, ("Value of currentDateAndTime: " + currentDateAndTime));
            if(currentDateAndTime != null) {
                try {
                    String date = getNewsPublicationDate(currentDateAndTime);
                    String time = getNewsPublicationTime(currentDateAndTime);
                    contentPublishedDate.setText(date);
                    contentPublishedTime.setText(time);
                    contentPublishedDate.setVisibility(View.VISIBLE);
                    contentPublishedTime.setVisibility(View.VISIBLE);
                } catch (ParseException e) {
                    Log.e(LOG_TAG, context.getString(R.string.problem_passing_date_and_time), e);
                }
            }else{
                Log.e(TAG,context.getString(R.string.null_date_and_time));
                contentPublishedDate.setText(context.getString(R.string.no_date_found));
                contentPublishedTime.setText(context.getString(R.string.no_time_found));
            }

            TextView authorsTextView = listItemView.findViewById(R.id.author);
            ArrayList<String> authorsArray = currentNewsArticle.getAuthors();
            if(authorsArray == null ){
                authorsTextView.setText(context.getString(R.string.no_author_found));
            }else{
                StringBuilder authorString = new StringBuilder();
                for(int i=0;i<authorsArray.size(); i++){
                    authorString.append(authorsArray.get(i));
                    if((i + 1) < authorsArray.size()){
                        authorString.append(", ");
                    }
                }
                authorsTextView.setText(authorString.toString());
                authorsTextView.setVisibility(View.VISIBLE);
            }
        }
        return listItemView;
    }

    private String getNewsPublicationDate(String currentDateAndTime) throws ParseException {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(context.getString(R.string.received_date_and_time_format));
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdfDate = new SimpleDateFormat(context.getString(R.string.displayed_date_format));
        return sdfDate.format(sdf.parse(currentDateAndTime));
    }

    private String getNewsPublicationTime(String currentDateAndTime) throws ParseException {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat(context.getString(R.string.received_date_and_time_format));
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdfTime = new SimpleDateFormat(context.getString(R.string.displayed_time_format));
        sdfTime.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdfTime.format(sdf.parse(currentDateAndTime));
    }

    private void loadImageFromUrl(String url, ImageView newsArticleMainImage) {
        if(url != null) {
            Picasso.with(context).load(url).placeholder(R.drawable.loading)
                    .error(R.drawable.newspaper)
                    .into(newsArticleMainImage, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                        }

                        @Override
                        public void onError() {
                        }
                    });
        }else{
            newsArticleMainImage.setImageResource(R.drawable.newspaper);
        }
    }
}
