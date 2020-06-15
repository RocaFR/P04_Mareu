package bryan.roca.mareu.service;

import java.util.List;

import bryan.roca.mareu.models.Collaborator;
import bryan.roca.mareu.models.Meeting;
import bryan.roca.mareu.models.MeetingRoom;

/**
 * Mareu - bryan.roca.mareu.service
 * 13/06/20
 * Author Bryan Ferreras-Roca
 */
public interface MeetingApiService {

    /**
     * Get the Meeting list for all Meeting Room
     * @return
     */
    List<Meeting> getMeetings();

    /**
     * Get Meeting list for this Meeting Room
     * @param pMeetingRoom
     * @return
     */
    List<Meeting> getMeetings(MeetingRoom pMeetingRoom);

    void addMeeting(Meeting pMeeting);

    void removeMeeting(Meeting pMeeting);
}
