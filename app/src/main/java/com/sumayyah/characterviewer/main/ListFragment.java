package com.sumayyah.characterviewer.main;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sumayyah.characterviewer.R;

import com.sumayyah.characterviewer.main.Adapters.CharacterListAdapter;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by sumayyah on 8/10/16.
 */
public class ListFragment extends Fragment {

    private static final int GRID_VIEW = 2;

    private RecyclerView list;
    private CharacterListAdapter adapter;
    private View v;
    private ListItemClickListener listItemClickListener;

    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.list_fragment_layout, container, false);
        listItemClickListener = (ListItemClickListener) getActivity();

        //Setup view
        list = (RecyclerView) v.findViewById(R.id.list);

        linearLayoutManager = new LinearLayoutManager(getActivity());
        gridLayoutManager = new GridLayoutManager(getActivity(), GRID_VIEW);
        list.setLayoutManager(linearLayoutManager);

        adapter = new CharacterListAdapter(listItemClickListener, getActivity());
        list.setAdapter(adapter);

        list.setItemAnimator(new SlideInUpAnimator());

        return v;
    }


    public interface ListItemClickListener {
        void onListItemSelected(int position);
    }

    public void gridSelected() {
        adapter.isList = false;
        list.setLayoutManager(gridLayoutManager);
    }

    public void listSelected() {
        adapter.isList = true;
        list.setLayoutManager(linearLayoutManager);
    }

    public void update() {
        adapter.notifyDataSetChanged();
    }
}
