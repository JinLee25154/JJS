package com.example.mobileprogramming_teamproject_leejin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static com.example.mobileprogramming_teamproject_leejin.FragmentTop.topList;

@SuppressWarnings("deprecation")

public class UploadActivity extends AppCompatActivity {
    /*
    clothes12 -> clothes로 고치기!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    Bitmap image;
    ImageView gradimage;
    GridView gv;
    MyGridAdapter gAdapter;

    Button btn_cancel;
    Button btn_finish;

    //그리드
    ArrayList<Bitmap> grad = new ArrayList<Bitmap>();

    //갤러리
    ArrayList<Bitmap> galleryTop = new ArrayList<Bitmap>();
    ArrayList<Bitmap> galleryBottom = new ArrayList<Bitmap>();
    ArrayList<Bitmap> galleryOuter = new ArrayList<Bitmap>();
    ArrayList<Bitmap> galleryShoes = new ArrayList<Bitmap>();
    ArrayList<Bitmap> galleryAccessory = new ArrayList<Bitmap>();

    EditText description;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        /*
        BitmapDrawable drawable1 = (BitmapDrawable) getResources().getDrawable(R.drawable.top);//기본이미지 가져오기
        Bitmap defaultImg1 = drawable1.getBitmap();//기본이미지를 비트맵으로 변환해서


        BitmapDrawable drawable2 = (BitmapDrawable) getResources().getDrawable(R.drawable.shoes);//기본이미지 가져오기
        Bitmap defaultImg2 = drawable2.getBitmap();//기본이미지를 비트맵으로 변환해서


        BitmapDrawable drawable3 = (BitmapDrawable) getResources().getDrawable(R.drawable.skiny);//기본이미지 가져오기
        Bitmap defaultImg3 = drawable3.getBitmap();//기본이미지를 비트맵으로 변환해서


        c1 = new Clothes(defaultImg1,0,"상의입니다.");
        c2 = new Clothes(defaultImg2,3,"신발입니다.");
        c3 = new Clothes(defaultImg3,1,"바지입니다.");

        topList.add(c1);
        topList.add(c2);
        topList.add(c3);
        */
        //갤러리에 추가
        BitmapDrawable topFont = (BitmapDrawable) getResources().getDrawable(R.drawable.topfont);//기본이미지 가져오기
        Bitmap top_val = topFont.getBitmap();//기본이미지를 비트맵으로 변환해서
        galleryTop.add(top_val);

        BitmapDrawable bottomFont = (BitmapDrawable) getResources().getDrawable(R.drawable.bottomfont);//기본이미지 가져오기
        Bitmap bottom_val = bottomFont.getBitmap();//기본이미지를 비트맵으로 변환해서
        galleryBottom.add(bottom_val);

        BitmapDrawable outerFont = (BitmapDrawable) getResources().getDrawable(R.drawable.outerfont);//기본이미지 가져오기
        Bitmap outer_val = outerFont.getBitmap();//기본이미지를 비트맵으로 변환해서
        galleryOuter.add(outer_val);

        BitmapDrawable shoesFont = (BitmapDrawable) getResources().getDrawable(R.drawable.shoesfont);//기본이미지 가져오기
        Bitmap shoes_val = shoesFont.getBitmap();//기본이미지를 비트맵으로 변환해서
        galleryShoes.add(shoes_val);

        BitmapDrawable accessoryFont = (BitmapDrawable) getResources().getDrawable(R.drawable.accessoryfont);//기본이미지 가져오기
        Bitmap accessory_val = accessoryFont.getBitmap();//기본이미지를 비트맵으로 변환해서
        galleryAccessory.add(accessory_val);

        for(int i=0;i<topList.size();i++){
            if(topList.get(i).getCategory()==0){//상의이면       상의 하의 아우터 신발 악세사리
                galleryTop.add(topList.get(i).getImage());
            }
            else if(topList.get(i).getCategory()==1){//하의이면       상의 하의 아우터 신발 악세사리
                galleryBottom.add(topList.get(i).getImage());
            }
            else if(topList.get(i).getCategory()==2){//아우터이면       상의 하의 아우터 신발 악세사리
                galleryOuter.add(topList.get(i).getImage());
            }
            else if(topList.get(i).getCategory()==3){//신발이면       상의 하의 아우터 신발 악세사리
                galleryShoes.add(topList.get(i).getImage());
            }
            else if(topList.get(i).getCategory()==4){//악세사리이면       상의 하의 아우터 신발 악세사리
                galleryAccessory.add(topList.get(i).getImage());
            }
        }

