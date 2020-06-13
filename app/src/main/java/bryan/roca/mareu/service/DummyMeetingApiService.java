package bryan.roca.mareu.service;

import java.util.List;

import bryan.roca.mareu.models.Meeting;

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
    public void addMeeting(Meeting pMeeting) {
        mMeetingList.add(pMeeting);
    }

    @Override
    public void removeMeeting(Meeting pMeeting) {
        mMeetingList.remove(pMeeting);
    }
}
