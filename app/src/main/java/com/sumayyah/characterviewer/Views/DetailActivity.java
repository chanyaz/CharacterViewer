package com.sumayyah.characterviewer.Views;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toolbar;

import com.sumayyah.characterviewer.R;

/**
 * Created by sumayyah on 8/10/16.
 */
public class DetailActivity extends Activity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_layout);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpActionBar();
    }

    private void setUpActionBar() {

        if (toolbar != null) {
            setActionBar(toolbar);
            getActionBar().setDisplayHomeAsUpEnabled(false);
            getActionBar().setDisplayShowTitleEnabled(true);
            getActionBar().setElevation(7);
        }
    }
}
