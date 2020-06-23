package bryan.roca.mareu.service;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import java.util.ArrayList;
import java.util.List;

import bryan.roca.mareu.models.Meeting;
import bryan.roca.mareu.models.MeetingRoom;

/**
 * Mareu - bryan.roca.mareu.service
 * 13/06/20
 * Author Bryan Ferreras-Roca
 */
public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> mMeetingList = DummyMeetingGenerator.generateMeetings();

    @Override
    public List<Meeting> getMeetings() {
        return mMeetingList;
    }

    @Override
    public List<Meeting> getMeetings(MeetingRoom pMeetingRoom) {
        List<Meeting> mMeetingListByMeetingRoom = new ArrayList<>();
        if (pMeetingRoom != null) {
            for (Meeting meeting : mMeetingList) {
                if (pMeetingRoom.equals(meeting.getPlace())) {
                    mMeetingListByMeetingRoom.add(meeting);
                }
            }
        }
        return mMeetingListByMeetingRoom;
    }

    @Override
    public boolean addMeeting(Meeting pMeeting) {
        // Get the Meeting list for the pMeeting's Place
        List<Meeting> mMeetingListByMeetingRoom = getMeetings(pMeeting.getPlace());
        boolean isOverlap = false;

        // Create the Interval for pMeeting
        DateTime dateBegin = new DateTime(pMeeting.getDateBegin());
        DateTime dateEnd = new DateTime(pMeeting.getDateEnd());
        Interval interval = new Interval(dateBegin, dateEnd);

        // Check if the Interval above exist in mMeetingListByMeetingRoom
        if (mMeetingListByMeetingRoom.size() > 0) {
            for (Meeting meeting : mMeetingListByMeetingRoom) {
                DateTime dtBegin = new DateTime(meeting.getDateBegin());
                DateTime dtEnd = new DateTime(meeting.getDateEnd());
                Interval intervalTmp = new Interval(dtBegin, dtEnd);

                if (interval.overlaps(intervalTmp)) {
                    isOverlap = true;
                    break;
                }
            }
            if (!isOverlap) {
                mMeetingList.add(pMeeting);
                return true;
            }
        } else {
            mMeetingList.add(pMeeting);
            return true;
        }
        return false;
    }

    @Override
    public void removeMeeting(Meeting pMeeting) {
        mMeetingList.remove(pMeeting);
    }
}
