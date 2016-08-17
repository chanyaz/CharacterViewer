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
public class ListFragment extends Fragment {


    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int LIST_VIEW = 1;
    private static final int GRID_VIEW = 2;

    private RecyclerView list;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private CharacterListAdapter adapter;
    private View v;
    private ListItemClickListener listItemClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.list_fragment_layout, container, false);
        listItemClickListener = (ListItemClickListener) getActivity();

        //Setup view
        list = (RecyclerView) v.findViewById(R.id.list);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(LIST_VIEW, StaggeredGridLayoutManager.VERTICAL);

        list.setLayoutManager(staggeredGridLayoutManager);
        adapter = new CharacterListAdapter(listItemClickListener);
        list.setAdapter(adapter);

        Console.log("Created fragment, set up list and adapter ");

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save currently selected layout manager.
//        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, currentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    public interface ListItemClickListener {
        void onListItemSelected(int position);
    }

    public void gridSelected() {
        staggeredGridLayoutManager.setSpanCount(GRID_VIEW);
    }

    public void listSelected() {
        staggeredGridLayoutManager.setSpanCount(LIST_VIEW);
    }

    public void update() {
        Console.log("List - updating adapter");
        adapter.notifyDataSetChanged();
    }
}
