package bryan.roca.mareu.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

import bryan.roca.mareu.R;
import bryan.roca.mareu.models.Collaborator;
import bryan.roca.mareu.models.Meeting;
import bryan.roca.mareu.service.DummyMeetingApiService;
import bryan.roca.mareu.service.MeetingApiService;
import bryan.roca.mareu.views.MeetingAdapter;


public class MainActivity extends AppCompatActivity {

    private MeetingApiService mMeetingApiService;
    private RecyclerView mRecyclerView;
    private List<Meeting> mMeetingList;
    private MeetingAdapter mMeetingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views
        mRecyclerView = findViewById(R.id.recyclerView);

        // Data
        List<Collaborator> collaboratorList = Arrays.asList(new Collaborator("bryan.ferreras@gmail.Com"), new Collaborator("solene.moussion@gmail.com"));
        mMeetingApiService = new DummyMeetingApiService();
        Meeting meeting = new Meeting(DateTime.now(), DateTime.now().plusMinutes(45), mMeetingApiService.getMeetingRooms().get(0), "Test", collaboratorList);
        Meeting meeting2 = new Meeting(DateTime.now().plusMinutes(60), DateTime.now().plusMinutes(120), mMeetingApiService.getMeetingRooms().get(1), "Test 2", collaboratorList);
        mMeetingApiService.addMeeting(meeting);
        mMeetingApiService.addMeeting(meeting2);

        this.configureRecyclerView();
    }

    private void configureRecyclerView() {
        mMeetingAdapter = new MeetingAdapter(mMeetingApiService.getMeetings());
        mRecyclerView.setAdapter(mMeetingAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
}