package bryan.roca.mareu.models;

import java.util.Date;
import java.util.List;

/**
 * <p><Mareu - bryan.roca.mareu.models</p>
 *
 * <p>A Meeting</p>
 *
 * <p>13/06/20<br>
 * Author Bryan Ferreras-Roca</p>
 */
public class Meeting {

    /**
     * The begeinning date of the Meeting represented by a day and a hour.
     */
    private Date dateBegin;
    /**
     * The end date of the Meeting represented by a day and a hour.
     */
    private Date dateEnd;
    /**
     * The place were the Meeting will take place. He is represented by a MeetingRoom.
     */
    private MeetingRoom place;
    /**
     * A short resume about the subject of the Meeting
     */
    private String subject;
    /**
     * The participants list of the Meeting. She is represented by a List of Collaborator.
     */
    private List<Collaborator> participantsList;

    /**
     * Default constructor
     * @param pDateBegin Date where Meeting begin
     * @param pDateEnd Date where Meeting end
     * @param pPlace {@link MeetingRoom Place} where Meeting take place
     * @param pSubject Short summary
     * @param pParticipantsList List of {@link Collaborator participants}
     */
    public Meeting(Date pDateBegin, Date pDateEnd, MeetingRoom pPlace, String pSubject, List<Collaborator> pParticipantsList) {
        this.dateBegin = pDateBegin;
        this.dateEnd = pDateEnd;
        this.place = pPlace;
        this.subject = pSubject;
        this.participantsList = pParticipantsList;
    }

    ///
    /// GETTERS
    ///

    /**
     * Get the beginning {@link Date} of the Meeting.
     * @return the Date where the Meeting begin
     */
    public Date getDateBegin() {
        return dateBegin;
    }

    /**
     * Get the end {@link Date} of the Meeting.
     * @return the Date where the Meeting ending
     */
    public Date getDateEnd() {
        return dateEnd;
    }

    /**
     * Get the place ({@link MeetingRoom}) where she will take place.
     * @return
     */
    public MeetingRoom getPlace() {
        return place;
    }

    /**
     * Get the subject about the Meeting.
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Get the participants ({@link Collaborator}) list of the Meeting.
     * @return
     */
    public List<Collaborator> getParticipantsList() {
        return participantsList;
    }

    ///
    /// SETTERS
    ///

    /**
     * Set the beginning {@link Date} of the Meeting.
     * @param pDateBegin
     */
    public void setDateBegin(Date pDateBegin) {
        dateBegin = pDateBegin;
    }

    /**
     * Set the ending {@link Date} of the Meeting.
     * @param pDateEnd
     */
    public void setDateEnd(Date pDateEnd) {
        dateEnd = pDateEnd;
    }

    /**
     * Set the place ({@link MeetingRoom}) where she will take place.
     * @param pPlace
     */
    public void setPlace(MeetingRoom pPlace) {
        place = pPlace;
    }

    /**
     * Set the subject about the Meeting.
     * @param pSubject
     */
    public void setSubject(String pSubject) {
        subject = pSubject;
    }

    /**
     * Set the participants ({@link Collaborator}) list of the Meeting.
     * @param pParticipantsList
     */
    public void setParticipantsList(List<Collaborator> pParticipantsList) {
        participantsList = pParticipantsList;
    }
}
