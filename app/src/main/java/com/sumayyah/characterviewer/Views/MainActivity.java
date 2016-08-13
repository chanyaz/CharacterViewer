package com.sumayyah.characterviewer.Views;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.sumayyah.characterviewer.R;


public class MainActivity extends Activity {

    private Toolbar toolbar;
    private Menu menu;
    private boolean isList;
    private SpanCountInterface spanCountInterface;

    private Bundle bundle;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpActionBar();

        fragmentManager = getFragmentManager();
        currentFragment = new ListFragment();
        spanCountInterface = (SpanCountInterface) currentFragment;

//        if(savedInstanceState != null) {
            fragmentManager.beginTransaction().replace(R.id.list_fragment_holder, currentFragment).commit();
//        }


//        Intent intent = new Intent(this, DetailActivity.class);
//        startActivity(intent);

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
            spanCountInterface.onGridSelected();
            item.setIcon(R.drawable.ic_view_list);
            item.setTitle("Show as list");
            isList = false;
        } else {
            spanCountInterface.onListSelected();
            item.setIcon(R.drawable.ic_view_grid);
            item.setTitle("Show as grid");
            isList = true;
        }
    }

    public interface SpanCountInterface {
        void onGridSelected();
        void onListSelected();
    }

    @Override
    protected void onPause() {
       super.onPause();
        Console.log("on pause in main");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Console.log("On resume in main");
    }
}
