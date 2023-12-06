package com.example.mobdev_nhom7;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static java.lang.Thread.sleep;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.example.mobdev_nhom7.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class SearchCityTest {
    @Rule()
    public ActivityScenarioRule<MainActivity> activityActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void searchCity() throws InterruptedException {
        sleep(2000);
        onView(withId(R.id.searchbar))
                .perform(click());
        sleep(2000);
        onView(withId(R.id.editPreferredDest1))
                .perform(click());
//        onView(withId(R.id.recyclerView))
//                .perform();

    }
}
