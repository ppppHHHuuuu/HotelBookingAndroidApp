package com.example.mobdev_nhom7;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static java.lang.Thread.sleep;

import com.example.mobdev_nhom7.R;
import com.example.mobdev_nhom7.activity.Login;
import com.example.mobdev_nhom7.activity.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginToMainActTest {
    @Rule
    public ActivityScenarioRule<Login> activityActivityScenarioRule = new ActivityScenarioRule<>(Login.class);
    @Test
    public void LoginToMainTest() {
        onView(withId(R.id.email_edit_text))
                .perform(clearText())
                .perform(click(), typeText("doanphu2112@gmail.com"));
        onView(withId(R.id.login_button))
                .perform(click());
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        onView(withId(R.id.password_edit_text))
                .perform(click(), typeText("12345678910!"));
        onView(withId(R.id.login_button))
                .perform(click());
    }
}
