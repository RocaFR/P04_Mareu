package bryan.roca.mareu;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import bryan.roca.mareu.controllers.activities.MainActivity;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LogicalInstrumentedTest {

    private String name = "Instrumental Test";
    private String email = "bryan.ferreras@gmail.com";
    //TODO Use API to add Meetings to the list for "canWeSeeMeetingList", "canWeFilterMeetingByMeetingRoom" and "canWeRemoveMeeting"

    /**
     * Rule for launching activity
     */
    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);


    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("bryan.roca.mareu", appContext.getPackageName());
    }

    /**
     * Ensure the message proposing to add a Meeting is displayed if there is no Meetings
     */
    @Test
    public void isTextViewVisibleIfNoMeeting() {
        onView(withId(R.id.activity_main_textView_noMeeting))
                .check(matches(isDisplayed()));
    }

    /**
     * Ensure we can add a Meeting correctly
     */
    @Test
    public void canWeAddMeeting() {
        onView(withId(R.id.floatingButton_addMeeting))
                .perform(click());
        onView(withId(R.id.editText_addMeeting_activity_meetingName))
                .perform(typeText(name));
        onView(withId(R.id.imageButton_addMeeting_activity_addParticipant))
                .perform(scrollTo(), click());
        onView(withId(R.id.fragment_add_participant_editText_mail_address))
                .perform(typeText(email));
        onView(withText(R.string.alert_dialog_positive_button))
                .perform(click());
        onView(withId(R.id.imageButton_addMeeting_activity_addMeeting))
                .perform(scrollTo(), click());
    }

    /**
     * Ensure the RecyclerView display the Meeting list
     */
    @Test
    public void canWeSeeMeetingList() {
    }

    @Test
    public void canWeFilterMeetingByDate() {
        onView(withId(R.id.menuMain_item_filter))
                .perform(click());
        // Date begin
        onView(withId(R.id.fragment_filter_textView_dateBegin))
                .perform(click());
        onView(withText("OK"))
                .perform(click());
        // Date end
        onView(withId(R.id.fragment_filter_textView_dateEnd))
                .perform(click());
        onView(withText("OK"))
                .perform(click());
        // Filter
        onView(withText(MainActivity.ALERTDIALOG_FILTER_POSITIVE_TEXT))
                .perform(click());
    }

    @Test
    public void canWeFilterMeetingByMeetingRoom() {

    }

    @Test
    public void canWeRemoveMeeting() {
        
    }
}