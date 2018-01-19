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
import com.sumayyah.characterviewer.main.Data.CharacterRepository;
import com.sumayyah.characterviewer.main.Model.Character;

/**
 * Created by sumayyah on 8/10/16.
 */
public class DetailFragment extends Fragment {

    private DetailFragmentLayoutBinding binding;

    public static DetailFragment newInstance(int index) {
        Bundle args = new Bundle();
        args.putInt("index", index);

        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment_layout, container, false);

        showEmptyView();

        if(getArguments() !=null && CharacterRepository.INSTANCE.getAllCharacters().size() > 0) {
            int position = getArguments().getInt("index");
            refreshUI(position);
        }

        return binding.getRoot();
    }

    public void refreshUI(int position) {
        showContent();
        if(CharacterRepository.INSTANCE.getAllCharacters().size() > 0) {

            Character c = CharacterRepository.INSTANCE.getAllCharacters().get(position);
            binding.characterDetails.setText(c.getDescription());

            if(c.getImageURL().length() > 0) {
                Picasso.with(getActivity()).load(c.getImageURL()).placeholder(R.drawable.placeholder_profile_pic).into(binding.profilePic);
            } else {
                Picasso.with(getActivity()).load(R.drawable.placeholder_profile_pic).placeholder(R.drawable.ic_view_grid).into(binding.profilePic);
            }
        }
    }

    private void showEmptyView() {
        if (binding.mainDetailView.getVisibility() == View.VISIBLE) { //TODO is this the most efficient way of doing this?
            binding.mainDetailView.setVisibility(View.GONE);
            binding.emptyView.setVisibility(View.VISIBLE);
        }
    }

    private void showContent() {
        if (binding.emptyView.getVisibility() == View.VISIBLE) {
            binding.emptyView.setVisibility(View.GONE);
            binding.mainDetailView.setVisibility(View.VISIBLE);
        }
    }
}
