package com.example.sieuthi.newsfeed;

public class Newsfeed {

    private int imgPhoto;
    private String title;
    private String time;
    private String shortContent;
    private String content;

    public Newsfeed(int imgPhoto, String title, String time, String shortContent, String content) {
        this.imgPhoto = imgPhoto;
        this.title = title;
        this.time = time;
        this.shortContent = shortContent;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImgPhoto() {
        return imgPhoto;
    }

    public void setImgPhoto(int imgPhoto) {
        this.imgPhoto = imgPhoto;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }
}
