package com.example.closet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.InputStream;

public class ClothesRegistrationActivity extends Activity {

    private final int REQUEST_CODE = 1;
    private ImageView choiceClothesPhoto;
    Bitmap clothesImage = null;
    int category;
    Uri selectedImageUri;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_registration);

        Button btnReturnCloset = (Button) findViewById(R.id.btnReturnCloset);
        btnReturnCloset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        final Button choiceCategory = (Button) findViewById(R.id.choiceCategory);
        final String[] categoryArray = new String[] {"상의", "하의", "아우터", "신발", "액세서리"};
        final AlertDialog.Builder dlg = new AlertDialog.Builder(ClothesRegistrationActivity.this);
        dlg.setTitle("카테고리");
        dlg.setItems(categoryArray, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choiceCategory.setText(categoryArray[i]);
                category = i;
            }
        });
        dlg.setPositiveButton("닫기", null);
        choiceCategory.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                dlg.show();
            }
        });

        choiceClothesPhoto = (ImageView) findViewById(R.id.choiceClothesPhoto);
        choiceClothesPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        final EditText clothesExplanation = (EditText) findViewById(R.id.clothesExplanation);

        Button clothesRegistration = (Button)findViewById(R.id.clothesRegistration);
        clothesRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //글 설명은 입력하지 않아도 되게 수정하기, clothesExplanation.getText() != null 이거 되는지 확인
                //clothesExplanation.getText().toString().equals("")
                if(clothesImage!=null && !(clothesExplanation.getText().toString().getBytes().length <= 0)){
                    Fragment fragment = new Fragment();
                    Bundle bundle = new Bundle();
                    Clothes clothes = new Clothes(clothesImage, category, clothesExplanation.getText().toString());
                    bundle.putSerializable("clothes", clothes);
                    fragment.setArguments(bundle);

//                    Intent intent = new Intent(getApplicationContext(), FragmentTop.class);
//                    Clothes clothes = new Clothes(clothesImage, category, clothesExplanation.getText().toString());
//                    intent.putExtra("clothes", clothes);
//                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "정보를 모두 입력하시지 않으셨습니다.", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            try{
                InputStream in = getContentResolver().openInputStream(data.getData());
                Bitmap img = BitmapFactory.decodeStream(in);
                clothesImage = BitmapFactory.decodeStream(in);
                in.close();
                choiceClothesPhoto.setImageBitmap(img);
            }catch (Exception e){
            }
        }else if(resultCode == RESULT_CANCELED){
            Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
        }
    }
}
