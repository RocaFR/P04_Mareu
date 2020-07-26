package bryan.roca.mareu.controllers.activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.joda.time.DateTime;

import java.util.Arrays;
import java.util.List;

import bryan.roca.mareu.R;
import bryan.roca.mareu.event.DeleteMeetingEvent;
import bryan.roca.mareu.models.Collaborator;
import bryan.roca.mareu.models.Meeting;
import bryan.roca.mareu.service.DummyMeetingApiService;
import bryan.roca.mareu.service.MeetingApiService;
import bryan.roca.mareu.ui.di.DI;
import bryan.roca.mareu.utils.ItemClickSupport;
import bryan.roca.mareu.views.MeetingAdapter;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton mFloatingActionButtonAddActivity;
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
        mFloatingActionButtonAddActivity = findViewById(R.id.floatingButton_addMeeting);

        // Data
        List<Collaborator> collaboratorList = Arrays.asList(new Collaborator("bryan.ferreras@gmail.com"), new Collaborator("solene.moussion@gmail.com"), new Collaborator("marineducap33@free.fr"));
        mMeetingApiService = DI.getMeetingApiService();
        Meeting meeting = new Meeting(DateTime.now(), DateTime.now().plusMinutes(45), mMeetingApiService.getMeetingRooms().get(0), "Test", collaboratorList);
        Meeting meeting2 = new Meeting(DateTime.now().plusMinutes(60), DateTime.now().plusMinutes(120), mMeetingApiService.getMeetingRooms().get(1), "Test 2", collaboratorList);
        mMeetingApiService.addMeeting(meeting);
        mMeetingApiService.addMeeting(meeting2);

        this.configureRecyclerView();

        mFloatingActionButtonAddActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                Intent intent = new Intent(getBaseContext(), AddMeetingActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    private void configureRecyclerView() {
        this.initList();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    private void initList() {
        mMeetingList = mMeetingApiService.getMeetings();
        mMeetingAdapter = new MeetingAdapter(mMeetingList);
        mRecyclerView.setAdapter(mMeetingAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.configureRecyclerView();
    }

    /**
     * Fired when the user tap the Image Remove Button
     * @param pDeleteMeetingEvent
     */
    @Subscribe
    public void onRemoveMeeting(DeleteMeetingEvent pDeleteMeetingEvent) {
        mMeetingApiService.removeMeeting(pDeleteMeetingEvent.getMeeting());
        this.initList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuMain_item_filter) {
            Toast.makeText(getBaseContext(), "Soon...", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}