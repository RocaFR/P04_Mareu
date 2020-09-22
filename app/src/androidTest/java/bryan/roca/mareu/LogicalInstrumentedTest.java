package bryan.roca.mareu;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import bryan.roca.mareu.controllers.activities.MainActivity;
import bryan.roca.mareu.controllers.fragments.DatePickerFragment;
import bryan.roca.mareu.models.MeetingRoom;
import bryan.roca.mareu.service.DummyMeetingApiService;
import bryan.roca.mareu.service.MeetingApiService;
import bryan.roca.mareu.utils.RecyclerViewMatcher;
import bryan.roca.mareu.utils.RemoveMeetingAction;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LogicalInstrumentedTest {

    private String firstMeetingName = "Instrumental Test";
    private String secondMeetingName = "Another Meeting";
    private String firstMeetingEmail = "bryan.ferreras@gmail.com";
    private String secondMeetingEmail = "moussion.solene@gmail.com";
    private static final String BEGIN_DATE_FIRST_MEETING = "01/01/2020";
    private static final String END_DATE_FIRST_MEETING = "01/01/2020";
    private static final String BEGIN_DATE_SECOND_MEETING = "02/01/2020";
    private static final String END_DATE_SECOND_MEETING = "02/01/2020";

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
     * Ensure the RecyclerView display the added Meeting list
     */
    @Test
    public void canWeAddAndSeeMeetingList() {
        onView(withId(R.id.floatingButton_addMeeting))
                .perform(click());
        onView(withId(R.id.editText_addMeeting_activity_meetingName))
                .perform(typeText(firstMeetingName));
        onView(withId(R.id.imageButton_addMeeting_activity_addParticipant))
                .perform(scrollTo(), click());
        onView(withId(R.id.fragment_add_participant_editText_mail_address))
                .perform(typeText(firstMeetingEmail));
        onView(withText(R.string.alert_dialog_positive_button))
                .perform(click());
        onView(withId(R.id.imageButton_addMeeting_activity_addMeeting))
                .perform(scrollTo(), click());

        onView(withRecyclerView(R.id.recyclerView).atPosition(0))
                .check(matches(hasDescendant(withText(firstMeetingName))));
    }

    @Test
    public void canWeFilterMeetingByDate() {
        // First Meeting
        onView(withId(R.id.floatingButton_addMeeting))
                .perform(click());
        onView(withId(R.id.editText_addMeeting_activity_meetingName))
                .perform(typeText(firstMeetingName));
        onView(withId(R.id.textView_addMeeting_activity_dateBegin))
                .perform(setTextInTextView(BEGIN_DATE_FIRST_MEETING));
        onView(withId(R.id.textView_addMeeting_activity_dateEnd))
                .perform(scrollTo(), setTextInTextView(END_DATE_FIRST_MEETING));
        onView(withId(R.id.imageButton_addMeeting_activity_addParticipant))
                .perform(scrollTo(), click());
        onView(withId(R.id.fragment_add_participant_editText_mail_address))
                .perform(typeText(firstMeetingEmail));
        onView(withText(R.string.alert_dialog_positive_button))
                .perform(click());
        onView(withId(R.id.imageButton_addMeeting_activity_addMeeting))
                .perform(scrollTo(), click());

        // Second Meeting
        onView(withId(R.id.floatingButton_addMeeting))
                .perform(click());
        onView(withId(R.id.editText_addMeeting_activity_meetingName))
                .perform(typeText(secondMeetingName));
        onView(withId(R.id.textView_addMeeting_activity_dateBegin))
                .perform(scrollTo(), setTextInTextView(BEGIN_DATE_SECOND_MEETING));
        onView(withId(R.id.textView_addMeeting_activity_dateEnd))
                .perform(scrollTo(), setTextInTextView(END_DATE_SECOND_MEETING));
        onView(withId(R.id.imageButton_addMeeting_activity_addParticipant))
                .perform(scrollTo(), click());
        onView(withId(R.id.fragment_add_participant_editText_mail_address))
                .perform(typeText(firstMeetingEmail));
        onView(withText(R.string.alert_dialog_positive_button))
                .perform(click());
        onView(withId(R.id.imageButton_addMeeting_activity_addMeeting))
                .perform(scrollTo(), click());

        onView(withId(R.id.menuMain_item_filter))
                .perform(click());
        // Date begin
        onView(withId(R.id.fragment_filter_textView_dateBegin))
                .perform(setTextInTextView(BEGIN_DATE_SECOND_MEETING));
        // Date end
        onView(withId(R.id.fragment_filter_textView_dateEnd))
                .perform(setTextInTextView(END_DATE_SECOND_MEETING));
        // Filter
        onView(withText(MainActivity.ALERTDIALOG_FILTER_POSITIVE_TEXT))
                .perform(click());
        // Assert that the Meeting displayed is the good one
        onView(withRecyclerView(R.id.recyclerView).atPosition(0))
                .check(matches(not(hasDescendant(withText(BEGIN_DATE_FIRST_MEETING)))));

    }

    @Test
    public void canWeFilterMeetingByMeetingRoom() {
        MeetingApiService meetingApiService = new DummyMeetingApiService();
        MeetingRoom meetingRoom = meetingApiService.getMeetingRooms().get(1);

        // First Meeting
        onView(withId(R.id.floatingButton_addMeeting))
                .perform(click());
        onView(withId(R.id.editText_addMeeting_activity_meetingName))
                .perform(typeText(firstMeetingName));
        onView(withId(R.id.textView_addMeeting_activity_dateBegin))
                .perform(setTextInTextView(BEGIN_DATE_FIRST_MEETING));
        onView(withId(R.id.textView_addMeeting_activity_dateEnd))
                .perform(scrollTo(), setTextInTextView(END_DATE_FIRST_MEETING));
        onView(withId(R.id.imageButton_addMeeting_activity_addParticipant))
                .perform(scrollTo(), click());
        onView(withId(R.id.fragment_add_participant_editText_mail_address))
                .perform(typeText(firstMeetingEmail));
        onView(withText(R.string.alert_dialog_positive_button))
                .perform(click());
        onView(withId(R.id.imageButton_addMeeting_activity_addMeeting))
                .perform(scrollTo(), click());

        // Second Meeting
        onView(withId(R.id.floatingButton_addMeeting))
                .perform(click());
        onView(withId(R.id.editText_addMeeting_activity_meetingName))
                .perform(typeText(secondMeetingName));
        onView(withId(R.id.textView_addMeeting_activity_dateBegin))
                .perform(scrollTo(), setTextInTextView(BEGIN_DATE_SECOND_MEETING));
        onView(withId(R.id.textView_addMeeting_activity_dateEnd))
                .perform(scrollTo(), setTextInTextView(END_DATE_SECOND_MEETING));
        onView(withId(R.id.spinner_addMeeting_activity_meetingRoom))
                .perform(scrollTo(), click());
        onData(allOf(is(instanceOf(MeetingRoom.class)), is(meetingRoom)))
                .perform(click());
        onView(withId(R.id.imageButton_addMeeting_activity_addParticipant))
                .perform(scrollTo(), click());
        onView(withId(R.id.fragment_add_participant_editText_mail_address))
                .perform(typeText(firstMeetingEmail));
        onView(withText(R.string.alert_dialog_positive_button))
                .perform(click());
        onView(withId(R.id.imageButton_addMeeting_activity_addMeeting))
                .perform(scrollTo(), click());

        onView(withId(R.id.menuMain_item_filter))
                .perform(click());

        onView(withId(R.id.fragment_filter_spinner_MeetingRoom))
                .perform(click());
        onData(allOf(is(instanceOf(MeetingRoom.class)), is(meetingRoom)))
                .inRoot(isPlatformPopup())
                .perform(click());
        onView(withText(R.string.item_filter))
                .perform(click());

        onView(withRecyclerView(R.id.recyclerView).atPosition(0))
                .check(matches(hasDescendant(withText(meetingRoom.getName()))));

    }

    @Test
    public void canWeRemoveMeeting() {
        onView(withId(R.id.floatingButton_addMeeting))
                .perform(click());
        onView(withId(R.id.editText_addMeeting_activity_meetingName))
                .perform(typeText(firstMeetingName));
        onView(withId(R.id.imageButton_addMeeting_activity_addParticipant))
                .perform(scrollTo(), click());
        onView(withId(R.id.fragment_add_participant_editText_mail_address))
                .perform(typeText(firstMeetingEmail));
        onView(withText(R.string.alert_dialog_positive_button))
                .perform(click());
        onView(withId(R.id.imageButton_addMeeting_activity_addMeeting))
                .perform(scrollTo(), click());

        onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, new RemoveMeetingAction()));
        onView(withId(R.id.recyclerView))
                .check(matches(not(withText(firstMeetingName))));
    }

    //
    // UTILS FOR TESTS
    //

    /**
     * Util for perfom Recyclerview actions
     * @param pRecyclerViewId
     * @return
     */
    private static RecyclerViewMatcher withRecyclerView(final int pRecyclerViewId) {
        return new RecyclerViewMatcher(pRecyclerViewId);
    }

    /**
     * Useful for assign value to a TextView
     * @param pValue
     * @return
     */
    private static ViewAction setTextInTextView(final String pValue) {
        return new ViewAction() {
            @SuppressWarnings("unchecked")
            @Override
            public Matcher<View> getConstraints() {
                return allOf(isDisplayed(), isAssignableFrom(TextView.class));
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((TextView) view).setText(pValue);
            }

            @Override
            public String getDescription() {
                return "replace text";
            }
        };
    }
}