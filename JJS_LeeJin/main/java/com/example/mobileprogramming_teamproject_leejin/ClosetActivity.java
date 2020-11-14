package com.example.mobileprogramming_teamproject_leejin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ClosetActivity extends Activity {
    /*
    //홈에서 비트맵이미지 받아오기
    Bitmap image;
    ImageView img;
     */

    //임시 clothes 객체
    Clothes c1;
    Clothes c2;
    Clothes c3;
    static ArrayList<Clothes> clothes12 = new ArrayList<Clothes>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_closet);

        //나중엔 주영님이 보내주신 객체 받아와야함(일단은 임시로 추가)

        BitmapDrawable drawable1 = (BitmapDrawable) getResources().getDrawable(R.drawable.top);//기본이미지 가져오기
        Bitmap defaultImg1 = drawable1.getBitmap();//기본이미지를 비트맵으로 변환해서


        BitmapDrawable drawable2 = (BitmapDrawable) getResources().getDrawable(R.drawable.shoes);//기본이미지 가져오기
        Bitmap defaultImg2 = drawable2.getBitmap();//기본이미지를 비트맵으로 변환해서


        BitmapDrawable drawable3 = (BitmapDrawable) getResources().getDrawable(R.drawable.skiny);//기본이미지 가져오기
        Bitmap defaultImg3 = drawable3.getBitmap();//기본이미지를 비트맵으로 변환해서


        //intent_goHome.putExtra("login_to_home_profile",byteArray1);
        c1 = new Clothes(defaultImg1,0,"상의입니다.");
        c2 = new Clothes(defaultImg2,1,"신발입니다.");
        c3 = new Clothes(defaultImg3,2,"바지입니다.");

        clothes12.add(c1);
        clothes12.add(c2);
        clothes12.add(c3);


        //Intent inIntent = getIntent();
        /*
        //홈에서 비트맵이미지 받아오기
        byte[] arr = getIntent().getByteArrayExtra("home_to_clothes_profile");
        image = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        img = findViewById(R.id.testimg);
        img.setImageBitmap(image);
         */


    }
}
