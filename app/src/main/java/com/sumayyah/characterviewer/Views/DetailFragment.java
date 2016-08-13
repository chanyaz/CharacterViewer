package com.sumayyah.characterviewer.Views;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sumayyah.characterviewer.R;

/**
 * Created by sumayyah on 8/10/16.
 */
public class DetailFragment extends Fragment {

    int position;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.detail_fragment_layout, container, false);
        position = getArguments().getInt("position");

        Console.log("Starting fragment with detail fragment, position "+position);

        return v;
    }

}
