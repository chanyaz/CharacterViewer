package com.sumayyah.characterviewer.Views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.sumayyah.characterviewer.R;

import Model.Character;
import Managers.DataManager;

/**
 * Created by sumayyah on 8/10/16.
 */
public class DetailFragment extends Fragment {

    private static final int DEFAULT_DETAIL_VIEW = 0;
    private TextView characterName;
    private TextView characterDetails;
    private ImageView profilePic;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.detail_fragment_layout, container, false);

        characterName = (TextView) v.findViewById(R.id.characterName);
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

    private void refreshUI(int position) {

        Console.log("setting detail UI with position "+position);

        Character c = new DataManager().getList().get(position); //TODO singleton
//        Console.log(c.printDetails());

        characterName.setText(c.getName());
        characterDetails.setText(c.getDescription());
        Picasso.with(getActivity()).load(c.getImageURL()).placeholder(R.drawable.ic_view_grid).into(profilePic);
    }
}
