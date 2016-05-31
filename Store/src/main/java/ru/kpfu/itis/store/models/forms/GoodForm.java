package ru.kpfu.itis.store.models.forms;

/**
 * Created by perfect on 5/6/16.
 */

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class GoodForm implements Serializable {

    private String description;

    private MultipartFile img;

    private Integer price;

    private String name;

    private Long categoryId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImg() {
        return img;
    }

    public void setImg(MultipartFile img) {
        this.img = img;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}