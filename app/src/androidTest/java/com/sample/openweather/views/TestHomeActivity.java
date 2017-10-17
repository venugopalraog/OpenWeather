package com.sample.openweather.views;

import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.sample.openweather.R;
import com.sample.openweather.views.activities.HomeActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by venugopalraog on 10/16/17.
 */
@RunWith(AndroidJUnit4.class)
public class TestHomeActivity {

    @Rule
    public ActivityTestRule<HomeActivity> activityActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    @Before
    public void init() {
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void TestUpdateButtonVisibility() {
        onView(withId(R.id.update_city)).check(matches((isDisplayed())));
    }

    @Test
    public void TestEnterCityDialogVisibility() {
        onView(withId(R.id.update_city)).perform(click());
        onView(withId(R.id.update_city_edit_text)).check(matches(isDisplayed()));
    }

    @Test
    public void TestEnterNewCityName() {
        onView(withId(R.id.update_city)).perform(click());
        onView(withId(R.id.update_city_edit_text)).check(matches(isDisplayed()));
        onView(withId(R.id.update_city_edit_text)).perform(clearText(), typeText("Chennai"));
        onView(withId(android.R.id.button1)).perform(click());
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(2));
        onView(withId(R.id.weather_city_name)).check(matches(withText("Chennai")));
    }
}
