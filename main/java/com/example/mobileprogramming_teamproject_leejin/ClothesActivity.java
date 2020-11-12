package com.example.mobileprogramming_teamproject_leejin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

public class ClothesActivity extends Activity {
    Bitmap image;
    ImageView img;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes);

        Intent inIntent = getIntent();

        //홈에서 비트맵이미지 받아오기
        byte[] arr = getIntent().getByteArrayExtra("home_to_clothes_profile");
        image = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        img = findViewById(R.id.testimg);
        img.setImageBitmap(image);
    }
}
