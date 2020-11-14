package com.example.closet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.closet.FragmentBottom.bottomList;
import static com.example.closet.FragmentTop.topList;

public class ViewClothesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_clothes);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index", 0);
        int category = intent.getIntExtra("category", 0);

        Button btnReturnClosetInView = (Button)findViewById(R.id.btnReturnClosetInView);
        btnReturnClosetInView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        TextView categoryTextView = (TextView)findViewById(R.id.category);
        ImageView clothesImage = (ImageView)findViewById(R.id.clothesImage);
        if(category == 0) {
            categoryTextView.setText("상의");
            clothesImage.setImageBitmap(topList.get(index).getImage());
        }else if(category == 1) {
            categoryTextView.setText("하의");
            clothesImage.setImageBitmap(bottomList.get(index).getImage());
        }else if(category == 2) {
            categoryTextView.setText("아우터");
        }else if(category == 3) {
            categoryTextView.setText("신발");
        }else if(category == 4) {
            categoryTextView.setText("액세서리");
        }





        TextView clothesExplanation = (TextView)findViewById(R.id.clothesExplanation);
        clothesExplanation.setText(topList.get(index).getExplanation());



    }

}
