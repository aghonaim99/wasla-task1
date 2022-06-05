package com.example.waslatask1.Models;

import javax.persistence.*;

@Entity
@Table(name="CATEGORY")
public class Category {
    @Id
    private Long id;

    @Column(name="TITLE_EN")
    private String title_en;

    @Column(name="TITLE_AR")
    private String title_ar;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
