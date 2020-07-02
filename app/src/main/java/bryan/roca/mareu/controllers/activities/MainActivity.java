package bryan.roca.mareu.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import bryan.roca.mareu.R;
import bryan.roca.mareu.models.Meeting;
import bryan.roca.mareu.service.DummyMeetingApiService;
import bryan.roca.mareu.service.MeetingApiService;
import bryan.roca.mareu.views.MeetingAdapter;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Meeting> mMeetingList;
    private MeetingAdapter mMeetingAdapter;
    private MeetingApiService mMeetingApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMeetingApiService = new DummyMeetingApiService();
        mRecyclerView = findViewById(R.id.recyclerView);
    }

    private void configureRecyclerView() {
        this.mMeetingList = mMeetingApiService.getMeetings();
        this.mMeetingAdapter = new MeetingAdapter(mMeetingList);
        this.mRecyclerView.setAdapter(mMeetingAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
    }
}