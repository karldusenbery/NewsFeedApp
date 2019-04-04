package com.example.android.newsfeed;

import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

class NewsArticle {
    private String thumbnailImage;
    private String title;
    private String columnSection;
    private String date;
    private String time;
    private String dateAndTime;
    private String author;
    private ArrayList<String> authors;
    private String webpageUrl;

    //default constructor
    NewsArticle(String thumbnailImage, String title, String columnSection, String date, String time, String author, String webpageUrl) {
        this.thumbnailImage = thumbnailImage;
        this.title = title;
        this.columnSection = columnSection;
        this.date = date;
        this.time = time;
        this.author = author;
        this.webpageUrl = webpageUrl;
    }

    //constructor used in QueryUtils class
    NewsArticle(String title, String section, String publicationDateTime, String webUrl, String thumbnail,  ArrayList<String> authors){
        this.thumbnailImage = thumbnail;
        this.title = title;
        this.columnSection = section;
        this.dateAndTime = publicationDateTime;
        Log.i(TAG, ("Value of publicationDateTime: " + publicationDateTime));
        this.authors = authors;
        this.webpageUrl = webUrl;
    }


    /** Getter methods **/
    String getThumbnailImage() {
        return thumbnailImage;
    }

    String getTitle() {
        return title;
    }

    String getColumnSection() {
        return columnSection;
    }

    String getDate() {
        return date;
    }

    String getTime() {
        return time;
    }

    String getAuthor() {
        return author;
    }

    ArrayList<String> getAuthors() {
        return authors;
    }

    String getWebpageUrl() {
        return webpageUrl;
    }

    String getWebPublicationDateAndTime() {
        Log.i(TAG, ("Value of dateAndTime: " + dateAndTime));
        return dateAndTime;
    }
}
