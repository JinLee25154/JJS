/*
<홈>
작성자 : 이진
 */

package com.example.mobileprogramming_teamproject_leejin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;

public class HomeActivity extends AppCompatActivity {
    ImageView testImg;

    Bitmap ori_profile;
    String ori_nickname;
    String ori_age;
    String ori_gender;
    String ori_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Home");


        //로그인창으로부터 처음설정된 사용자 정보 가져오기
        //login창으로부터 받아온 정보
        Intent inIntent = getIntent();
        //ori_profile = getIntent().getParcelableExtra("login_to_home_profile");//비트맵으로 프로필 사진 받아오기
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
                startActivityForResult(intent_goUpload,2);
            }
        });

        //홈에서 Clothes 버튼 누르면
        Button btn_Mypage = (Button) findViewById(R.id.btn_mypage);
        btn_Mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_goClothes = new Intent(getApplicationContext(),ClothesActivity.class);
                //비트맵 사용자 프로필 사진 바이트로 전송
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                ori_profile.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent_goClothes.putExtra("home_to_clothes_profile",byteArray);
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