package com.example.mobdev_nhom7;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static java.lang.Thread.sleep;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.mobdev_nhom7.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;

public class FavoriteHotelQuangTest {
    @Rule()
    public ActivityScenarioRule<MainActivity> activityActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void favoriteHotel() throws InterruptedException {
        sleep(2000);
        onView(withId(R.id.favourites))
                .perform(click());
        sleep(2000);
        onView(withId(R.id.recycleView))
                .perform(click());
        sleep(2000);
    }
}
