package com.example.mobileprogramming_teamproject_leejin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class TestActivity extends AppCompatActivity {
    ArrayList<Clothes> testList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        testList = new ArrayList<>();
        Intent intent = getIntent();
        testList = intent.getParcelableArrayListExtra("test");

        ImageView testImage = (ImageView)findViewById(R.id.testImage);
        testImage.setImageBitmap(testList.get(0).getImage());

    }
}
