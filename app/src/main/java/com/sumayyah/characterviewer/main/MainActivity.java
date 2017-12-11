package com.sumayyah.characterviewer.main;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.sumayyah.characterviewer.R;

import com.sumayyah.characterviewer.main.Data.CharacterRepository;
import com.sumayyah.characterviewer.main.Managers.DataManager;
import com.sumayyah.characterviewer.main.Model.Character;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class MainActivity extends Activity implements ListFragment.ListItemClickListener, ListView, DetailView {

    private Toolbar toolbar;
    private Menu menu;
    private boolean isList;
    private boolean isTablet;
    private FragmentManager fragmentManager;
    private ArrayList<Character> characters;
    private MainPresenter mainPresenter;
    private CharacterRepository repository; //TODO inject
//    private ListFragment listFragment;
    private DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setUpActionBar();

        isTablet = false;
        isList = true;
        fragmentManager = getFragmentManager();

        if (repository == null) {
            repository = new CharacterRepository();
        }

        if (mainPresenter == null) {
            mainPresenter = new MainPresenter(this, this, repository);
        }
        Console.log("Sumi", "Main activity oncreate");

        createRelevantViews();
    }

    private void fetchData() {
        //Console.log("MainActivity", "Fetching data");
        //characters = new CharacterRepository().getAllCharacters();
        //DataManager.getInstance().setCharacterList(characters); //TODO Notify the views that the data has changed
        //new NetworkManager(networkUtils, this).executeAPICall();
    }

    private void createRelevantViews() {

        //Initialize list fragment for both views
        ListFragment listFragment = (ListFragment) fragmentManager.findFragmentByTag(getString(R.string.list_fragment_tag));
        if(listFragment == null) {
            Console.log("Sumi", "Main activity list fragmetn is null, creating new");

            listFragment = new ListFragment();
            fragmentManager.beginTransaction().replace(R.id.list_fragment_holder, listFragment, getString(R.string.list_fragment_tag)).commit();
        } else {
            Console.log("Sumi", "Main activity list fragmetn is NOT null");

        }

        //If detail fragment exists, set flag to true
        if(findViewById(R.id.detail_fragment) != null) {
            isTablet = true;
            detailFragment = new DetailFragment();
            fragmentManager.beginTransaction().replace(R.id.detail_fragment_holder, detailFragment, getString(R.string.detail_fragment_tag)).commit();
        }

        Console.log("Sumi", "Main activity made fragments");
        mainPresenter.initViews();
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
    public void onResume() {
        super.onResume();
        //mainPresenter.onResume();
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
            //Detail fragment is not in the phone layout, so start separate Activity
            //Include index of selected Character
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);

        } else {
//            //if Detail fragment exists in tablet layout, update
//            DetailFragment detailFragment = (DetailFragment) fragmentManager.findFragmentById(R.id.detail_fragment);
//            detailFragment.showCharacterData(position);

            //TODO call presenter
        }
    }

    @Override
    public void showEmptyDetailView() {

        if (isTablet) {
//            DetailFragment detailFragment = (DetailFragment) fragmentManager.findFragmentById(R.id.detail_fragment);
            detailFragment.showEmptyView();
        }
    }

    @Override
    public void showDetailForCharacter(@NotNull Character character) {

        if(isTablet) {
            DetailFragment detailFragment = (DetailFragment) fragmentManager.findFragmentById(R.id.detail_fragment);
            detailFragment.showCharacterData(character);
        }
    }

    @Override
    public void showEmptyList() {

        ListFragment listFragment = (ListFragment) getFragmentManager().findFragmentById(R.id.list_fragment_holder);
        if (listFragment == null) {
            Console.log("SUmi", "Main activity showing empty list, listfragment is null");
        } else {
            Console.log("SUmi", "Main activity showing empty list, listfragment is NOT null");

        }
        listFragment.showEmptyView();
    }

    @Override
    public void loadListData(@NotNull ArrayList<Character> characters) {
        ListFragment listFragment = (ListFragment) getFragmentManager().findFragmentById(R.id.list_fragment_holder);
        Console.log("SUmi", "Main activity listfragment loading data "+characters.size());

        listFragment.update(characters);
    }
}
