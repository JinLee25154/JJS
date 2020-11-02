/*
<홈>
작성자 : 이진
 */

package com.example.mobileprogramming_teamproject_leejin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {


    Uri ori_profile;
    String ori_nickname;
    String ori_age;
    String ori_gender;
    String ori_email;

    Uri che_profile;
    String che_nickname;
    String che_age;
    String che_gender;
    String che_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Home");


        TextView test = findViewById(R.id.test);


        //로그인창으로부터 처음설정된 사용자 정보 가져오기
        //login창으로부터 받아온 정보
        Intent inIntent = getIntent();
        ori_profile = getIntent().getParcelableExtra("login_to_home_profile");
        ori_nickname = inIntent.getStringExtra("login_to_home_nickname");
        ori_age = inIntent.getStringExtra("login_to_home_age");
        ori_gender = inIntent.getStringExtra("login_to_home_gender");
        ori_email = inIntent.getStringExtra("login_to_home_email");


        //홈에서 set 버튼 누르면 사용자 프로필 사진 전송
        Button btn_set = (Button) findViewById(R.id.btn_set);
        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_goSet = new Intent(getApplicationContext(),SetActivity.class);
                intent_goSet.putExtra("home_to_set_profile",ori_profile);
                intent_goSet.putExtra("home_to_set_nickname",ori_nickname);
                intent_goSet.putExtra("home_to_set_age",ori_age);
                intent_goSet.putExtra("home_to_set_gender",ori_gender);
                intent_goSet.putExtra("home_to_set_email",ori_email);
                startActivityForResult(intent_goSet,0);
            }
        });

    }

    //Set에서 받으면.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {//Set 화면에서 온 정보
            ori_profile = data.getParcelableExtra("set_to_home_profile");
            ori_nickname = data.getStringExtra("set_to_home_nickname");
            ori_age = data.getStringExtra("set_to_home_age");
            ori_gender = data.getStringExtra("set_to_home_gender");
            ori_email  = data.getStringExtra("set_to_home_email");

            TextView test = findViewById(R.id.test);
            test.setText(ori_nickname);
            Toast.makeText(getApplicationContext(), "Set에서 옴 ",Toast.LENGTH_LONG).show();

        }
    }
}