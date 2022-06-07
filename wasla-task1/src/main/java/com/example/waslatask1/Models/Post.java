package com.example.waslatask1.Models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="POST")
public class Post {
    @Id
    @Column(name="ID", unique = true, nullable = false, insertable = true, updatable = true)
//    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "POST_SEQ", allocationSize = 1)
    private Long id;

    @Column(name="TITLE_EN")
    private String title_en;

    @Column(name="TITLE_AR")
    private String title_ar;

    @Column(name="BODY_EN")
    private String body_en;

    @Column(name="BODY_AR")
    private String body_ar;

    @Column(name="CREATED_AT")
    private Date created_at;

    @Column(name="MODIFIED_AT")
    private Date MODIFIED_at;

    @Column(name="TYPE")
    private int type;

    @Column(name="IMAGE_URL")
    private String img_url;

    //    @Column(name = "CATEGORY_ID")
    @ManyToOne(targetEntity = Category.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    private Category category;

    @PostPersist
    public void saveCreatedAtDate(){
        setCreated_at(new Date(new java.util.Date().getTime()));
    }

    @PostUpdate
    public void updateModifiedDate(){
        setMODIFIED_at(new Date(new java.util.Date().getTime()));
    }

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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getMODIFIED_at() {
        return MODIFIED_at;
    }

    public void setMODIFIED_at(Date MODIFIED_at) {
        this.MODIFIED_at = MODIFIED_at;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
