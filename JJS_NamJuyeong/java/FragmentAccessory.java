package com.example.closet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class FragmentAccessory extends Fragment {

    public static ArrayList<Clothes> accessoryList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_accessory, container, false );

        //그리드뷰 어댑터 설정 아래 TopGridAdapter 클래스 연계
        final GridView gvAccessory = (GridView) view.findViewById(R.id.accessoryGrid);
        AccessoryGridAdapter gAccessoryAdapter = new AccessoryGridAdapter(container.getContext());
        gvAccessory.setAdapter(gAccessoryAdapter);

        return view;
    }

    public class AccessoryGridAdapter extends BaseAdapter  {
        Context context;
        int index;

        public AccessoryGridAdapter(Context c){
            context = c;
        }

        @Override
        public int getCount() {
            return accessoryList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new GridView.LayoutParams(350, 450));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5, 5, 5, 5);
            imageview.setImageBitmap(accessoryList.get(i).getImage());
            index = i;

            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View goView = (View) View.inflate(getContext(), R.layout.activity_view_clothes, null);
                    Intent intent = new Intent(getContext(), ViewClothesActivity.class);
                    intent.putExtra("index", index);
                    intent.putExtra("category", 4);
                    startActivity(intent);
                }
            });
            return imageview;
        }
    }






}
