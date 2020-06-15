package bryan.roca.mareu.service;

import android.view.View;

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
    private List<Meeting> mMeetingListByMeetingRoom;

    @Override
    public List<Meeting> getMeetings() {
        return mMeetingList;
    }

    @Override
    public List<Meeting> getMeetings(MeetingRoom pMeetingRoom) {
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
    public void addMeeting(Meeting pMeeting) {
        //TODO Implement this, filtered by mMeetingListByMeetingRoom
        //USING PERIOD COMPARISON
        // CREATE PERDIO FROM DATEBEGIN AND DATEEND
        // THEN COMPARE THIS NEW PERIOD WITH THE MEETING LIST'S PERIODS
        mMeetingList.add(pMeeting);
    }

    @Override
    public void removeMeeting(Meeting pMeeting) {
        mMeetingList.remove(pMeeting);
    }
}
