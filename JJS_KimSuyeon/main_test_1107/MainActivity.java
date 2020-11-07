package com.example.main_test_1107;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

// 작성자: 김수연, 11/6
// main 탭 작동 코드
public class MainActivity extends AppCompatActivity {
    Button btnMore;
    FeedAdapter adapter;

    // MainActivity가 생성되었을 때 실행되는 함수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FeedAdapter();

        // Bitmap 변수 추가
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.t_shirt);
        Bitmap bm2 = BitmapFactory.decodeResource(getResources(), R.drawable.t_shirt2);
        Bitmap bm3 = BitmapFactory.decodeResource(getResources(), R.drawable.t_shirt3);

        // Feed 생성하여 adapter에 연결
        adapter.addItem(new Feed("수연짱", "출근룩", "전체 여성", "통통함", "20대 여성", bm));
        adapter.addItem(new Feed("솔찬", "등교", "20대 남성", "날씬함", "10대 남성", bm2));
        adapter.addItem(new Feed("진이", "등교", "10대 여성", "보통", "10대 여성", bm3));

        recyclerView.setAdapter(adapter);
    }

    // 컨텍스트메뉴의 메뉴 아이템이 선택되었을 때 실행되는 함수
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