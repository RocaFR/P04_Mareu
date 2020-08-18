package bryan.roca.mareu;

import android.support.annotation.NonNull;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import bryan.roca.mareu.DI.DI;
import bryan.roca.mareu.models.Collaborator;
import bryan.roca.mareu.models.Meeting;
import bryan.roca.mareu.models.MeetingRoom;
import bryan.roca.mareu.service.MeetingApiService;

import static org.junit.Assert.*;

/**
 * Unit tests for Meetings management
 *
 */
public class MeetingTests {

    private Meeting mMeeting;
    private MeetingRoom mMeetingRoom;
    private MeetingApiService mMeetingApiService;

    @Before
    public void setup() {
        mMeetingApiService = DI.getNewInstanceMeetingApiService();
    }

    /**
     * Assert that ApiService is a Singleton
     */
    @Test
    public void canWeGetApiService() {
        MeetingApiService apiService = DI.getMeetingApiService();
        MeetingApiService secondApiService = DI.getMeetingApiService();
        assertSame(apiService, secondApiService);
    }

    /**
     * Assert we can create a new Meeting Room
     */
    @Test
    public void canWeCreateNewMeetingRoom() {
        this.configureAMeeting();
        assertNotNull(mMeetingRoom);
    }

    /**
     * Assert we can get Meeting Rooms list
     */
    @Test
    public void canWeGetMeetingRoomsList() {
        assertFalse(mMeetingApiService.getMeetingRooms().isEmpty());
    }

    /**
     * Assert we can create a new Meeting with success
     */
    @Test
    public void canWeCreateNewMeeting() {
        this.configureAMeeting();
        assertNotNull(mMeeting);
    }

    /**
     * Assert we can get Meetings list
     */
    @Test
    public void canWeGetMeetingsList() {
        this.configureAMeeting();
        List<Meeting> mMeetingList = mMeetingApiService.getMeetings();
        mMeetingApiService.addMeeting(mMeeting);
        assertTrue(mMeetingList.size() == 1);
    }

    /**
     * Assert we can filter the Meeting list by Meeting Room
     */
    @Test
    public void canWeFilterMeeting() {
        this.configureAMeeting();
        mMeetingApiService.addMeeting(mMeeting);

        List<Meeting> filteredList = mMeetingApiService.getMeetings(mMeeting.getPlace());
        assertTrue(filteredList.get(0).getPlace() == mMeeting.getPlace());
    }

    /**
     * Assert we can filter the Meeting list by date range
     */
    @Test
    public void canWeGetMeetingFromInterval() {
        // Initialize mMeeting
        this.configureAMeeting();
        // Initialize meeting2
        DateTime beginningDate = mMeeting.getDateBegin().plusDays(1);
        DateTime endDate = beginningDate.plusMinutes(30);
        int randomMeetingRoomID = (int)(Math.random() * mMeetingApiService.getMeetingRooms().size());
        Meeting meeting2 = new Meeting(beginningDate, endDate, mMeetingRoom, "Event Storming", Arrays.asList(new Collaborator("bryan.ferreras@gmail.com"), new Collaborator("moussion.solene@gmail.com")));

        List<Meeting> meetingList = mMeetingApiService.getMeetings();
        meetingList.add(mMeeting);
        meetingList.add(meeting2);

        List<Meeting> meetingsListFiletered = mMeetingApiService.getMeetings(mMeeting.getDateBegin(), mMeeting.getDateEnd());
        assertFalse(meetingsListFiletered.contains(meeting2));
    }

