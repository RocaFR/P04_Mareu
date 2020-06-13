package bryan.roca.mareu.di;

import bryan.roca.mareu.service.DummyMeetingApiService;
import bryan.roca.mareu.service.MeetingApiService;

/**
 * Mareu - bryan.roca.mareu.service
 *
 * Dependency injector
 *
 * 13/06/20
 * Author Bryan Ferreras-Roca
 */
public class DI {

    private static MeetingApiService sMeetingApiService = new DummyMeetingApiService();

    public static MeetingApiService getMeetingApiService() {
        return sMeetingApiService;
    }

    public static MeetingApiService getNewInstanceMeetingApiService() {
        return new DummyMeetingApiService();
    }
}
