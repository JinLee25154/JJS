/*
<홈>
작성자 : 이진
 */

package com.example.mobileprogramming_teamproject_leejin;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class HomeActivity extends Activity {
    ImageView testImg;

    Bitmap ori_profile;
    String ori_nickname;
    String ori_age;
    String ori_gender;
    String ori_email;

    //ArrayList<Clothes> clothes;
    //ArrayList<Bitmap> bitmap;//나중에 지우자

    //ArrayList<byte[]> be;
    //임시 clothes 객체
    //Clothes c1;
    //Clothes c2;
    //Clothes c3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Home");

        //나중엔 주영님이 보내주신 객체 받아와야함(일단은 임시로 추가)

        BitmapDrawable drawable1 = (BitmapDrawable) getResources().getDrawable(R.drawable.top);//기본이미지 가져오기
        Bitmap defaultImg1 = drawable1.getBitmap();//기본이미지를 비트맵으로 변환해서
        //바이트 스트림으로 전송
        ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
        defaultImg1.compress(Bitmap.CompressFormat.JPEG, 100, stream1);
        byte[] byteArray1 = stream1.toByteArray();
        //be.add(byteArray1);

        BitmapDrawable drawable2 = (BitmapDrawable) getResources().getDrawable(R.drawable.shoes);//기본이미지 가져오기
        Bitmap defaultImg2 = drawable2.getBitmap();//기본이미지를 비트맵으로 변환해서
        //바이트 스트림으로 전송
        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
        defaultImg2.compress(Bitmap.CompressFormat.JPEG, 100, stream2);
        byte[] byteArray2 = stream2.toByteArray();
        //be.add(byteArray2);

        BitmapDrawable drawable3 = (BitmapDrawable) getResources().getDrawable(R.drawable.skiny);//기본이미지 가져오기
        Bitmap defaultImg3 = drawable3.getBitmap();//기본이미지를 비트맵으로 변환해서
        //바이트 스트림으로 전송
        ByteArrayOutputStream stream3 = new ByteArrayOutputStream();
        defaultImg3.compress(Bitmap.CompressFormat.JPEG, 100, stream3);
        byte[] byteArray3 = stream3.toByteArray();
        //be.add(byteArray3);
        /*
        //intent_goHome.putExtra("login_to_home_profile",byteArray1);
        c1 = new Clothes(defaultImg1,0,"상의입니다.");
        c2 = new Clothes(defaultImg2,1,"신발입니다.");
        c3 = new Clothes(defaultImg3,2,"바지입니다.");
         */
        /*
        clothes.add(c1);
        clothes.add(c2);
        clothes.add(c3);
        */

        //로그인창으로부터 처음설정된 사용자 정보 가져오기
        //login창으로부터 받아온 정보
        Intent inIntent = getIntent();
        //
        byte[] arr = getIntent().getByteArrayExtra("login_to_home_profile");
        ori_profile = BitmapFactory.decodeByteArray(arr, 0, arr.length);//byteArray로 받아온 Bitmap
        testImg = findViewById(R.id.testImg);
        testImg.setImageBitmap(ori_profile);


        ori_nickname = inIntent.getStringExtra("login_to_home_nickname");
        ori_age = inIntent.getStringExtra("login_to_home_age");
        ori_gender = inIntent.getStringExtra("login_to_home_gender");
        ori_email = inIntent.getStringExtra("login_to_home_email");


        //홈에서 set 버튼 누르면 사용자 정보 전송
        Button btn_set = (Button) findViewById(R.id.btn_set);
        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_goSet = new Intent(getApplicationContext(),SetActivity.class);//인텐트 생성
                //intent_goSet.putExtra("home_to_set_profile",ori_profile);//사용자 프로필 사진
                //비트맵 사용자 프로필 사진 바이트로 전송
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                ori_profile.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent_goSet.putExtra("home_to_set_profile",byteArray);

                intent_goSet.putExtra("home_to_set_nickname",ori_nickname);//사용자 닉네임
                intent_goSet.putExtra("home_to_set_age",ori_age);//사용자 연령대
                intent_goSet.putExtra("home_to_set_gender",ori_gender);//사용자 성별
                intent_goSet.putExtra("home_to_set_email",ori_email);//사용자 이메일
                startActivityForResult(intent_goSet,0);
            }
        });

        //홈에서 Upload 버튼 누르면
        Button btn_upload = (Button) findViewById(R.id.btn_upload);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_goUpload = new Intent(getApplicationContext(),UploadActivity.class);//인텐트 생성
                //비트맵 사용자 프로필 사진 바이트로 전송
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                ori_profile.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent_goUpload.putExtra("home_to_upload_profile",byteArray);
                //intent_goUpload.putExtra("home_to_upload_byte",be);
                startActivityForResult(intent_goUpload,2);
            }
        });

        //홈에서 Clothes 버튼 누르면
        Button btn_Mypage = (Button) findViewById(R.id.btn_mypage);
        btn_Mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_goClothes = new Intent(getApplicationContext(), ClosetActivity.class);
                /*
                //비트맵 사용자 프로필 사진 바이트로 전송
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                ori_profile.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent_goClothes.putExtra("home_to_clothes_profile",byteArray);
                */
                //intent_goClothes.putExtra("clothes",clothes);
                startActivityForResult(intent_goClothes,3);
            }
        });

    }

    //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0 && resultCode == RESULT_OK) {//Set 화면에서 온 정보
            byte[] arr = data.getByteArrayExtra("set_to_home_profile");
            ori_profile = BitmapFactory.decodeByteArray(arr, 0, arr.length);
            testImg = findViewById(R.id.testImg);
            testImg.setImageBitmap(ori_profile);

            //testImg.setImageResource(R.drawable.clothes1);
            ori_nickname = data.getStringExtra("set_to_home_nickname");
            ori_age = data.getStringExtra("set_to_home_age");
            ori_gender = data.getStringExtra("set_to_home_gender");
            ori_email  = data.getStringExtra("set_to_home_email");

            Toast.makeText(getApplicationContext(), "Set에서 옴 ",Toast.LENGTH_LONG).show();
        }

        else if(requestCode == 2 && resultCode == RESULT_OK) {//Upload 화면에서 온 정보
            Toast.makeText(getApplicationContext(), "Upload에서 옴 ",Toast.LENGTH_LONG).show();
        }
        else if(requestCode == 3 && resultCode == RESULT_OK) {//Clothes 화면에서 온 정보
            Toast.makeText(getApplicationContext(), "Clothes에서 옴 ",Toast.LENGTH_LONG).show();
        }
    }
}