package com.example.mobileprogramming_teamproject_leejin;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Clothes implements Serializable {
    static final long serialVersionUID = 1L;
    Bitmap image;
    int category;
    String explanation;

    public Clothes(Bitmap image, int category, String explanation){
        this.image = image;
        this.category = category;
        this.explanation = explanation;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}