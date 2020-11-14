/*
<설정창 - 사용자 개인정보 표시>
작성자 : 이진
 */

package com.example.mobileprogramming_teamproject_leejin;

import android.app.Activity;
import android.content.DialogInterface;
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
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SetActivity extends Activity {

    private EditText user_nickname;
    private TextView user_gender;
    private TextView user_age;
    private EditText user_email;

    Button btn_cancel;
    Button btn_finish;

    private ImageView imageview;//프로필 사진

    Bitmap img;//설정할 프로필 사진(비트맵)
    Bitmap image;//받아온 프로필
    InputStream in;

    Bitmap ori_profile;
    String ori_nickname;
    String ori_age;
    String ori_gender;
    String ori_email;

    Button btn_che_age;
    Button btn_che_gender;

    //데이터베이스에서 데이터를 읽거나 쓰려면 DatabaseReference의 인스턴스가 필요합니다.
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference mDatabase = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        setTitle("Set");

        //뷰 set
        imageview = (ImageView) findViewById(R.id.imageview);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_finish = (Button) findViewById(R.id.btn_finish);

        user_nickname = (EditText) findViewById(R.id.user_nickname);
        user_gender = (TextView) findViewById(R.id.user_gender);
        user_age = (TextView) findViewById(R.id.user_age);
        user_email = (EditText) findViewById(R.id.user_email);

        btn_che_age  = (Button) findViewById(R.id.btn_che_age);
        btn_che_gender  = (Button) findViewById(R.id.btn_che_gender);

        //로그인창으로부터 처음설정된 사용자 정보 가져오기
        //login창으로부터 받아온 정보
        Intent inIntent = getIntent();
        byte[] arr = getIntent().getByteArrayExtra("home_to_set_profile");
        image = BitmapFactory.decodeByteArray(arr, 0, arr.length);
        imageview.setImageBitmap(image);

        ori_nickname = inIntent.getStringExtra("home_to_set_nickname");//닉네임
        ori_age = inIntent.getStringExtra("home_to_set_age");//연령대
        ori_gender = inIntent.getStringExtra("home_to_set_gender");//성별
        ori_email = inIntent.getStringExtra("home_to_set_email");//이메일주소

        user_nickname.setText(ori_nickname);
        user_email.setText(ori_email);
        user_age.setText(ori_age);
        user_gender.setText(ori_gender);

        btn_che_gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] gender_menu = {"남성","여성"};
                AlertDialog.Builder dlg = new AlertDialog.Builder(SetActivity.this);
                dlg.setTitle("자신의 성별을 선택하세요.");
                dlg.setItems(gender_menu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        user_gender.setText(gender_menu[which]);
                    }
                });
                dlg.setPositiveButton("닫기",null);
                dlg.show();
            }
        });


        btn_che_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] age_menu = {"10세 이전","10대","20대","30대","40대","50대","60대","70대","80대","90대"};
                AlertDialog.Builder dlg = new AlertDialog.Builder(SetActivity.this);
                dlg.setTitle("자신의 연령대를 선택하세요.");
                dlg.setItems(age_menu, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        user_age.setText(age_menu[which]);
                    }
                });
                dlg.setPositiveButton("닫기",null);
                dlg.show();
            }
        });


        //취소버튼 누르면 홈으로 돌아가기
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //설정에서 완료 버튼 누르면  입력한 유저 정보를 파이어베이스 데이터베이스에 전송하고 홈으로 이동
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewPost(user_email.getText().toString(),user_nickname.getText().toString(),user_age.getText().toString(), user_gender.getText().toString());
                Intent intent_goHome = new Intent(getApplicationContext(),HomeActivity.class);
                //intent_goHome.putExtra("set_to_home_profile",selectedImageUri);
                //intent_goHome.putExtra("set_to_home_profile",img);
                //프로필 사진 전송
                if(img == null){//사용자가 프로필 사진을 바꾼 것이 아니라면
                    ByteArrayOutputStream stream1 = new ByteArrayOutputStream();
                    image.compress(Bitmap.CompressFormat.JPEG, 100, stream1);
                    byte[] byteArray1 = stream1.toByteArray();
                    intent_goHome.putExtra("set_to_home_profile",byteArray1);
                }
                else{//사용자가 프로필 사진을 바꾸었다면
                    ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                    img.compress(Bitmap.CompressFormat.JPEG, 100, stream2);
                    byte[] byteArray2 = stream2.toByteArray();
                    intent_goHome.putExtra("set_to_home_profile",byteArray2);
                }

                intent_goHome.putExtra("set_to_home_nickname",user_nickname.getText().toString());
                intent_goHome.putExtra("set_to_home_age",user_age.getText().toString());
                intent_goHome.putExtra("set_to_home_gender",user_gender.getText().toString());
                intent_goHome.putExtra("set_to_home_email",user_email.getText().toString());
                setResult(RESULT_OK, intent_goHome);
                finish();
            }
        });

        //갤러리로 이동 -  프로필 사진 설정(비트맵)
        imageview = (ImageView)findViewById(R.id.imageview);
        imageview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 2);
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
            imageview.setImageURI(selectedImageUri);
        }
    }
     */

    //갤러리에서 가져온 프로필 사진 설정(비트맵)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 2) {
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
