package com.example.main_final;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// 작성자: 김수연
// 11/6 각 Feed 내 View들의 기능 구현. ViewHolder 클래스 정의.
// 11/11 댓글 내용이 없을 때 댓글 업로드가 안 되는 기능 미작동, 알림 미작동 문제 해결
// -> Visibility() 문제: 댓글 내용이 있을 때만 Visible로 설정, Toast.makeText()에 show() 추가
// 11/14 nickname을 Button에서 TextView로 변경.
// editComment, btnComment, commentNickname, viewComment, btnDeleteComment 삭제 및 btnComment, btnDeleteComment 버튼클릭 리스너 삭제

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
        RadioGroup rGroup;
        final RadioButton rdoBest, rdoGood, rdoSoso, rdoBad, rdoToobad;

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
                if (viewFlipper1.getDisplayedChild() > 0) {
                    viewFlipper1.showPrevious();
                }
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

        rGroup = (RadioGroup) itemView.findViewById(R.id.rGroup);
        rdoBest = (RadioButton) itemView.findViewById(R.id.em_best);
        rdoGood = (RadioButton) itemView.findViewById(R.id.em_good);
        rdoSoso = (RadioButton) itemView.findViewById(R.id.em_soso);
        rdoBad = (RadioButton) itemView.findViewById(R.id.em_bad);
        rdoToobad = (RadioButton) itemView.findViewById(R.id.em_too_bad);

        rGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == rdoBest.getId()) {
                    rdoBest.setButtonDrawable(R.drawable.emoticon_customize_best);
                    rdoGood.setButtonDrawable(R.drawable.emoticon_customize_good_gray);
                    rdoSoso.setButtonDrawable(R.drawable.emoticon_customize_soso_gray);
                    rdoBad.setButtonDrawable(R.drawable.emoticon_customize_bad_gray);
                    rdoToobad.setButtonDrawable(R.drawable.emoticon_customize_too_bad_gray);
                }
                if (i == rdoGood.getId()) {
                    rdoBest.setButtonDrawable(R.drawable.emoticon_customize_best_gray);
                    rdoGood.setButtonDrawable(R.drawable.emoticon_customize_good);
                    rdoSoso.setButtonDrawable(R.drawable.emoticon_customize_soso_gray);
                    rdoBad.setButtonDrawable(R.drawable.emoticon_customize_bad_gray);
                    rdoToobad.setButtonDrawable(R.drawable.emoticon_customize_too_bad_gray);
                }
                if (i == rdoSoso.getId()) {
                    rdoBest.setButtonDrawable(R.drawable.emoticon_customize_best_gray);
                    rdoGood.setButtonDrawable(R.drawable.emoticon_customize_good_gray);
                    rdoSoso.setButtonDrawable(R.drawable.emoticon_customize_soso);
                    rdoBad.setButtonDrawable(R.drawable.emoticon_customize_bad_gray);
                    rdoToobad.setButtonDrawable(R.drawable.emoticon_customize_too_bad_gray);
                }
                if (i == rdoBad.getId()) {
                    rdoBest.setButtonDrawable(R.drawable.emoticon_customize_best_gray);
                    rdoGood.setButtonDrawable(R.drawable.emoticon_customize_good_gray);
                    rdoSoso.setButtonDrawable(R.drawable.emoticon_customize_soso_gray);
                    rdoBad.setButtonDrawable(R.drawable.emoticon_customize_bad);
                    rdoToobad.setButtonDrawable(R.drawable.emoticon_customize_too_bad_gray);
                }
                if (i == rdoToobad.getId()) {
                    rdoBest.setButtonDrawable(R.drawable.emoticon_customize_best_gray);
                    rdoGood.setButtonDrawable(R.drawable.emoticon_customize_good_gray);
                    rdoSoso.setButtonDrawable(R.drawable.emoticon_customize_soso_gray);
                    rdoBad.setButtonDrawable(R.drawable.emoticon_customize_bad_gray);
                    rdoToobad.setButtonDrawable(R.drawable.emoticon_customize_too_bad);
                }
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
        TextView nickname;
        Button category;
        Button target;
        Button btnMore;
        ImageView profile;
        TextView description;
        Feed feedItem;

        ImageView imageView;
        // ImageView 배열. 최대 개수는 8
        ImageView imvs[] = new ImageView[8];

        // ViewHolder 생성자
        public ViewHolder(View itemView) {
            super(itemView);
            nickname = (TextView) itemView.findViewById(R.id.nickname);
            category = (Button) itemView.findViewById(R.id.category);
            target = (Button) itemView.findViewById(R.id.target);
            btnMore = (Button) itemView.findViewById(R.id.btnMore);
            profile = (ImageView) itemView.findViewById(R.id.profile);
            description = (TextView) itemView.findViewById(R.id.description);

            imageView = (ImageView) itemView.findViewById(R.id.im1);

            int[] imid = { R.id.im1, R.id.im2, R.id.im3, R.id.im4, R.id.im5, R.id.im6, R.id.im7, R.id.im8};
            //imvs[0] = (ImageView) itemView.findViewById(R.id.im1);
            //imvs[1] = (ImageView) itemView.findViewById(R.id.im2);
            //imvs[2] = (ImageView) itemView.findViewById(R.id.im3);
            //imvs[3] = (ImageView) itemView.findViewById(R.id.im4);
            //imvs[4] = (ImageView) itemView.findViewById(R.id.im5);
            //imvs[5] = (ImageView) itemView.findViewById(R.id.im6);
            //imvs[6] = (ImageView) itemView.findViewById(R.id.im7);
            //imvs[7] = (ImageView) itemView.findViewById(R.id.im8);

            for (int i = 0; i < 8; i++) {
                imvs[i] = (ImageView) itemView.findViewById(imid[i]);
            }

            // adapter에서는 contextMenu를 직접 사용하지 못하므로 button 객체에 직접 ContextMenuListener를 설정해준다.
            btnMore.setOnCreateContextMenuListener(this);
        }

        // 뷰홀더 객체에 들어있는 뷰 객체의 데이터를 알맞은 Feed 내용으로 채워준다.
        public void setItem(Feed item) {
            nickname.setText(item.getNickname());
            category.setText(item.getCategory());
            target.setText(item.getTarget());
            profile.setImageBitmap(item.getProfile());
            description.setText(item.getDescription());

            imageView.setImageBitmap(item.getBitmap());

            int i = 0;
            while (item.getArrBM()[i] != null && i < 8) {
                imvs[i].setImageBitmap(item.getArrBM()[i]);
                i++;
            }

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
