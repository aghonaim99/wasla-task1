package com.example.waslatask1.Models;

public class NewPost {
    private Long id;
    private String title_en;
    private String title_ar;
    private String body_en;
    private String body_ar;
    private int type;
    private long categoryId;
    private String imgUrl;

    public String getTitle_en() {
        return title_en;
    }

    public void setTitle_en(String title_en) {
        this.title_en = title_en;
    }

    public String getTitle_ar() {
        return title_ar;
    }

    public void setTitle_ar(String title_ar) {
        this.title_ar = title_ar;
    }

    public String getBody_en() {
        return body_en;
    }

    public void setBody_en(String body_en) {
        this.body_en = body_en;
    }

    public String getBody_ar() {
        return body_ar;
    }

    public void setBody_ar(String body_ar) {
        this.body_ar = body_ar;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }
}