        //사용자가 선택한 옷 이미지들을 보여줄 그리드뷰
        gv = (GridView) findViewById(R.id.gridView1);
        gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);


        //슬라이딩 드로우 내부 갤러리
        //상의
        Gallery gallery_top = (Gallery) findViewById(R.id.gallery_top);
        MyGalleryAdapterTop galAdapter1 = new MyGalleryAdapterTop(this);
        gallery_top.setAdapter(galAdapter1);

        //하의
        Gallery gallery_bottom = (Gallery) findViewById(R.id.gallery_bottom);
        MyGalleryAdapterBottom galAdapter2 = new MyGalleryAdapterBottom(this);
        gallery_bottom.setAdapter(galAdapter2);

        //아우터
        Gallery gallery_outer = (Gallery) findViewById(R.id.gallery_outer);
        MyGalleryAdapterOuter galAdapter3 = new MyGalleryAdapterOuter(this);
        gallery_outer.setAdapter(galAdapter3);

        //신발
        Gallery gallery_shoes = (Gallery) findViewById(R.id.gallery_shoes);
        MyGalleryAdapterShoes galAdapter4 = new MyGalleryAdapterShoes(this);
        gallery_shoes.setAdapter(galAdapter4);

        //악세사리
        Gallery gallery_accessory = (Gallery) findViewById(R.id.gallery_accessory);
        MyGalleryAdapterAccessory galAdapter5 = new MyGalleryAdapterAccessory(this);
        gallery_accessory.setAdapter(galAdapter5);

        //그리드뷰에서 아이템(옷 이미지)이 선택되면 delete하기
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //position은 이벤트가 발생한 아이템의 위치
                grad.remove(grad.get(position));//ArrayList(그리드뷰)에서 삭제
                //새로고침(변경 반영)
                gv.setAdapter(gAdapter);
                gAdapter.notifyDataSetChanged();
            }
        });


        //터치리스너 썼더니 스치는 순간 막 추가되어서 아이템 클릭 리스너로 바뀜
        gallery_top.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //position은 이벤트가 발생한 아이템의 위치
                grad.add(galleryTop.get(position));//그리드뷰 ArrayList에 추가
                //새로고침(변경 반영)
                gv.setAdapter(gAdapter);
                gAdapter.notifyDataSetChanged();
            }
        });

        gallery_bottom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //position은 이벤트가 발생한 아이템의 위치
                grad.add(galleryBottom.get(position));//그리드뷰 ArrayList에 추가
                //새로고침(변경 반영)
                gv.setAdapter(gAdapter);
                gAdapter.notifyDataSetChanged();
            }
        });

        gallery_outer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //position은 이벤트가 발생한 아이템의 위치
                grad.add(galleryOuter.get(position));//그리드뷰 ArrayList에 추가
                //새로고침(변경 반영)
                gv.setAdapter(gAdapter);
                gAdapter.notifyDataSetChanged();
            }
        });

        gallery_shoes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //position은 이벤트가 발생한 아이템의 위치
                grad.add(galleryShoes.get(position));//그리드뷰 ArrayList에 추가
                //새로고침(변경 반영)
                gv.setAdapter(gAdapter);
                gAdapter.notifyDataSetChanged();
            }
        });

        gallery_accessory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //position은 이벤트가 발생한 아이템의 위치
                grad.add(galleryAccessory.get(position));//그리드뷰 ArrayList에 추가
                //새로고침(변경 반영)
                gv.setAdapter(gAdapter);
                gAdapter.notifyDataSetChanged();
            }
        });

        //취소버튼, 완료버튼
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_finish = (Button) findViewById(R.id.btn_finish);

        //취소버튼 누르면 홈으로 돌아가기
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //수연님 액티비티와 합칠 때 주석 풀기!
        //설정에서 완료 버튼 누르면 홈으로 돌아가며 게시물 업로드 하기!(게시물 정보 전송)
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_goHome = new Intent(getApplicationContext(),HomeActivity.class);
                description = findViewById(R.id.describe);
                intent_goHome.putExtra("upload_to_home_describe",description.getText().toString());
                startActivity(intent_goHome);
            }
        });



    }

    //그리드뷰 Adapter
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
            gradimage.setLayoutParams(new GridView.LayoutParams(400, 300));
            gradimage.setScaleType(ImageView.ScaleType.FIT_CENTER);
            gradimage.setPadding(5, 5, 5, 5);

            gradimage.setImageBitmap(grad.get(position));

            return gradimage;
        }
    }

        //갤러리 Adapter - 상의
        public class MyGalleryAdapterTop extends BaseAdapter {
            Context context;
            public MyGalleryAdapterTop(Context c) {
                context = c;
            }

            public int getCount() {
                return galleryTop.size();
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

                imageview.setImageBitmap(galleryTop.get(position));

                return imageview;
            }
        }

    //갤러리 Adapter - 하의
    public class MyGalleryAdapterBottom extends BaseAdapter {
        Context context;
        public MyGalleryAdapterBottom (Context c) {
            context = c;
        }

        public int getCount() {
            return galleryBottom.size();
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

            imageview.setImageBitmap(galleryBottom.get(position));

            return imageview;
        }
    }

    //갤러리 Adapter - 아우터
    public class MyGalleryAdapterOuter extends BaseAdapter {
        Context context;
        public MyGalleryAdapterOuter(Context c) {
            context = c;
        }

        public int getCount() {
            return galleryOuter.size();
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

            imageview.setImageBitmap(galleryOuter.get(position));

            return imageview;
        }
    }

    //갤러리 Adapter - 신발
    public class MyGalleryAdapterShoes extends BaseAdapter {
        Context context;
        public MyGalleryAdapterShoes(Context c) {
            context = c;
        }

        public int getCount() {
            return galleryShoes.size();
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

            imageview.setImageBitmap(galleryShoes.get(position));

            return imageview;
        }
    }


    //갤러리 Adapter - 악세사리
    public class MyGalleryAdapterAccessory extends BaseAdapter {
        Context context;
        public MyGalleryAdapterAccessory(Context c) {
            context = c;
        }

        public int getCount() {
            return galleryAccessory.size();
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

            imageview.setImageBitmap(galleryAccessory.get(position));

            return imageview;
        }
    }



}

