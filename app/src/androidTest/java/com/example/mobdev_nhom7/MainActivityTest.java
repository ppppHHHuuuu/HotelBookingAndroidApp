package com.example.mobdev_nhom7;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {
    int LIST_ITEM_IN_TEST = 3;
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void staysFragment() throws InterruptedException {
        onView(withId(R.id.desInput)).perform(ViewActions.typeText("Hanoi"), ViewActions.closeSoftKeyboard());
        sleep(3500);
        onView(withId(R.id.buttonSearch)).perform(ViewActions.click());
        sleep(4000);
        onView(withId(R.id.recyclerView)).perform();
        sleep(2000);
    }

    @Test
    public void mainUI() throws InterruptedException {
        onView(withId(R.id.cities)).perform(ViewActions.click());
        sleep(1000);
        onView(withId(R.id.favourites)).perform(ViewActions.click());
        sleep(1000);
        onView(withId(R.id.trips)).perform(ViewActions.click());
        sleep(1000);
        onView(withText("PAST")).perform(ViewActions.click());
        sleep(1000);
        onView(withText("CANCELLED")).perform(ViewActions.click());
        sleep(1000);
        onView(withId(R.id.settings)).perform(ViewActions.click());
        sleep(1000);
    }


}
