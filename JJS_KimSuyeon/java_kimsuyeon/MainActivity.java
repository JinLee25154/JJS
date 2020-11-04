package com.example.main_test_1029;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btnMore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);
        Button btnComment;
        final Button btnDeleteComment;
        ImageButton btnPrev, btnNext;
        final EditText editComment;
        final TextView viewComment;
        final TextView commentNickname;
        final ViewFlipper viewFlipper1;

        btnPrev = (ImageButton) findViewById(R.id.btnPrev);
        btnNext = (ImageButton) findViewById(R.id.btnNext);

        // ImageButton 의 svg Image 색 변경
        Drawable left_arrow = getResources().getDrawable(R.drawable.ic_left_arrow);
        Drawable right_arrow = getResources().getDrawable(R.drawable.ic_right_arrow);
        left_arrow.mutate();
        right_arrow.mutate();
        btnPrev.setImageResource(R.drawable.ic_left_arrow);
        btnNext.setImageResource(R.drawable.ic_right_arrow);

        viewFlipper1 = findViewById(R.id.viewFlipper1);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper1.showPrevious();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewFlipper1.showNext();
            }
        });
        btnMore = (Button) findViewById(R.id.btnMore);
        registerForContextMenu(btnMore);
        editComment = (EditText) findViewById(R.id.editComment);
        btnComment = (Button) findViewById(R.id.btnComment);
        viewComment = (TextView) findViewById(R.id.viewComment);
        btnDeleteComment = (Button) findViewById(R.id.btnDeleteComment);
        commentNickname = (TextView) findViewById(R.id.commentNickname);
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                if (null == viewComment) {
                    linearLayout1.addView(viewComment);
                }
                //
                if (viewComment.getVisibility() == android.view.View.GONE || btnDeleteComment.getVisibility() == android.view.View.GONE
                || commentNickname.getVisibility() == android.view.View.GONE) {
                    viewComment.setVisibility(android.view.View.VISIBLE);
                    btnDeleteComment.setVisibility(android.view.View.VISIBLE);
                    commentNickname.setVisibility(android.view.View.VISIBLE);
                }
                if (editComment.getText().toString() == "") {
                    Toast.makeText(getApplicationContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT);
                } else {
                    viewComment.setText(editComment.getText().toString());
                    editComment.setText("");
                }
            }
        });
        btnDeleteComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("댓글 삭제");
                dlg.setMessage("댓글을 삭제하시겠습니까?");
                dlg.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //linearLayout1.removeView(viewComment);
                        //linearLayout1.removeView(btnDeleteComment);
                        viewComment.setVisibility(android.view.View.GONE);
                        btnDeleteComment.setVisibility(android.view.View.GONE);
                        commentNickname.setVisibility(android.view.View.GONE);
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater mInflater = getMenuInflater();
        btnMore = (Button) findViewById(R.id.btnMore);
        if (v == btnMore) {
            menu.setHeaderTitle("더보기");
            mInflater.inflate(R.menu.menu_more, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
        dlg.setPositiveButton("닫기", null);
        switch(item.getItemId()) {
            case R.id.menu_body_info:
                dlg.setTitle("체형 정보");
                dlg.setMessage("체형 정보 내용");
                dlg.show();
                return true;
            case R.id.menu_person_info:
                dlg.setTitle("성별/연령대 정보");
                dlg.setMessage("성별/연령대 정보 내용");
                dlg.show();
                return true;
        }
        return false;
    }
}