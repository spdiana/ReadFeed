package com.example.demo.model;

public class FeedNews {

    private int newsId;

    private String author;

    private String link;

    private String title;

    private String descriptionValue;

    private String publishedDate;

    private String retrievedDate;

    public FeedNews() {
    }

    public FeedNews(String author, String link, String title, String descriptionValue, String publishedDate) {
        this.author = author;
        this.link = link;
        this.title = title;
        this.descriptionValue = descriptionValue;
        this.publishedDate = publishedDate;
    }

    public FeedNews(String author, String link, String title, String descriptionValue, String publishedDate, String retrievedDate) {
        this.author = author;
        this.link = link;
        this.title = title;
        this.descriptionValue = descriptionValue;
        this.publishedDate = publishedDate;
        this.retrievedDate = retrievedDate;
    }

    public FeedNews(int newsId, String author, String link, String title, String descriptionValue, String publishedDate, String retrievedDate) {
        this.newsId = newsId;
        this.author = author;
        this.link = link;
        this.title = title;
        this.descriptionValue = descriptionValue;
        this.publishedDate = publishedDate;
        this.retrievedDate = retrievedDate;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptionValue() {
        return descriptionValue;
    }

    public void setDescriptionValue(String descriptionValue) {
        this.descriptionValue = descriptionValue;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getRetrievedDate() {
        return retrievedDate;
    }

    public void setRetrievedDate(String retrievedDate) {
        this.retrievedDate = retrievedDate;
    }

    @Override
    public String toString() {
        return "FeedNews{" +
                "newsId=" + newsId +
                ", author='" + author + '\'' +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", descriptionValue='" + descriptionValue + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", retrievedDate='" + retrievedDate + '\'' +
                '}';
    }
}
