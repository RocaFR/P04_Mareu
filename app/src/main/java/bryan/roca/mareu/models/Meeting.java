package bryan.roca.mareu.models;

import org.joda.time.DateTime;
import org.joda.time.Interval;

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
    private DateTime dateBegin;
    /**
     * The end date of the Meeting represented by a day and a hour.
     */
    private DateTime dateEnd;
    /**
     * The interval used for check among other the overlaps
     */
    private Interval interval;
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
    public Meeting(DateTime pDateBegin, DateTime pDateEnd, MeetingRoom pPlace, String pSubject, List<Collaborator> pParticipantsList) {
        this.dateBegin = pDateBegin;
        this.dateEnd = pDateEnd;
        interval = new Interval(pDateBegin, pDateEnd);
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
    public DateTime getDateBegin() {
        return dateBegin;
    }

    /**
     * Get the end {@link Date} of the Meeting.
     * @return the Date where the Meeting ending
     */
    public DateTime getDateEnd() {
        return dateEnd;
    }

    /**
     * Get the Interval of the Meeting.
     * @return the Interval from dateBegin and dateEnd
     */
    public Interval getInterval() {
        return interval;
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
    public void setDateBegin(DateTime pDateBegin) {
        dateBegin = pDateBegin;
    }

    /**
     * Set the ending {@link Date} of the Meeting.
     * @param pDateEnd
     */
    public void setDateEnd(DateTime pDateEnd) {
        dateEnd = pDateEnd;
    }

    /**
     * <p>Update the Interval from dateBegin and dateEnd.<br>
     * <strong>Set dateBegin and dateEnd before !</strong></p>
     */
    public void updateInterval() {
        interval = new Interval(dateBegin, dateEnd);
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
