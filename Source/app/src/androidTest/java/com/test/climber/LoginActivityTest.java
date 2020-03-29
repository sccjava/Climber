package com.test.climber;

import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.test.climber.activity.DashboardActivity;
import com.test.climber.activity.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(
            LoginActivity.class);
    public IntentsTestRule<DashboardActivity> mIntentsRule = new IntentsTestRule<>(DashboardActivity.class);

    @Before
    public void setUp() {
        Intents.init();
    }

    @Test
    public void loginWithoutUsernamePasswordTest() {
        onView(withId(R.id.userName)).perform(clearText());
        onView(withId(R.id.password)).perform(clearText());
        onView(withId(R.id.login)).perform(click());
        intended(hasComponent(DashboardActivity.class.getName()));
    }

    @Test
    public void promptTest() {
        onView(withId(R.id.textViewUsername)).check(matches(not(isDisplayed())));
        onView(withId(R.id.textViewPassword)).check(matches(not(isDisplayed())));
        onView(withId(R.id.userName)).perform(replaceText("test.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(replaceText("123456"), closeSoftKeyboard());
        onView(withId(R.id.login)).perform(click());
        onView(withId(R.id.textViewUsername)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewPassword)).check(matches(isDisplayed()));
    }

    @Test
    public void invalidEmailTest() {
        onView(withId(R.id.userName)).perform(replaceText("test.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(replaceText("123456"), closeSoftKeyboard());
        onView(withId(R.id.login)).perform(click());
        onView(withText(R.string.invalid_email))
                .inRoot(withDecorView(not(mActivityRule.getActivity().getWindow().getDecorView())))
                .check(matches(isDisplayed()));
    }

    @Test
    public void loginTest() {
        onView(withId(R.id.userName)).perform(replaceText("test@test.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(replaceText("123456"), closeSoftKeyboard());
        onView(withId(R.id.login)).perform(click());
        intended(hasComponent(DashboardActivity.class.getName()));
    }

    @After
    public void tearDown() {
        Intents.release();
    }
}
