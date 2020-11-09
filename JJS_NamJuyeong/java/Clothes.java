package com.example.closet;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Clothes implements Serializable {
    private static final long serialVersionUID = 1L;
    private Bitmap image;
    private int category;
    private String explanation;

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
