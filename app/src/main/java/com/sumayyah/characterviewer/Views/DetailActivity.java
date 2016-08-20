package com.sumayyah.characterviewer.Views;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.sumayyah.characterviewer.R;

import Adapters.CharacterListAdapter;
import Managers.DataManager;

/**
 * Created by sumayyah on 8/10/16.
 */
public class DetailActivity extends Activity {

    private Toolbar toolbar;
    private DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_layout);

        int position = getIntent().getIntExtra("position",0);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpActionBar(position);

        if(savedInstanceState == null) {

            detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            detailFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.detail_fragment_holder, detailFragment, "Detail").addToBackStack(null).commit();
        }
    }

    private void setUpActionBar(int position) {

        if (toolbar != null) {
            setActionBar(toolbar);
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setDisplayShowTitleEnabled(true);
            getActionBar().setElevation(7);
            getActionBar().setTitle(DataManager.getInstance().getList().get(position).getName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
