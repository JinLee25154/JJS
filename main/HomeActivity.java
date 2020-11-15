/*
<홈>
작성자 : 이진
 */

package com.example.mobileprogramming_teamproject_leejin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static com.example.mobileprogramming_teamproject_leejin.UploadActivity.selectExplanation;
import static com.example.mobileprogramming_teamproject_leejin.FragmentTop.topList;
public class HomeActivity extends AppCompatActivity {
    ImageView testImg;

    Bitmap ori_profile;
    String ori_nickname;
    String ori_age;
    String ori_gender;
    String ori_email;

    String description;

    Bitmap[] temp;
    String[] mainText;

    //==============수연==============
    FeedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("Home");
        Intent inIntent = getIntent();

        //=============수연,진 공동 작업=================


        if(selectExplanation != null){//사용자가 게시물 업로드 액티비티에서 완료버튼을 눌렀을 때
            int index=0;
            temp = new Bitmap[8];
            for(int i=0;i<selectExplanation.size();i++){

                for(int j=0;j<topList.size();j++){
                    if(selectExplanation.get(i).equals(topList.get(j).getExplanation())){//이미지가 일치할 때마다
                        description = inIntent.getStringExtra("upload_to_home_describe");


                        temp[index] = topList.get(j).getImage();
                        //System.out.println(topList.get(j).getExplanation());
                        if(topList.get(j).getExplanation() == null || topList.get(j).getExplanation().equals("")){
                            mainText[index] = "";
                        }
                        else{
                            //mainText[index] = topList.get(j).getExplanation();
                            mainText[index] = "null은 아님";
                        }

                        index++;
                        //Log.d("myApp", topList.get(j).getExplanation() + " 아하핳");
                    }
                }
            }
            //adapter.addItem(new Feed(ori_nickname, "카테고리", ori_age, ori_gender, description ,mainText, temp, ori_profile));
            //이름,카테고리,타겟,개인정보,글,옷이미지,프로필 사진
        }
        //================끝==========================================


        //===========수연=============
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FeedAdapter();

        // Bitmap 변수 추가
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.t_shirt);
        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.t_shirt2);
        Bitmap bm3 = BitmapFactory.decodeResource(getResources(), R.drawable.t_shirt3);

        Bitmap[] bm4 = new Bitmap[8];
        bm4[0] = bm;
        bm4[1] = bm2;
        bm4[2] = bm3;

        Bitmap bm5 = BitmapFactory.decodeResource(getResources(), R.drawable.profile_ex);

        // Feed 생성하여 adapter에 연결
        adapter.addItem(new Feed("User", "룩", "전체 전체", "20대 전체", "Test", bm4, bm5));
        adapter.addItem(new Feed("꺅꺅", "룩", "아무나", "20대 혼종", "안녕하세요. 제 이름은 김수연입니다. 현재 국민대 소프트웨어융합대학에 재학 중이고, 학점은 너무 신경 쓰지 않습니다. 하지만 뭔 상관입니까 하하하", bm4, bm5));
        adapter.addItem(new Feed("주영남", "마실룩", "전체 전체", "20대 여성", bm4));
        adapter.addItem(new Feed("수연짱", "등교", "전체 여성", "10대 여성", bm));
        adapter.addItem(new Feed("진이", "출근룩", "10대 여성", "20대 여성", bm2));
        adapter.addItem(new Feed("솔찬", "등교", "20대 남성", "10대 남성", bm3));
        recyclerView.setAdapter(adapter);


        //나중엔 주영님이 보내주신 객체 받아와야함(일단은 임시로 추가)
        /*
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

                startActivityForResult(intent_goUpload,2);
            }
        });

        //홈에서 mypage 버튼 누르면
        Button btn_Mypage = (Button) findViewById(R.id.btn_mypage);
        btn_Mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_goClothes = new Intent(getApplicationContext(), ClosetActivity.class);
                //startActivityForResult(intent_goClothes,3);
                startActivity(intent_goClothes);
            }
        });

    }

    //
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0 && resultCode == RESULT_OK) {//Set 화면에서 온 정보
            byte[] arr = data.getByteArrayExtra("set_to_home_profile");
            ori_profile = BitmapFactory.decodeByteArray(arr, 0, arr.length);

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
        /*
        else if(requestCode == 3 && resultCode == RESULT_OK) {//Clothes 화면에서 온 정보
            Toast.makeText(getApplicationContext(), "Clothes에서 옴 ",Toast.LENGTH_LONG).show();
        }
         */
    }

    //=============수연=============
    // 각 Feed별 컨텍스트 메뉴의 메뉴 아이템이 선택되었을 때 실행되는 함수.
    // 각 Feed 내의 "더보기" 버튼을 눌렀을 때 컨텍스트 메뉴가 표시된다.
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = adapter.getPosition();
        Feed feed = adapter.items.get(position);
        AlertDialog.Builder dlg = new AlertDialog.Builder(HomeActivity.this);
        dlg.setPositiveButton("닫기", null);
        switch(item.getItemId()) {
            case R.id.menu_person_info:
                dlg.setTitle("성별/연령대 정보");
                dlg.setMessage(feed.menu_person_info);
                dlg.show();
                return true;
        }
        return false;
    }



}