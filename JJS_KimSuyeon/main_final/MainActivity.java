package com.example.main_final;

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

// 작성자: 김수연
// 11/6 Recyclerview와 FeedAdapter가 잘 작동하도록 작성
// 11/14 onContextItemSelected()에서 item.getItemId()가 R.id.menu_body_info인 case 삭제

// main 화면 작동 코드
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

        Bitmap[] bm4 = new Bitmap[8];
        bm4[0] = bm;
        bm4[1] = bm2;
        bm4[2] = bm3;

        Bitmap bm5 = BitmapFactory.decodeResource(getResources(), R.drawable.profile_ex);

        // Feed 생성하여 adapter에 연결
        adapter.addItem(new Feed("User", "룩", "전체 전체", "20대 전체", "Test", bm4, bm5));
        adapter.addItem(new Feed("꺅꺅", "룩", "아무나", "20대 혼종", "안녕하세요. 제 이름은 김수연입니다. 현재 국민대 소프트웨어융합대학에 재학 중이고, 학점은 너무 신경 쓰지 않습니다. 하지만 뭔 상관입니까 하하하", bm4, bm5));
        adapter.addItem(new Feed("주영남", "마실룩", "전체 전체", "20대 여성", bm4));
        adapter.addItem(new Feed("수연짱", "등교", "전체 여성", "10대 여성", bm));
        adapter.addItem(new Feed("진이", "출근룩", "10대 여성", "20대 여성", bm2));
        adapter.addItem(new Feed("솔찬", "등교", "20대 남성", "10대 남성", bm3));
        recyclerView.setAdapter(adapter);
    }

    // 각 Feed별 컨텍스트 메뉴의 메뉴 아이템이 선택되었을 때 실행되는 함수.
    // 각 Feed 내의 "더보기" 버튼을 눌렀을 때 컨텍스트 메뉴가 표시된다.
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = adapter.getPosition();
        Feed feed = adapter.items.get(position);
        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
        dlg.setPositiveButton("닫기", null);
        switch(item.getItemId()) {
            case R.id.menu_person_info:
                dlg.setTitle("성별/연령대 정보");
                dlg.setMessage(feed.menu_person_info);
                dlg.show();
                return true;
        }
        return false;
    }
}