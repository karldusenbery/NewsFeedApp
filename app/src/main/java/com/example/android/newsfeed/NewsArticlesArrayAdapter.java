package com.example.android.newsfeed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class NewsArticlesArrayAdapter extends ArrayAdapter<NewsArticle> {

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

            //Thumbnail image code **/
            ImageView newsArticleMainImage = listItemView.findViewById(R.id.thumbnailImage);
            newsArticleMainImage.setImageResource(R.drawable.newspaper);

            //News article strings text code **/
            TextView articleTitleTextView = listItemView.findViewById(R.id.title);
            String title = currentNewsArticle.getTitle();
            if(title != null) {
                articleTitleTextView.setText(title);
            }else{
                articleTitleTextView.setText(context.getString(R.string.no_title_found));
            }

            TextView columnSectionTextView = listItemView.findViewById(R.id.column_section);
            String columnSection = currentNewsArticle.getColumnSection();
            if(columnSection != null) {
                columnSectionTextView.setText(columnSection);
            }else{
                columnSectionTextView.setText(context.getString(R.string.no_column_section_found));
            }

            TextView dateTextView = listItemView.findViewById(R.id.date);
            String articleDate = currentNewsArticle.getDate();
            if(articleDate != null) {
                dateTextView.setText(articleDate);
            }else{
                dateTextView.setText(context.getString(R.string.no_date_found));
            }

            TextView timeTextView = listItemView.findViewById(R.id.time);
            String articleTime = currentNewsArticle.getTime();
            if(articleTime != null) {
                timeTextView.setText(articleTime);
            }else{
                timeTextView.setText(context.getString(R.string.no_time_found));
            }

            TextView authorTextView = listItemView.findViewById(R.id.author);
            String articleAuthor = currentNewsArticle.getAuthor();
            if(articleAuthor != null ){
                authorTextView.setText(articleAuthor);
            }else{
                authorTextView.setText(context.getString(R.string.no_author_found));
            }
        }
        return listItemView;
    }
}
