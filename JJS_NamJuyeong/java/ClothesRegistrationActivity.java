package com.example.closet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.InputStream;

import static com.example.closet.FragmentAccessory.accessoryList;
import static com.example.closet.FragmentBottom.bottomList;
import static com.example.closet.FragmentOuter.outerList;
import static com.example.closet.FragmentShoes.shoesList;
import static com.example.closet.FragmentTop.topList;

public class ClothesRegistrationActivity extends Activity {

    ImageView choiceClothesImageView;

    //갤러리에서 사진을 가져왔는지 확인하는 flag
    boolean isBringImage;

    int category;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_registration);

        //옷 등록 화면 실행 시에는 false
        isBringImage = false;

        //ClosetActivity로 돌아가기
        Button btnReturnCloset = (Button) findViewById(R.id.btnReturnCloset);
        btnReturnCloset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        //카테고리 설정
        final Button choiceCategory = (Button) findViewById(R.id.choiceCategory);
        final String[] categoryArray = new String[] {"상의", "하의", "아우터", "신발", "액세서리"};
        final AlertDialog.Builder dlg = new AlertDialog.Builder(ClothesRegistrationActivity.this);
        dlg.setTitle("카테고리");
        dlg.setItems(categoryArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choiceCategory.setText(categoryArray[i]);
                category = i; //카테고리 정보 저장
            }
        });
        dlg.setPositiveButton("닫기", null);
        choiceCategory.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                dlg.show();
            }
        });

        //갤러리에서 사진 가져오기. onActivityResult에서 requestCode==0 부분 연계
        choiceClothesImageView = (ImageView) findViewById(R.id.choiceClothesPhoto);
        choiceClothesImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 0);
            }
        });

        //옷 설명 담을 변수 선언
        final EditText clothesExplanation = (EditText) findViewById(R.id.clothesExplanation);

        //옷 등록 버튼 기능 구현
        Button clothesRegistration = (Button)findViewById(R.id.clothesRegistration);
        clothesRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //옷 설명과 이미지 정보가 채워졌는지 확인
                if(clothesExplanation.getText().toString().getBytes().length > 0 && isBringImage){
                    //선택한 이미지 Bitmap 타입으로 가져오기
                    Drawable choiceClothesImageDrawable = choiceClothesImageView.getDrawable();
                    Bitmap choiceClothesImageBitmap = ((BitmapDrawable)choiceClothesImageDrawable).getBitmap();

                    //static 선언한 topList에 바로 추가
                    //카테고리별 옷 리스트에 비트맵, 카테고리, 옷설명 정보 담은 객체 추가
                    //category에 맞게 리스트 선택할 수 있도록 수정해야함 현재는 topList만 사용 중
                    if(category==0){
                        topList.add(new Clothes(choiceClothesImageBitmap, category, clothesExplanation.getText().toString()));
                    }else if(category==1){
                        bottomList.add(new Clothes(choiceClothesImageBitmap, category, clothesExplanation.getText().toString()));
                    }else if(category==2) {
                        outerList.add(new Clothes(choiceClothesImageBitmap, category, clothesExplanation.getText().toString()));
                    }else if(category==3) {
                        shoesList.add(new Clothes(choiceClothesImageBitmap, category, clothesExplanation.getText().toString()));
                    }else if(category==4) {
                        accessoryList.add(new Clothes(choiceClothesImageBitmap, category, clothesExplanation.getText().toString()));
                    }



                    finish();
                }else{
                    Toast.makeText(getApplicationContext(), "정보를 모두 입력하시지 않으셨습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //갤러리에서 사진 가져온 후 이미지뷰에 세팅
        if (requestCode == 0 && resultCode == RESULT_OK) {
            try{
                InputStream in = getContentResolver().openInputStream(data.getData());
                Bitmap img = BitmapFactory.decodeStream(in);
                in.close();
                choiceClothesImageView.setImageBitmap(img);
                isBringImage = true;
            }catch (Exception e){
            }
        }else if(requestCode == 0 && resultCode == RESULT_CANCELED){
            Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
        }
    }
}
