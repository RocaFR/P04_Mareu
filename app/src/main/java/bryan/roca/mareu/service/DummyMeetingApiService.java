package bryan.roca.mareu.service;

import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.MutableDateTime;

import java.util.ArrayList;
import java.util.List;

import bryan.roca.mareu.R;
import bryan.roca.mareu.controllers.activities.MainActivity;
import bryan.roca.mareu.models.Meeting;
import bryan.roca.mareu.models.MeetingRoom;

/**
 * Mareu - bryan.roca.mareu.service
 * 13/06/20
 * Author Bryan Ferreras-Roca
 */
public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> mMeetingList = DummyMeetingGenerator.generateMeetings();
    private List<MeetingRoom> mMeetingRoomsList = DummyMeetingGenerator.generateMeetingRooms();

    /**
     * Get the list of all Meetings
     * @return
     */
    @Override
    public List<Meeting> getMeetings() { return mMeetingList; }

    @Override
    public List<Meeting> getMeetings(MeetingRoom pMeetingRoom) {
        List<Meeting> meetingListByMeetingRoom = new ArrayList<>();
        if (pMeetingRoom != null) {
            for (Meeting meeting : mMeetingList) {
                if (pMeetingRoom.equals(meeting.getPlace())) {
                    meetingListByMeetingRoom.add(meeting);
                }
            }
        }
        return meetingListByMeetingRoom;
    }

    @Override
    public List<Meeting> getMeetings(DateTime pDateTimeBegin, DateTime pDateTimeEnd) {
        // Adding hours for enable overlaps
        MutableDateTime mdt = pDateTimeEnd.toMutableDateTime();
        mdt.setHourOfDay(23);
        mdt.setMinuteOfHour(59);
        pDateTimeEnd = mdt.toDateTime();
        mMeetingList = getMeetings();
        List<Meeting> meetingListByDateRange = new ArrayList<>();

        if (pDateTimeBegin.isBefore(pDateTimeEnd)) {
            Interval intervalToFilter = new Interval(pDateTimeBegin, pDateTimeEnd);
            for (Meeting meeting : mMeetingList) {
                if (meeting.getInterval().overlaps(intervalToFilter)) {
                    meetingListByDateRange.add(meeting);
                }
            }
        } else {
            return getMeetings();
        }
        return meetingListByDateRange;
    }

    @Override
    public boolean addMeeting(Meeting pMeeting) {
        // Get the Meeting list for the pMeeting's Place
        List<Meeting> mMeetingListByMeetingRoom = getMeetings(pMeeting.getPlace());
        boolean isOverlap = false;

        // Check if the Interval exist in mMeetingListByMeetingRoom
        if (mMeetingListByMeetingRoom.size() > 0) {
            for (Meeting meeting : mMeetingListByMeetingRoom) {
                if (pMeeting.getInterval().overlaps(meeting.getInterval())) {
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

    @Override
    public List<MeetingRoom> getMeetingRooms() {
        return mMeetingRoomsList;
    }
}
