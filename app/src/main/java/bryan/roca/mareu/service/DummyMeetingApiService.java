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
    private List<MeetingRoom> mMeetingRoomsList = DummyMeetingGenerator.generateMeetingRooms();

    @Override
    public List<Meeting> getMeetings() { return mMeetingList; }

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
