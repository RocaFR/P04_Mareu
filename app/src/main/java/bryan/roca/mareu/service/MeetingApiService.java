package bryan.roca.mareu.service;

import org.joda.time.DateTime;

import java.util.List;

import bryan.roca.mareu.models.Meeting;
import bryan.roca.mareu.models.MeetingRoom;

/**
 * Mareu - bryan.roca.mareu.service
 * 13/06/20
 * Author Bryan Ferreras-Roca
 */
public interface MeetingApiService {

    /**
     * Get the Meeting list for all Meeting Room.
     * @return all the Meetings list
     */
    List<Meeting> getMeetings();

    /**
     * Get Meeting list for this Meeting Room.
     * @param pMeetingRoom the Meeting Room for filter Meetings list
     * @return all the Meetings list filtered
     */
    List<Meeting> getMeetings(MeetingRoom pMeetingRoom);

    /**
     * Get the Meeting list for this Date Range.
     * @param pDateTimeBegin the Date where the Meetings begin
     * @param pDateTimeEnd the Date where the Meetings end
     * @return Meetings in this range
     */
    List<Meeting> getMeetings(DateTime  pDateTimeBegin, DateTime pDateTimeEnd);

    /**
     * Add a Meeting to the list of Meetings.
     * @param pMeeting the Meeting to add
     * @return true if success else false. Useful for manage behavior
     */
    boolean addMeeting(Meeting pMeeting);

    /**
     * Remove a Meeting from the Meetings list.
     * @param pMeeting the Meeting to remove
     */
    void removeMeeting(Meeting pMeeting);

    /**
     * Get the Meeting Rooms list.
     * @return all of Meeting Rooms
     */
    List<MeetingRoom> getMeetingRooms();

    void clean();
}
