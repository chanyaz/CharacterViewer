package com.sumayyah.characterviewer;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.InstrumentationTestCase;

import com.sumayyah.characterviewer.main.Adapters.CharacterListAdapter;
import com.sumayyah.characterviewer.main.ListFragment;
import com.sumayyah.characterviewer.main.Managers.DataManager;
import com.sumayyah.characterviewer.main.Model.Character;

import org.junit.Test;

import java.util.List;

/**
 * Created by sumayyah on 8/24/2016.
 */
public class RecyclerviewTest extends ActivityInstrumentationTestCase2 {

    Context mContext;
    CharacterListAdapter mAdapter;
    List<Character> characters;

    public RecyclerviewTest(Class activityClass) {
        super(activityClass);
    }

    public void setUp() throws Exception {
        super.setUp();

        mContext = getInstrumentation().getTargetContext();
        characters = DataManager.getInstance().getList();
        mAdapter = new CharacterListAdapter(fakeListItemClickListener, mContext);
    }

    @Test
    public void testAdapterShowsCorrectNumberOfItems() {
        int expected = characters.size();
        int actual = mAdapter.getItemCount();

        assertEquals(expected, actual);
    }

    @Test
    public void testClickListItemShowsCorrectDetails() {
        //TODO
    }

    private ListFragment.ListItemClickListener fakeListItemClickListener = new ListFragment.ListItemClickListener() {
        @Override
        public void onListItemSelected(int position) {}
    };
}
