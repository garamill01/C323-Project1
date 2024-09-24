package com.example.calculator

import org.junit.After
import org.junit.Before
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    // This test verifies that the starting state of the calculatorTextView shows "0"
    @Test
    fun showsZeroOnLaunch() {
        onView(withId(R.id.calculator_text_view))
            .check(matches(withText("0")))
    }

    // This test verifies that the calculatorTextView shows operand1 when first pressing a number
    // button
    @Test
    fun showsOperand1() {
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.add_button)).perform(click())

        onView(withId(R.id.calculator_text_view))
            .check(matches(withText("1")))

    }

    // This test verifies that the calculatorTextView shows operand2 when there is an operand1 and
    // an operation button pressed
    @Test
    fun showsOperand2() {
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.add_button)).perform(click())
        onView(withId(R.id.two_button)).perform(click())

        onView(withId(R.id.calculator_text_view))
            .check(matches(withText("2")))
    }

    // This test verifies that the calculatorTextView shows the result of the two operands given
    // the operation after clicking the equals button.
    @Test
    fun showsResult() {
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.add_button)).perform(click())
        onView(withId(R.id.two_button)).perform(click())
        onView(withId(R.id.equals_button)).perform(click())

        onView(withId(R.id.calculator_text_view))
            .check(matches(withText("3")))
    }

    // This test verifies that the clear button sets the calculatorTextView back to its original
    // state of showing "0"
    @Test
    fun clearTextView() {
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.add_button)).perform(click())
        onView(withId(R.id.two_button)).perform(click())

        onView(withId(R.id.clear_button)).perform(click())

        onView(withId(R.id.calculator_text_view))
            .check(matches(withText("0")))
    }

    // This test verifies that the result of the two operands and operation are calculated and the
    // result is shows on the calculatorTextView when another operation is pressed after all three
    // variables have values
    @Test
    fun showResultForFirstOperands() {
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.add_button)).perform(click())
        onView(withId(R.id.two_button)).perform(click())
        onView(withId(R.id.three_button)).perform(click())
        onView(withId(R.id.add_button)).perform(click())

        onView(withId(R.id.calculator_text_view))
            .check(matches(withText("24")))
    }

    // This test verifies that the values stored and calculatorTextView are restored when the
    // application view is destroyed via rotation.
    @Test
    fun handlesActivityRecreation() {
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.add_button)).perform(click())
        onView(withId(R.id.two_button)).perform(click())
        onView(withId(R.id.three_button)).perform(click())

        scenario.recreate()
        onView(withId(R.id.calculator_text_view))
            .check(matches(withText("23")))
    }

    // This test verifies that the calculator text view will show an error message when there is an
    // attempt to divide by "0"
    @Test
    fun showError() {
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.divide_button)).perform(click())
        onView(withId(R.id.zero_button)).perform(click())
        onView(withId(R.id.equals_button)).perform(click())

        onView(withId(R.id.calculator_text_view))
            .check(matches(withText("Error")))
    }

    // This test verifies that the calculator correctly shows the operand currently viewed is
    // operated on by the percent button and the result is shown right away
    @Test
    fun showPercent() {
        onView(withId(R.id.one_button)).perform(click())
        onView(withId(R.id.zero_button)).perform(click())
        onView(withId(R.id.zero_button)).perform(click())
        onView(withId(R.id.percent_button)).perform(click())

        onView(withId(R.id.calculator_text_view))
            .check(matches(withText("1")))
    }

    // This test verifies that the value of an operand cannot exceed 16 characters
    @Test
    fun stopAtMaximumChars() {
        for (i in 1..16) {
            onView(withId(R.id.one_button)).perform(click())
        }

        onView(withId(R.id.calculator_text_view))
            .check(matches(withText("1111111111111111")))

        onView(withId(R.id.two_button)).perform(click())

        onView(withId(R.id.calculator_text_view))
            .check(matches(withText("1111111111111111")))
    }
}