package com.test.climber;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.test.climber.activity.DashboardActivity;
import com.test.climber.activity.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class DashboardActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(
            LoginActivity.class);
    public ActivityTestRule<DashboardActivity> mDashboardRule = new ActivityTestRule<>(DashboardActivity.class);

    @Before
    public void setUp() {
        onView(withId(R.id.userName)).perform(replaceText("test@test.com"), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(replaceText("123456"), closeSoftKeyboard());
        onView(withId(R.id.login)).perform(click());
    }

    @Test
    public void circleProgressTest() {
        onView(withId(R.id.circleProgressBar)).check(matches(isDisplayed()));
        onView(withId(R.id.steps)).check(matches(isDisplayed()));
    }

    @Test
    public void profileTest(){
        onView(withId(R.id.userName)).check(matches(isDisplayed()));
        onView(withId(R.id.imageProfile)).check(matches(isDisplayed()));
    }
}
