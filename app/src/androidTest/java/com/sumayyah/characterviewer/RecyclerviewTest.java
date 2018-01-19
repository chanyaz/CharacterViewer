package com.sumayyah.characterviewer;

import android.content.Context;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import com.sumayyah.characterviewer.main.Adapters.CharacterListAdapter;
import com.sumayyah.characterviewer.main.Data.CharacterRepository;
import com.sumayyah.characterviewer.main.ListFragment;
import com.sumayyah.characterviewer.main.MainActivity;
import com.sumayyah.characterviewer.main.Managers.DataManager;
import com.sumayyah.characterviewer.main.Model.Character;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Created by sumayyah on 8/24/2016.
 */
@RunWith(AndroidJUnit4.class)
public class RecyclerviewTest extends AndroidTestCase {

    Context mContext;
    CharacterListAdapter mAdapter;
    List<Character> characters;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        super.setUp();

        mContext = mActivityRule.getActivity().getApplicationContext();
        characters = CharacterRepository.INSTANCE.getAllCharacters();
        mAdapter = new CharacterListAdapter(fakeListItemClickListener, mContext);
    }

    @Test
    public void testAdapterShowsCorrectNumberOfItems() {

        int expected = characters.size();
        int actual = mAdapter.getItemCount();

        assertEquals(expected, actual);
    }

    private ListFragment.ListItemClickListener fakeListItemClickListener = new ListFragment.ListItemClickListener() {
        @Override
        public void onListItemSelected(int position) {}
    };
}
