package com.sumayyah.characterviewer.Views;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.sumayyah.characterviewer.R;

import Managers.NetworkManager;


public class MainActivity extends Activity implements ListFragment.ListItemClickListener, NetworkManager.NetworkOpsCompleteListener {

    private Toolbar toolbar;
    private Menu menu;
    private boolean isList;
    private boolean isTablet;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpActionBar();
        isTablet = false;
        isList = true;

        new NetworkManager(this, getString(R.string.base_api_url)).executeAPICall();

        fragmentManager = getFragmentManager();
        createRelevantViews();

      //TODO check rotations
    }

    private void createRelevantViews() {

        //Initialize list fragment for both views
        ListFragment listFragment = (ListFragment) fragmentManager.findFragmentByTag(getString(R.string.list_fragment_tag));
        if(listFragment == null) {

            listFragment = new ListFragment();
            fragmentManager.beginTransaction().replace(R.id.list_fragment_holder, listFragment, getString(R.string.list_fragment_tag)).commit();
        }

        //If is tablet, activate detail panel
        if(findViewById(R.id.detail_fragment_holder) != null) {
            isTablet = true;

            DetailFragment detailFragment = (DetailFragment) fragmentManager.findFragmentByTag(getString(R.string.detail_fragment_tag));
            if(detailFragment == null) {
                detailFragment = new DetailFragment();
                fragmentManager.beginTransaction().replace(R.id.detail_fragment_holder, detailFragment, getString(R.string.detail_fragment_tag)).commit();
            }
        }
    }

    private void setUpActionBar() {

        if (toolbar != null) {
            setActionBar(toolbar);
            getActionBar().setDisplayHomeAsUpEnabled(false);
            getActionBar().setDisplayShowTitleEnabled(true);
            getActionBar().setElevation(7);
            toolbar.setTitleTextColor(getResources().getColor(R.color.lightGrey));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        if(!isTablet) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_main, menu);
            this.menu = menu;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_toggle) {
            toggle();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void toggle() {
        MenuItem item = menu.findItem(R.id.action_toggle);
        ListFragment listFragment = (ListFragment) getFragmentManager().findFragmentById(R.id.list_fragment_holder);

        if (isList) {
            listFragment.gridSelected();
            item.setIcon(R.drawable.ic_view_list);
            item.setTitle(getString(R.string.toggle_show_list));
            isList = false;
        } else {
            listFragment.listSelected();
            item.setIcon(R.drawable.ic_view_grid);
            item.setTitle(getString(R.string.toggle_show_grid));
            isList = true;
        }
    }

    @Override
    public void onListItemSelected(int position) {

        if(!isTablet) {

            //Detail fragment is not in the phone layout, so start separate Activity
            //Include index of selected Character
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);

        } else {
            //Detail fragment exists in tablet layout
            DetailFragment detailFragment = new DetailFragment();
            getFragmentManager().beginTransaction().replace(R.id.detail_fragment_holder, detailFragment).commit();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            detailFragment.setArguments(bundle);
        }
    }

    @Override
    public void onNetworkOpsComplete() {
        Console.log("Main- network ops complete");

        //Refresh UI to reflect that all data is available now
        ListFragment listFragment = (ListFragment) getFragmentManager().findFragmentById(R.id.list_fragment_holder);
        listFragment.update();

        if(isTablet) {
            DetailFragment detailFragment = (DetailFragment) fragmentManager.findFragmentByTag(getString(R.string.detail_fragment_tag));
            detailFragment.refreshUI(0);
        }
    }
}
