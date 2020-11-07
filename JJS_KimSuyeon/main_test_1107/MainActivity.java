package com.example.main_test_1107;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

// 작성자: 김수연, 11/6
// main 탭 작동 코드
public class MainActivity extends AppCompatActivity {
    Button btnMore;
    FeedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FeedAdapter();


        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.t_shirt);
        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.t_shirt2);
        Bitmap bm3 = BitmapFactory.decodeResource(getResources(), R.drawable.t_shirt3);
        adapter.addItem(new Feed("수연짱", "출근룩", "전체 여성", "통통함", "20대 여성", bm));
        adapter.addItem(new Feed("솔찬", "등교", "20대 남성", "날씬함", "10대 남성", bm2));
        adapter.addItem(new Feed("진이", "등교", "10대 여성", "보통", "10대 여성", bm3));

        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = adapter.getPosition();
        Feed feed = adapter.items.get(position);
        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
        dlg.setPositiveButton("닫기", null);
        switch(item.getItemId()) {
            case R.id.menu_body_info:
                dlg.setTitle("체형 정보");
                dlg.setMessage(feed.menu_body_info);
                dlg.show();
                if (null == item.getMenuInfo()) {
                    Log.d("myApp", "null");
                } else {
                    Log.d("myApp", item.getMenuInfo().getClass().getName());
                }
                return true;
            case R.id.menu_person_info:
                dlg.setTitle("성별/연령대 정보");
                dlg.setMessage(feed.menu_person_info);
                dlg.show();
                return true;
        }
        return false;
    }
}