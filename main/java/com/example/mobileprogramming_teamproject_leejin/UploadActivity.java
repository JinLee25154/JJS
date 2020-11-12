package com.example.mobileprogramming_teamproject_leejin;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class UploadActivity extends Activity {
    Bitmap image;
    ImageView gradimage;
    GridView gv;
    MyGridAdapter gAdapter;

    ArrayList<Integer> grad = new ArrayList<Integer>();
    ArrayList<Integer> gal = new ArrayList<Integer>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        gal.add(R.drawable.clothes1);
        gal.add(R.drawable.clothes2);
        gal.add(R.drawable.clothes3);
        gal.add(R.drawable.clothes4);
        gal.add(R.drawable.clothes5);
        gal.add(R.drawable.clothes6);

        Intent inIntent = getIntent();
        byte[] arr = getIntent().getByteArrayExtra("home_to_upload_profile");
        image = BitmapFactory.decodeByteArray(arr, 0, arr.length);


        //사용자가 선택한 옷 배치 보여줄 그리드뷰
        gv = (GridView) findViewById(R.id.gridView1);
        gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);
        /*
        //그리드뷰에서 아이템이 선택되면
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        */
        //슬라이딩 도어 내부 갤러리
        //상의
        Gallery gallery1 = (Gallery) findViewById(R.id.gallery_top);
        MyGalleryAdapter galAdapter1 = new MyGalleryAdapter(this);
        gallery1.setAdapter(galAdapter1);
        /*
        //갤러리에서 아이템이 선택되면
        //그리드뷰에 추가하기
        gallery1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(AdapterView<?> adapterView, View view, int i, long l) {
                imageview.setImageBitmap(image);
            }
        });
        */
        //하의
        Gallery gallery2 = (Gallery) findViewById(R.id.gallery_bottom);
        MyGalleryAdapter galAdapter2 = new MyGalleryAdapter(this);
        gallery2.setAdapter(galAdapter2);

        //아우터
        Gallery gallery3 = (Gallery) findViewById(R.id.gallery_outer);
        MyGalleryAdapter galAdapter3 = new MyGalleryAdapter(this);
        gallery3.setAdapter(galAdapter3);




    }

    //그리드뷰 구현
    public class MyGridAdapter extends BaseAdapter {
        Context context;

        public MyGridAdapter(Context c) {
            context = c;
        }

        public int getCount() {
            return grad.size();
        }

        public Object getItem(int position) {

            return null;
        }

        public long getItemId(int position) {

            return 0;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            gradimage = new ImageView(context);
            gradimage.setLayoutParams(new GridView.LayoutParams(200, 300));
            gradimage.setScaleType(ImageView.ScaleType.FIT_CENTER);
            gradimage.setPadding(5, 5, 5, 5);

            gradimage.setImageResource(grad.get(position));

            //gradimage.setImageResource(posterID[position]);
            return gradimage;
        }
    }

    //갤러리 구현
    public class MyGalleryAdapter extends BaseAdapter {
        Context context;


        public MyGalleryAdapter(Context c) {
            context = c;
        }

        public int getCount() {
            return gal.size();
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageview = new ImageView(context);

            imageview.setLayoutParams(new Gallery.LayoutParams(200, 300));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5, 5, 5, 5);

            imageview.setImageResource(gal.get(position));


            final int pos = position;
            imageview.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    grad.add(gal.get(pos));
                    gv.setAdapter(gAdapter);

                    gAdapter.notifyDataSetChanged();
                    return false;
                }
            });

            //imageview.setImageBitmap(image);//비트맵 이미지 갤러리에 추가

            return imageview;
        }
    }
}

