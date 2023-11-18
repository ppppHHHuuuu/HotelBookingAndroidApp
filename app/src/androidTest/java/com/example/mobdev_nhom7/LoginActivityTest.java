package com.example.mobdev_nhom7;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.filters.LargeTest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static java.lang.Thread.sleep;

import com.example.mobdev_nhom7.Login;
import com.example.mobdev_nhom7.R;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTest {

    @Rule
    public ActivityScenarioRule<Login> activityScenarioRule = new ActivityScenarioRule<>(Login.class);

    @Test
    public void loginWithAccount() throws InterruptedException {
        // Input email in email_edit_text in Login activity
        onView(withId(R.id.email_edit_text)).perform(ViewActions.typeText("vunhatminh317@gmail.com"));
        Espresso.closeSoftKeyboard();

        // Click next button in Login activity
        onView(withId(R.id.login_button)).perform(ViewActions.click());
        sleep(4000);
        // Input password in password_edit_text in Password activity
        onView(withId(R.id.password_edit_text)).perform(ViewActions.typeText("Trivagones"));
        Espresso.closeSoftKeyboard();

        // Click login_button in Password activity
        onView(withId(R.id.password_login_button)).perform(ViewActions.click());
    }
}
