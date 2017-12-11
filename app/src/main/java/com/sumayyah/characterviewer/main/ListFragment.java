package com.sumayyah.characterviewer.main;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sumayyah.characterviewer.R;

import com.sumayyah.characterviewer.databinding.ListFragmentLayoutBinding;
import com.sumayyah.characterviewer.main.Adapters.CharacterListAdapter;
import com.sumayyah.characterviewer.main.Model.Character;

import java.util.ArrayList;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

/**
 * Created by sumayyah on 8/10/16.
 */
public class ListFragment extends Fragment {

    private static final int GRID_VIEW = 2;

    private CharacterListAdapter adapter;
    private ListItemClickListener listItemClickListener;

    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private ListFragmentLayoutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.list_fragment_layout, container, false);
        listItemClickListener = (ListItemClickListener) getActivity();

        //Setup view
        linearLayoutManager = new LinearLayoutManager(getActivity());
        gridLayoutManager = new GridLayoutManager(getActivity(), GRID_VIEW);
        binding.list.setLayoutManager(linearLayoutManager);

        Console.log("Sumi", "List fragment oncreateview");

        return binding.getRoot();
    }


    public interface ListItemClickListener {
        void onListItemSelected(int position);
    }

    public void gridSelected() {
        adapter.isList = false;
        binding.list.setLayoutManager(gridLayoutManager);
    }

    public void listSelected() {
        adapter.isList = true;
        binding.list.setLayoutManager(linearLayoutManager);
    }

    public void showEmptyView() {
        Console.log("Sumi", "List fragment shownig empty view");

        binding.list.setVisibility(View.GONE);
        binding.emptyListView.setVisibility(View.VISIBLE);
    }

    public void update(ArrayList<Character> characters) {
        Console.log("Sumi", "List fragment shownig characters "+characters.size());

        if (adapter == null) {
            adapter = new CharacterListAdapter(listItemClickListener, getActivity(), characters);
            binding.list.setAdapter(adapter);
            binding.list.setItemAnimator(new SlideInUpAnimator());
        }

        adapter.notifyDataSetChanged();
        binding.emptyListView.setVisibility(View.GONE);
        binding.list.setVisibility(View.VISIBLE);
    }
}
