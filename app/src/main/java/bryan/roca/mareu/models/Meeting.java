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
}
