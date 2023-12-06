package com.example.mobdev_nhom7;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static java.lang.Thread.sleep;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.mobdev_nhom7.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;

public class BookQuangTest {
    @Rule()
    public ActivityScenarioRule<MainActivity> activityActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void book() throws InterruptedException {
        sleep(3000);
        onView(withId(R.id.recyclerView))
                .perform(scrollTo(), click());
        sleep(2000);
        onView(withId(R.id.priceLayout))
                .perform(scrollTo());
        sleep(2000);
        onView(withId(R.id.priceLayout))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        sleep(2000);
        onView(withId(R.id.plusButton))
                .perform(click());
        sleep(2000);
        onView(withId(R.id.confirm_button))
                .perform(click());
        sleep(2000);
        onView(withId(R.id.bookButton))
                .perform(scrollTo());
        sleep(2000);
        onView(withId(R.id.bookButton))
                .perform(click());
        sleep(2000);
    }
}
