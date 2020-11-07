package com.example.main_test_1107;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

// 작성자: 김수연, 11/6
public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder>  {
    ArrayList<Feed> items = new ArrayList<Feed>();
    HashMap<Button, Feed> feedMap = new HashMap<Button, Feed>();
    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.feed_item, viewGroup, false);
        final LinearLayout linearLayout1 = (LinearLayout) itemView.findViewById(R.id.linearLayout1);
        Button btnMore;
        Button btnComment;
        final Button btnDeleteComment;
        ImageButton btnPrev, btnNext;
        final EditText editComment;
        final TextView viewComment;
        final TextView commentNickname;
        final ViewFlipper viewFlipper1;

        btnPrev = (ImageButton) itemView.findViewById(R.id.btnPrev);
        btnNext = (ImageButton) itemView.findViewById(R.id.btnNext);

        // ImageButton 의 svg Image 색 변경
        Drawable left_arrow = itemView.getResources().getDrawable(R.drawable.ic_left_arrow);
        Drawable right_arrow = itemView.getResources().getDrawable(R.drawable.ic_right_arrow);
        left_arrow.mutate();
        right_arrow.mutate();
        btnPrev.setImageResource(R.drawable.ic_left_arrow);
        btnNext.setImageResource(R.drawable.ic_right_arrow);

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

        btnMore = (Button) itemView.findViewById(R.id.btnMore);
        ((Activity)viewGroup.getContext()).registerForContextMenu(btnMore);
        editComment = (EditText) itemView.findViewById(R.id.editComment);
        btnComment = (Button) itemView.findViewById(R.id.btnComment);
        viewComment = (TextView) itemView.findViewById(R.id.viewComment);
        btnDeleteComment = (Button) itemView.findViewById(R.id.btnDeleteComment);
        commentNickname = (TextView) itemView.findViewById(R.id.commentNickname);
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewComment.getVisibility() == android.view.View.GONE || btnDeleteComment.getVisibility() == android.view.View.GONE
                        || commentNickname.getVisibility() == android.view.View.GONE) {
                    viewComment.setVisibility(android.view.View.VISIBLE);
                    btnDeleteComment.setVisibility(android.view.View.VISIBLE);
                    commentNickname.setVisibility(android.view.View.VISIBLE);
                }
                if ("" == editComment.getText().toString()) {
                    Toast.makeText(inflater.getContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT);
                } else {
                    viewComment.setText(editComment.getText().toString());
                    editComment.setText("");
                }
            }
        });
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

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        Button nickname;
        Button category;
        Button target;
        Button btnMore;
        Feed feedItem;

        static HashMap<View, ViewHolder> viewHolderMap = new HashMap<View, ViewHolder>();

        ArrayList<ImageView> imageViews = new ArrayList<ImageView>();
        ImageView imv;

        public ViewHolder(View itemView) {
            super(itemView);
            nickname = (Button) itemView.findViewById(R.id.nickname);
            category = (Button) itemView.findViewById(R.id.category);
            target = (Button) itemView.findViewById(R.id.target);
            btnMore = (Button) itemView.findViewById(R.id.btnMore);

            viewHolderMap.put(btnMore, this);

            imv = (ImageView) itemView.findViewById(R.id.im1);
            btnMore.setOnCreateContextMenuListener(this);

        }
        public void setItem(Feed item) {
            nickname.setText(item.getNickname());
            category.setText(item.getCategory());
            target.setText(item.getTarget());
            imv.setImageBitmap(item.getBitmap());
            feedItem = item;
        }
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater mInflater = ((Activity)v.getContext()).getMenuInflater();
            menu.setHeaderTitle("더보기");
            mInflater.inflate(R.menu.menu_more, menu);
        }
    }
}
