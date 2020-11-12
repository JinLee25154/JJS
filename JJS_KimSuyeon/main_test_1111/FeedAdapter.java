package com.example.main_test_1111;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// 작성자: 김수연
// 11/6 각 Feed 내 View들의 기능 구현. ViewHolder 클래스 정의.
// 11/11 댓글 내용이 없을 때 댓글 업로드가 안 되는 기능 미작동, 알림 미작동 문제 해결
// -> Visibility() 문제: 댓글 내용이 있을 때만 Visible로 설정, Toast.makeText()에 show() 추가


// Feed 데이터들을 관리할 FeedAdapter 클래스
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder>  {
    ArrayList<Feed> items = new ArrayList<Feed>();
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    // feed_item.xml에 포함된 view들의 기능 구현
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.feed_item, viewGroup, false);
        Button btnMore;
        ImageButton btnPrev, btnNext;
        final ViewFlipper viewFlipper1;
        final EditText editComment;
        Button btnComment;
        final TextView commentNickname;
        final TextView viewComment;
        final Button btnDeleteComment;

        btnPrev = (ImageButton) itemView.findViewById(R.id.btnPrev);
        btnNext = (ImageButton) itemView.findViewById(R.id.btnNext);

        // ImageButton 의 svg Image 색 변경
        Drawable left_arrow = itemView.getResources().getDrawable(R.drawable.ic_left_arrow);
        Drawable right_arrow = itemView.getResources().getDrawable(R.drawable.ic_right_arrow);
        left_arrow.mutate();
        right_arrow.mutate();
        btnPrev.setImageResource(R.drawable.ic_left_arrow);
        btnNext.setImageResource(R.drawable.ic_right_arrow);

        // ViewFlipper 객체의 메서드와 버튼의 onClickListener를 이용하여 이미지뷰를 전환할 수 있게 함.
        viewFlipper1 = itemView.findViewById(R.id.viewFlipper1);
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

        // feed_item의 뷰들 가져오기
        btnMore = (Button) itemView.findViewById(R.id.btnMore);
        ((Activity)viewGroup.getContext()).registerForContextMenu(btnMore);
        editComment = (EditText) itemView.findViewById(R.id.editComment);
        btnComment = (Button) itemView.findViewById(R.id.btnComment);
        commentNickname = (TextView) itemView.findViewById(R.id.commentNickname);
        viewComment = (TextView) itemView.findViewById(R.id.viewComment);
        btnDeleteComment = (Button) itemView.findViewById(R.id.btnDeleteComment);

        // 댓글 달기 버튼 이벤트 처리
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(editComment.getText().toString())) {
                    Toast.makeText(inflater.getContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    if (viewComment.getVisibility() == android.view.View.GONE || btnDeleteComment.getVisibility() == android.view.View.GONE
                            || commentNickname.getVisibility() == android.view.View.GONE) {
                        commentNickname.setVisibility(android.view.View.VISIBLE);
                        viewComment.setVisibility(android.view.View.VISIBLE);
                        btnDeleteComment.setVisibility(android.view.View.VISIBLE);
                    }
                    viewComment.setText(editComment.getText().toString());
                    editComment.setText("");
                }
            }
        });
        // 댓글 삭제 버튼 이벤트 처리
        btnDeleteComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(inflater.getContext());
                dlg.setTitle("댓글 삭제");
                dlg.setMessage("댓글을 삭제하시겠습니까?");
                dlg.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        viewComment.setVisibility(android.view.View.GONE);
                        btnDeleteComment.setVisibility(android.view.View.GONE);
                        commentNickname.setVisibility(android.view.View.GONE);
                    }
                });
                dlg.setNegativeButton("취소", null);
                dlg.show();
            }
        });
        return new ViewHolder(itemView);
    }

    // 뷰홀더 객체 재사용하는 함수
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        Feed item = items.get(position);
        viewHolder.setItem(item);
        viewHolder.btnMore.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(viewHolder.getPosition());
                return false;
            }
        });
    }

    // 뷰홀더 객체가 재사용될 때 호출되는 함수
    @Override
    public void onViewRecycled(ViewHolder holder) {
        holder.btnMore.setOnLongClickListener(null);
        super.onViewRecycled(holder);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Feed item) { items.add(item); }

    public void setItems(ArrayList<Feed> items) {
        this.items = items;
    }

    public Feed getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Feed item) {
        items.set(position, item);
    }

    // ViewHolder 클래스
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        Button nickname;
        Button category;
        Button target;
        Button btnMore;
        Feed feedItem;

        // ImageView를 저장하는 ArrayList. ImageView의 수가 가변적이므로 ArrayList로 선언함.
        ArrayList<ImageView> imageViews = new ArrayList<ImageView>();
        ImageView imv1, imv2, imv3;

        // ViewHolder 생성자
        public ViewHolder(View itemView) {
            super(itemView);
            nickname = (Button) itemView.findViewById(R.id.nickname);
            category = (Button) itemView.findViewById(R.id.category);
            target = (Button) itemView.findViewById(R.id.target);
            btnMore = (Button) itemView.findViewById(R.id.btnMore);
            imv1 = (ImageView) itemView.findViewById(R.id.im1);
            imv2 = (ImageView) itemView.findViewById(R.id.im2);
            imv3 = (ImageView) itemView.findViewById(R.id.im3);

//            imageViews.add(imv1);
//            imageViews.add(imv2);
//            imageViews.add(imv3);

            // adapter에서는 contextMenu를 직접 사용하지 못하므로 button 객체에 직접 ContextMenuListener를 설정해준다.
            btnMore.setOnCreateContextMenuListener(this);
        }

        // 뷰홀더 객체에 들어있는 뷰 객체의 데이터를 알맞은 Feed 내용으로 채워준다.
        public void setItem(Feed item) {
            nickname.setText(item.getNickname());
            category.setText(item.getCategory());
            target.setText(item.getTarget());

            imv1.setImageBitmap(item.getBitmap());

//            imv1.setImageBitmap(item.getArrBM().get(0));
//            imv2.setImageBitmap(item.getArrBM().get(1));
//            imv3.setImageBitmap(item.getArrBM().get(2));
//
//            imageViews.add(imv1);
//            imageViews.add(imv2);
//            imageViews.add(imv3);

            feedItem = item;
        }

        // 각 피드별 더보기 컨텍스트 메뉴 생성
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater mInflater = ((Activity)v.getContext()).getMenuInflater();
            menu.setHeaderTitle("더보기");
            mInflater.inflate(R.menu.menu_more, menu);
        }
    }
}
