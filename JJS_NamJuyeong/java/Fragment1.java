package com.example.closet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment1 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment1, container, false );

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        GridLayoutManager layoutManager = new GridLayoutManager(container.getContext(), 3);
        recyclerView.setLayoutManager(layoutManager);
        TopAdapter adapter = new TopAdapter();

        adapter.addItem(new Top(R.drawable.top1));
        adapter.addItem(new Top(R.drawable.top2));
        adapter.addItem(new Top(R.drawable.top3));

        recyclerView.setAdapter(adapter);

        return view;

    }






}
