package com.sumayyah.characterviewer.main;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toolbar;

import com.sumayyah.characterviewer.R;

import com.sumayyah.characterviewer.main.Data.CharacterRepository;
import com.sumayyah.characterviewer.main.Managers.DataManager;
import com.sumayyah.characterviewer.main.Managers.NetworkManager;
import com.sumayyah.characterviewer.main.Model.Character;

import java.util.ArrayList;


public class MainActivity extends Activity implements ListFragment.ListItemClickListener, CharacterRepository.OnCompleteListener {

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
        fragmentManager = getFragmentManager();

        fetchData();
        initViews();
    }

    private void fetchData() {
        CharacterRepository.INSTANCE.setListener(this);
        CharacterRepository.INSTANCE.init();
    }

    private void initViews() {

        //Initialize list fragment for both views
        ListFragment listFragment = ListFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.holder, listFragment, "List fragment").commit();

        if (findViewById(R.id.detail_fragment) != null) {
            isTablet = true;
            DetailFragment detailFragment = DetailFragment.newInstance();
            fragmentManager.beginTransaction().replace(R.id.detail_fragment, detailFragment, "Detail fragment").addToBackStack(null).commit();
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
        ListFragment listFragment = (ListFragment) getFragmentManager().findFragmentById(R.id.holder);

        if (isList) {
            listFragment.gridSelected();
            item.setIcon(R.drawable.ic_view_list_white_24dp);
            item.setTitle(getString(R.string.toggle_show_list));
            isList = false;
        } else {
            listFragment.listSelected();
            item.setIcon(R.drawable.ic_view_grid_white_24dp);
            item.setTitle(getString(R.string.toggle_show_grid));
            isList = true;
        }
    }

    @Override
    public void onListItemSelected(int position) {

        if(!isTablet) {
            DetailFragment detailFragment = DetailFragment.newInstance(position);
            fragmentManager.beginTransaction().replace(R.id.holder, detailFragment, "Detail fragment").addToBackStack("Detail fragment").commit();

        } else {
            //if Detail fragment exists in tablet layout, update
            fragmentManager.executePendingTransactions();
            DetailFragment detailFragment = (DetailFragment) fragmentManager.findFragmentById(R.id.detail_fragment);
            detailFragment.refreshUI(position);
        }
    }

    @Override
    public void onDataComplete() {
        ListFragment listFragment = (ListFragment) fragmentManager.findFragmentById(R.id.holder);
        listFragment.update();

        if (isTablet) {
            //if Detail fragment exists in tablet layout, update
            DetailFragment detailFragment = (DetailFragment) fragmentManager.findFragmentById(R.id.detail_fragment);
            detailFragment.refreshUI(0);
        }
    }
}
