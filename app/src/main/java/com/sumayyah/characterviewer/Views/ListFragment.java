package com.sumayyah.characterviewer.Views;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sumayyah.characterviewer.R;

import Adapters.CharacterListAdapter;

/**
 * Created by sumayyah on 8/10/16.
 */
public class ListFragment extends Fragment implements MainActivity.SpanCountInterface {


    private static final String KEY_LAYOUT_MANAGER = "layoutManager";

    private RecyclerView list;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private CharacterListAdapter adapter;
    private View v;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.list_fragment_layout, container, false);

        Console.log("STarting fragment with list fragment");

        //Setup view
        list = (RecyclerView) v.findViewById(R.id.list);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);


        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            //TODO save span
//            currentLayoutManagerType = (DataManager.LayoutManagerType) savedInstanceState
//                    .getSerializable(KEY_LAYOUT_MANAGER);
        }

        list.setLayoutManager(staggeredGridLayoutManager);
        adapter = new CharacterListAdapter(getActivity(), itemClickListener);
        list.setAdapter(adapter);

        Console.log("Created fragment, set up list and adapter ");

        return v;
    }

    private View.OnClickListener itemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save currently selected layout manager.
//        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, currentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onGridSelected() {
        staggeredGridLayoutManager.setSpanCount(2);
    }

    @Override
    public void onListSelected() {
        staggeredGridLayoutManager.setSpanCount(1);
    }

}
