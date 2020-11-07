package com.example.closet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class ClothesRegistrationActivity extends Activity {

    private final int GET_GALLERY_IMAGE = 200;
    private ImageView choiceClothesPhoto;
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
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            selectedImageUri = data.getData();
            choiceClothesPhoto.setImageURI(selectedImageUri);

        }

    }
}
