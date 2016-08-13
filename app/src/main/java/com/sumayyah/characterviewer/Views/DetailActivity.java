package com.sumayyah.characterviewer.Views;

import android.app.Activity;
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
    private RecyclerView list;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private GridLayoutManager gridLayoutManager;
    private CharacterListAdapter adapter;

    private Menu menu;
    private boolean isList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_layout);

        Console.log("Detail activity");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpActionBar();

        //Setup view
        list = (RecyclerView) findViewById(R.id.list);
        list.setHasFixedSize(true);
        staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        gridLayoutManager = new GridLayoutManager(this, 1);
        list.setLayoutManager(gridLayoutManager);

        adapter = new CharacterListAdapter(this);
        list.setAdapter(adapter);

        isList = true;
    }

    private void setUpActionBar() {

        if (toolbar != null) {
            setActionBar(toolbar);
            getActionBar().setDisplayHomeAsUpEnabled(false);
            getActionBar().setDisplayShowTitleEnabled(true);
            getActionBar().setElevation(7);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        this.menu = menu;
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
        if (isList) {
            gridLayoutManager.setSpanCount(2);
            item.setIcon(R.drawable.ic_view_list);
            item.setTitle("Show as list");
            isList = false;
            adapter.notifyDataSetChanged();
        } else {
            gridLayoutManager.setSpanCount(1);
            item.setIcon(R.drawable.ic_view_grid);
            item.setTitle("Show as grid");
            isList = true;
            adapter.notifyDataSetChanged();
        }
    }
}
