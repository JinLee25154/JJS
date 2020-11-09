package com.example.closet;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class FragmentBottom extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.bottom_item, container, false );

        final GridView gvBottom = (GridView) view.findViewById(R.id.bottomGrid);
        BottomGridAdapter gBottomAdapter = new BottomGridAdapter(container.getContext());
        gvBottom.setAdapter(gBottomAdapter);

        return view;
    }

    public class BottomGridAdapter extends BaseAdapter {
        Context context;
        Integer[] bottomID = {
                R.drawable.bottom1, R.drawable.bottom2, R.drawable.bottom3, R.drawable.bottom4, R.drawable.bottom5, R.drawable.bottom6
        };
        public BottomGridAdapter(Context c){
            context = c;
        }

        @Override
        public int getCount() {
            return bottomID.length;
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

            imageview.setImageResource(bottomID[i]);

            return imageview;
        }
    }

}
