package com.corposense.testingandroid;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class BodyMassIndexInstrumentedTest {


    private Context appContext = InstrumentationRegistry.getTargetContext();
    public static final Double WEIGHT_VALUE = 55.0;
    public static final Double HEIGHT_VALUE = 1.5;

    @Before
    public void setup() throws Exception{
        this.appContext.startActivity(new Intent(this.appContext, MainActivity.class));
    }

    @Test
    public void testPackageName() {
        // Context of the app under test.
        assertEquals("com.corposense.testingandroid", appContext.getPackageName());
    }

    private double calculateBMI(){
        return new BodyMassIndex(WEIGHT_VALUE, HEIGHT_VALUE).calculate(); //=24.444444444444443
    }

    @Test
    public void testChangedText(){
        onView(withId(R.id.edWeight)).perform(typeText(WEIGHT_VALUE.toString()), closeSoftKeyboard());
        onView(withId(R.id.edHeight)).perform(typeText(HEIGHT_VALUE.toString()), closeSoftKeyboard());
        onView(withId(R.id.btnCalc)).perform(click());
        onView(withId(R.id.tvResult)).check(matches(withText( "Your BMI = "+calculateBMI()+" kg/m2")));
    }

    @Test
    public void testEmptyValues(){
        Instrumentation mInstrumentation = getInstrumentation();
        Instrumentation.ActivityMonitor monitor = mInstrumentation.addMonitor(MainActivity.class.getName(), null, false);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName(mInstrumentation.getTargetContext(), MainActivity.class.getName());
        mInstrumentation.startActivitySync(intent);

        Activity currentActivity = getInstrumentation().waitForMonitor(monitor);
        assertNotNull(currentActivity);

        View v = currentActivity.findViewById(R.id.btnCalc);
        assertNotNull(v);

        onView(withId(R.id.btnCalc))            // withId(R.id.my_view) is a ViewMatcher
                .perform(click())               // click() is a ViewAction
                .check(matches(isDisplayed())); // matches(isDisplayed()) is a ViewAssertion

        mInstrumentation.sendStringSync("Some text to send");

        onView(withId(R.id.tvResult)).check(matches(withText("Please enter correct values...")));
    }


    @After
    public void tearDown() throws Exception {
    }
}
