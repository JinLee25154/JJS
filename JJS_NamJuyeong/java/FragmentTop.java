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

import java.util.ArrayList;

public class FragmentTop extends Fragment {
    //Bitmap topSample = BitmapFactory.decodeResource(getResources(), R.drawable.top1);
    ArrayList<Clothes> topList = new ArrayList<Clothes>();
//    topList.add(new Clothes(topSample, 0, "샘플"));
//    Clothes[] topArray = {new Clothes(topSample, 0, "샘플")};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_top, container, false );

        final GridView gvTop = (GridView) view.findViewById(R.id.topGrid);
        TopGridAdapter gTopAdapter = new TopGridAdapter(container.getContext());
        gvTop.setAdapter(gTopAdapter);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            Clothes newClothes = (Clothes) bundle.getSerializable("clothes");
            topList.add(newClothes);
        }

        return view;
    }

    public class TopGridAdapter extends BaseAdapter {
        Context context;


        public TopGridAdapter(Context c){
            context = c;
        }

        @Override
        public int getCount() {
            return topList.size();
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

            imageview.setImageBitmap(topList.get(i).getImage());

            return imageview;
        }
    }






}
