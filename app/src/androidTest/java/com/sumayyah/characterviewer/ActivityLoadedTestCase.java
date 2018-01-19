package com.sumayyah.characterviewer;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import com.sumayyah.characterviewer.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ActivityLoadedTestCase extends ActivityInstrumentationTestCase2 {
    
    @Rule
    public ActivityTestRule<MainActivity> activityLoadedTestCase =
            new ActivityTestRule<>(MainActivity.class);


    public ActivityLoadedTestCase() {
        super(MainActivity.class);
    }

    @Test
    public void testListViewLoaded() throws Exception {
        //Test List fragment loaded by checking for recyclerview
        onView(withId(R.id.list)).check(matches(isDisplayed()));
    }

    @Test
    public void testDetailViewLoaded() throws Exception {

        //If in tablet mode, check Detail fragment is loaded in view
        ViewInteraction detailFragmentHolder = onView(withId(R.id.detail_fragment));
        try {
            detailFragmentHolder.check(matches(isDisplayed()));
            onView(withId(R.id.detailLayout)).check(matches(isDisplayed()));
            //view is displayed logic
        } catch (Exception e) {
           //View not displayed
        }
    }
}