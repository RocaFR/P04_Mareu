package bryan.roca.mareu.event;

import bryan.roca.mareu.models.Meeting;

/**
 * Mareu - bryan.roca.mareu.event
 * 12/07/20
 * Author Bryan Ferreras-Roca
 *
 * Fired when a Meeting is delete
 */
public class DeleteMeetingEvent {

    /**
     * Meeting to delete
     */
    private Meeting mMeeting;

    /**
     * Constructor
     * @param pMeeting the Meeting to delete
     */
    public DeleteMeetingEvent(Meeting pMeeting) {
        this.mMeeting = pMeeting;
    }

    /**
     * Get the Meeting to delete
     * @return the Meeting
     */
    public Meeting getMeeting() {
        return this.mMeeting;
    }
}
