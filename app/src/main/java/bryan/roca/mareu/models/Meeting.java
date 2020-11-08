package bryan.roca.mareu.models;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.Date;
import java.util.List;
import java.util.Random;

import bryan.roca.mareu.R;
import bryan.roca.mareu.controllers.activities.MainActivity;

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
     * The color assigned to this Meeting.<br>
     */
    private int shapeColor;

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
        this.interval = new Interval(dateBegin, dateEnd);
        this.place = pPlace;
        this.subject = pSubject;
        this.participantsList = pParticipantsList;
        this.shapeColor = this.generateRandomShapeColor();
    }

    public Meeting() {

    }



    ///
    /// GETTERS
    ///
    /**
     * Get the beginning {@link DateTime} of the Meeting.
     * @return the Date where the Meeting begin
     */
    public DateTime getDateBegin() {
        return dateBegin;
    }
    /**
     * Get the end {@link DateTime} of the Meeting.
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
     * @return the Place
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
     * @return the list
     */
    public List<Collaborator> getParticipantsList() {
        return participantsList;
    }

    /**
     * Get the color (int) who associated to this Meeting
     * @return the int color
     */
    public int getShapeColor() {
        return this.shapeColor;
    }


    ///
    /// SETTERS
    ///
    /**
     * Set the beginning {@link Date} of the Meeting.
     * @param pDateBegin the beginning DateTime
     */
    public void setDateBegin(DateTime pDateBegin) {
        dateBegin = pDateBegin;
    }

    /**
     * Set the ending {@link Date} of the Meeting.
     * @param pDateEnd the end DateTime
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
     * @param pPlace the {@link MeetingRoom}
     */
    public void setPlace(MeetingRoom pPlace) {
        place = pPlace;
    }

    /**
     * Set the subject about the Meeting.
     * @param pSubject the short subject
     */
    public void setSubject(String pSubject) {
        subject = pSubject;
    }

    /**
     * Set the participants ({@link Collaborator}) list of the Meeting.
     * @param pParticipantsList the list of participants
     */
    public void setParticipantsList(List<Collaborator> pParticipantsList) {
        participantsList = pParticipantsList;
    }

    /**
     * Generate a random int color and assign it.
     */
    public void setShapeColor() {
        this.shapeColor = this.generateRandomShapeColor();
    }

    ///
    /// UTILS
    ///

    /**
     * Generate a random int color from the array colors.
     * @return a random int color
     */
    private int generateRandomShapeColor() {
        int[] arrayColors = MainActivity.findResources().getResources().getIntArray(R.array.randomMeetingsColors);
        return arrayColors[new Random().nextInt(arrayColors.length)];
    }
}
