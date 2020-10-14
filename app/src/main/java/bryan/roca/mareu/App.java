package bryan.roca.mareu;

import android.app.Application;

import bryan.roca.mareu.DI.DI;
import bryan.roca.mareu.service.MeetingApiService;

public class App extends Application {

    public static MeetingApiService service;

    @Override
    public void onCreate() {
        super.onCreate();

        // Launching the API service
        service = DI.getMeetingApiService();
    }
}