    /**
     * Assert we get the original meeting list if a error is occurred in getMeeting by date range
     */
    @Test
    public void assertCanWeGetOriginalListIfErrorDateRange() {
        // Initialize mMeeting
        this.configureAMeeting();
        // Initialize meeting2
        DateTime beginningDate = mMeeting.getDateBegin().plusDays(1);
        DateTime endDate = beginningDate.plusMinutes(30);
        int randomMeetingRoomID = (int)(Math.random() * mMeetingApiService.getMeetingRooms().size());
        Meeting meeting2 = new Meeting(beginningDate, endDate, mMeetingRoom, "Event Storming", Arrays.asList(new Collaborator("bryan.ferreras@gmail.com"), new Collaborator("moussion.solene@gmail.com")));

        List<Meeting> meetingList = mMeetingApiService.getMeetings();
        meetingList.add(mMeeting);
        meetingList.add(meeting2);

        List<Meeting> meetingListFiltered = mMeetingApiService.getMeetings(mMeeting.getDateEnd(), mMeeting.getDateBegin());
        assertTrue(meetingListFiltered.isEmpty());
        assertEquals(2, meetingList.size());
    }

    /**
     * Assert we can add Meeting into empty list with success
     */
    @Test
    public void canWeAddMeeting() {
        this.configureAMeeting();
        List<Meeting> mMeetingsList = mMeetingApiService.getMeetings();
        mMeetingApiService.addMeeting(mMeeting);
        assertTrue(mMeetingsList.contains(mMeeting));
    }

    /**
     * Assert we cannot add a Meeting if the Interval overlaps in same Meeting Room
     */
    @Test
    public void doNotAddMeetingIfOverlaps() {
        // First Meeting
        this.configureAMeeting();
        // Second Meeting with same Interval
        DateTime dtBegin = mMeeting.getDateBegin();
        DateTime dtEnd = mMeeting.getDateEnd();
        Meeting meetingWithSameInterval = new Meeting(
                dtBegin,
                dtEnd,
                mMeeting.getPlace(),
                "Meeting Test",
                Arrays.asList(new Collaborator("bryan.ferreras@gmail.com"), new Collaborator("moussion.solene@gmail.com")));

        mMeetingApiService.addMeeting(mMeeting);
        assertFalse(mMeetingApiService.addMeeting(meetingWithSameInterval));
    }

    /**
     * Assert we can add a Meeting in same Meeting Room if he do not overlaps
     */
    @Test
    public void canWeAddMeetingInSameMeetingRoomsIfDoNotOverlaps() {
        // First Meeting
        this.configureAMeeting();
        // Second Meeting
        DateTime dtBegin = mMeeting.getDateEnd().plusMinutes(15);
        DateTime dtEnd = dtBegin.plusMinutes(45);

        Meeting secondMeeting = new Meeting(
                dtBegin,
                dtEnd,
                mMeeting.getPlace(),
                "Meeting Test",
                Arrays.asList(new Collaborator("bryan.ferreras@gmail.com"), new Collaborator("moussion.solene@gmail.com")));

        assertTrue(mMeetingApiService.addMeeting(mMeeting));
        assertTrue(mMeetingApiService.addMeeting(secondMeeting));
    }

    /**
     * Assert we can remove a Meeting with success
     */
    @Test
    public void canWeRemoveMeeting() {
        this.configureAMeeting();
        List<Meeting> mMeetingList = mMeetingApiService.getMeetings();
        mMeetingApiService.addMeeting(mMeeting);
        mMeetingApiService.removeMeeting(mMeeting);
        assertFalse(mMeetingList.contains(mMeeting));
    }

    /**
     * Initialize a Meeting for tests
     */
    private void configureAMeeting() {
        DateTime beginningDate = new DateTime();
        DateTime endDate = beginningDate.plusMinutes(30);
        int randomMeetingRoomID = (int)(Math.random() * mMeetingApiService.getMeetingRooms().size());
        mMeetingRoom = mMeetingApiService.getMeetingRooms().get(randomMeetingRoomID);

        mMeeting = new Meeting(beginningDate, endDate, mMeetingRoom, "Event Storming", Arrays.asList(new Collaborator("bryan.ferreras@gmail.com"), new Collaborator("moussion.solene@gmail.com")));
    }
}