package com.sumayyah.characterviewer.Views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;
import com.sumayyah.characterviewer.R;

import Model.Character;
import Managers.DataManager;

/**
 * Created by sumayyah on 8/10/16.
 */
public class DetailFragment extends Fragment {

    private static final int DEFAULT_DETAIL_VIEW = 0;
    private TextView characterDetails;
    private ImageView profilePic;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.detail_fragment_layout, container, false);

        characterDetails = (TextView) v.findViewById(R.id.characterDetails);
        profilePic = (ImageView) v.findViewById(R.id.profilePic);

        //Get position from calling activity, if possible
        if(getArguments() !=null) {
            refreshUI(getArguments().getInt("position"));
        } else {
            refreshUI(DEFAULT_DETAIL_VIEW);
        }

        return v;
    }

    public void refreshUI(int position) {

        if(DataManager.getInstance().getList().size() > 0) {

            Character c = DataManager.getInstance().getList().get(position);
            characterDetails.setText(c.getDescription());

            if(c.getImageURL().length() > 0) {
                Picasso.with(getActivity()).load(c.getImageURL()).placeholder(R.drawable.placeholder_profile_pic).into(profilePic);
            } else {
                Picasso.with(getActivity()).load(R.drawable.placeholder_profile_pic).placeholder(R.drawable.ic_view_grid).into(profilePic);
            }
        }
    }
}
