package com.example.closet;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Clothes implements Parcelable {
    public static ArrayList<Bitmap> topImageList;

    private static final long serialVersionUID = 1L;
    private Bitmap image;
    private int category;
    private String explanation;

    public Clothes(Bitmap image, int category, String explanation){
        this.image = image;
        this.category = category;
        this.explanation = explanation;
    }

    protected Clothes(Parcel in) {
        image = in.readParcelable(Bitmap.class.getClassLoader());
        category = in.readInt();
        explanation = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(image, flags);
        dest.writeInt(category);
        dest.writeString(explanation);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Clothes> CREATOR = new Creator<Clothes>() {
        @Override
        public Clothes createFromParcel(Parcel in) {
            return new Clothes(in);
        }

        @Override
        public Clothes[] newArray(int size) {
            return new Clothes[size];
        }
    };

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
