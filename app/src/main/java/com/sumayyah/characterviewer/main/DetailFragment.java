package com.sumayyah.characterviewer.main;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.sumayyah.characterviewer.R;

import com.sumayyah.characterviewer.databinding.DetailFragmentLayoutBinding;
import com.sumayyah.characterviewer.main.Model.Character;
import com.sumayyah.characterviewer.main.Managers.DataManager;

/**
 * Created by sumayyah on 8/10/16.
 */
public class DetailFragment extends Fragment {

    private DetailFragmentLayoutBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment_layout, container, false);

        return binding.getRoot();
    }

    public void showEmptyView() {
        binding.emptyDetailView.setVisibility(View.VISIBLE);
        binding.detailView.setVisibility(View.GONE);
    }

    public void showCharacterData(Character c) {
        binding.characterDetails.setText(c.getDescription());

        if(c.getImageURL().length() > 0) {
            Picasso.with(getActivity()).load(c.getImageURL()).placeholder(R.drawable.placeholder_profile_pic).into(binding.profilePic);
        } else {
            Picasso.with(getActivity()).load(R.drawable.placeholder_profile_pic).placeholder(R.drawable.ic_view_grid).into(binding.profilePic);
        }

        binding.emptyDetailView.setVisibility(View.GONE);
        binding.detailView.setVisibility(View.VISIBLE);
    }
}
