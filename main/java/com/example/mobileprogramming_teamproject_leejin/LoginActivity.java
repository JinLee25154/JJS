/*
<유저정보생성창(Login) - Firebase 연동>
작성자 : 이진
 */


package com.example.mobileprogramming_teamproject_leejin;

//import android.support.design.widget.FloatingActionButton;
//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {

    private EditText user_nickname;
    private Spinner user_gender;
    private Spinner user_age;
    private EditText user_email;

    private Button login_button;

    private ImageView imageview;

    //Uri selectedImageUri;//프로필 사진
    Bitmap img;//프로필 사진(비트맵)
    InputStream in;//프로필 사진(비트맵)


    //데이터베이스에서 데이터를 읽거나 쓰려면 DatabaseReference의 인스턴스가 필요합니다.
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mDatabase = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("LOGIN");


        user_nickname = (EditText) findViewById(R.id.user_nickname);
        user_gender = (Spinner) findViewById(R.id.user_gender);
        user_age = (Spinner) findViewById(R.id.user_age);
        user_email = (EditText) findViewById(R.id.user_email);

        login_button = (Button) findViewById(R.id.login);

        //spinner Set
        final String[] gender_menu = {"남성","여성"};
        ArrayAdapter<String> adapter1;
        adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,gender_menu);
        user_gender.setAdapter(adapter1);

        //spinner Set
        final String[] age_menu = {"10세 이전","10대","20대","30대","40대","50대","60대","70대","80대","90대"};
        ArrayAdapter<String> adapter2;
        adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,age_menu);
        user_age.setAdapter(adapter2);

        //로그인 버튼 누르면 입력한 유저 정보를 파이어베이스 데이터베이스에 전송
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewPost(user_email.getText().toString(),user_nickname.getText().toString(), user_age.getSelectedItem().toString(), user_gender.getSelectedItem().toString());
                //인텐트 생성
                Intent intent_goHome = new Intent(getApplicationContext(),HomeActivity.class);
                //intent_goHome.putExtra("login_to_home_profile",selectedImageUri);//프로필사진
                //intent_goHome.putExtra("login_to_home_profile",img);//프로필 사진(비트맵 자체)

                //프로필 사진 전송 (바이트 스트림!)
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                //Bitmap sendBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.clothes1);//R.drawble.id로 하면 오류가 안난다.
                img.compress(Bitmap.CompressFormat.JPEG, 100, stream);//100kb가 넘으면 오류!
                //sendBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                intent_goHome.putExtra("login_to_home_profile",byteArray);

                //닉네임,연령대,성별,이메일 전송
                intent_goHome.putExtra("login_to_home_nickname",user_nickname.getText().toString());
                intent_goHome.putExtra("login_to_home_age",user_age.getSelectedItem().toString());
                intent_goHome.putExtra("login_to_home_gender",user_gender.getSelectedItem().toString());
                intent_goHome.putExtra("login_to_home_email",user_email.getText().toString());
                startActivity(intent_goHome);
            }
        });
        /*
        //프로필 사진 설정
        imageview = (ImageView)findViewById(R.id.imageview);
        imageview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
                
            }
        });
        */

        //프로필 사진 설정(비트맵)
        imageview = (ImageView)findViewById(R.id.imageview);
        imageview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });
    }

    //파이어베이스에 데이터 PUT
    private void writeNewPost(String uemail , String unickname, String uage, String ugender) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("posts").push().getKey();
        Post post = new Post(uemail, unickname, uage, ugender);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + unickname + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }
    /*
    //프로필 사진 설정
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            imageview.setImageURI(selectedImageUri)
        }

    }
     */
    //갤러리에서 가져온 프로필 사진 설정(비트맵)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                try {
                    // 선택한 이미지에서 비트맵 생성
                    //InputStream in = getContentResolver().openInputStream(data.getData());
                    in = getContentResolver().openInputStream(data.getData());
                    //Bitmap img = BitmapFactory.decodeStream(in);
                    img = BitmapFactory.decodeStream(in);
                    //in.close();
                    // 이미지 표시
                    imageview.setImageBitmap(img);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}