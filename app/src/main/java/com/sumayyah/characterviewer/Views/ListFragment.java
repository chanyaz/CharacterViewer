package com.sumayyah.characterviewer.Views;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sumayyah.characterviewer.R;

/**
 * Created by sumayyah on 8/10/16.
 */
public class ListFragment extends Fragment {

    private static final int VIEW_LIST = 1;
    private static final int VIEW_GRID = 2;

    private RecyclerView list;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.list_fragment_layout, container, false);

        //Setup view
        list = (RecyclerView) v.findViewById(R.id.list);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(VIEW_LIST, StaggeredGridLayoutManager.VERTICAL);
        list.setLayoutManager(staggeredGridLayoutManager);

        return v;
    }
}
