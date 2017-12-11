package com.sumayyah.characterviewer.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.sumayyah.characterviewer.R;
import com.sumayyah.characterviewer.main.Managers.DataManager;
import com.sumayyah.characterviewer.main.Model.Character;

import org.jetbrains.annotations.NotNull;

/**
 * Created by sumayyah on 8/10/16.
 */
public class DetailActivity extends Activity implements DetailActivityView {

    private Toolbar toolbar;
    private DetailFragment detailFragment;
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity_layout);

        int position = getIntent().getIntExtra("position",0);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpActionBar(position);

        if(savedInstanceState == null) { //TODO rotation reload check
            detailFragment = new DetailFragment();
            getFragmentManager().beginTransaction().replace(R.id.detail_fragment_holder, detailFragment, "Detail").addToBackStack(null).commit();
        }
        detailFragment.showEmptyView(); //TODO move into presenter?
    }

    private void setUpActionBar(int position) {

        if (toolbar != null) {
            setActionBar(toolbar);
            getActionBar().setDisplayHomeAsUpEnabled(true);
            getActionBar().setDisplayShowTitleEnabled(true);
            getActionBar().setElevation(7);
            getActionBar().setTitle(DataManager.getInstance().getList().get(position).getName());
            toolbar.setTitleTextColor(getResources().getColor(R.color.White));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
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
        finishAfterTransition();
    }

    @Override
    public void loadCharacterData(@NotNull Character c) {
        detailFragment.showCharacterData(c);
    }
}
