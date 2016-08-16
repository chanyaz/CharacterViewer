package com.sumayyah.characterviewer.Views;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.sumayyah.characterviewer.R;

import Adapters.CharacterListAdapter;

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

        Console.log("Detail activity");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpActionBar();

        if(savedInstanceState == null) {

            detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", getIntent().getIntExtra("position",0));
            detailFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.detail_fragment_holder, detailFragment, "Detail").commit();
        }
    }

    private void setUpActionBar() {

        if (toolbar != null) {
            setActionBar(toolbar);
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setDisplayShowTitleEnabled(true);
            getActionBar().setElevation(7);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_toggle) {

            DetailFragment detailFragment = (DetailFragment) getFragmentManager().findFragmentByTag("Detail");
            Console.log("Detail fragment "+detailFragment);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
