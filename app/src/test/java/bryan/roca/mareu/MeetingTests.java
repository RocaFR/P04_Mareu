package bryan.roca.mareu;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bryan.roca.mareu.di.DI;
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

    Meeting mMeeting;
    MeetingRoom mMeetingRoom;
    private MeetingApiService mMeetingApiService;

    @Before
    public void setup() {
        //TODO create method
        Date beginningDate = Calendar.getInstance().getTime();
        Date endDate = Calendar.getInstance().getTime();
        Calendar tmpDate = Calendar.getInstance();
        tmpDate.setTime(endDate);
        tmpDate.add(Calendar.MINUTE, 30);
        endDate = tmpDate.getTime();

        mMeetingRoom = new MeetingRoom("A");
        mMeeting = new Meeting(beginningDate, endDate, mMeetingRoom, "Event Storming", Arrays.asList(new Collaborator("bryan.ferreras@gmail.com"), new Collaborator("moussion.solene@gmail.com")));
        mMeetingApiService = DI.getNewInstanceMeetingApiService();
    }

    /**
     * Assert we can create a new Meeting Room
     */
    @Test
    public void canWeCreateNewMeetingRoom() {
        assertNotNull(mMeetingRoom);
    }

    /**
     * Assert we can create a new Meeting with success
     */
    @Test
    public void canWeCreateNewMeeting() {
        assertNotNull(mMeeting);
    }

    /**
     * Assert we can get Meetings list
     */
    @Test
    public void canWeGetMeetingsList() {
        List<Meeting> mMeetingList = mMeetingApiService.getMeetings();
        mMeetingApiService.addMeeting(mMeeting);
        assertTrue(mMeetingList.size() == 1);
    }

    /**
     * Assert we can add Meeting with success
     */
    @Test
    public void canWeAddMeeting() {
        List<Meeting> mMeetingsList = mMeetingApiService.getMeetings();
        mMeetingApiService.addMeeting(mMeeting);
        assertTrue(mMeetingsList.contains(mMeeting));
    }

    /**
     * Assert we can remove a Meeting with success
     */
    @Test
    public void canWeRemoveMeeting() {
        List<Meeting> mMeetingList = mMeetingApiService.getMeetings();
        mMeetingApiService.addMeeting(mMeeting);
        mMeetingApiService.removeMeeting(mMeeting);
        assertFalse(mMeetingList.contains(mMeeting));
    }
}