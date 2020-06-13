package bryan.roca.mareu.service;

import java.util.List;

import bryan.roca.mareu.models.Collaborator;
import bryan.roca.mareu.models.Meeting;

/**
 * Mareu - bryan.roca.mareu.service
 * 13/06/20
 * Author Bryan Ferreras-Roca
 */
public interface MeetingApiService {

    List<Meeting> getMeetings();

    void addMeeting(Meeting pMeeting);

    void removeMeeting(Meeting pMeeting);
}
