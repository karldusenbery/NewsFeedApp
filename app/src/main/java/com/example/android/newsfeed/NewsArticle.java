package com.example.android.newsfeed;

class NewsArticle {
    private String thumbnailImage;
    private String title;
    private String columnSection;
    private String date;
    private String time;
    private String author;
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

    String getWebpageUrl() {
        return webpageUrl;
    }
}
