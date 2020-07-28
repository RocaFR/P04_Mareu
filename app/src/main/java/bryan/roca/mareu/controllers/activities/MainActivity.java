package bryan.roca.mareu.controllers.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bryan.roca.mareu.R;
import bryan.roca.mareu.controllers.fragments.DatePickerFragment;
import bryan.roca.mareu.event.DeleteMeetingEvent;
import bryan.roca.mareu.models.Collaborator;
import bryan.roca.mareu.models.Meeting;
import bryan.roca.mareu.models.MeetingRoom;
import bryan.roca.mareu.service.MeetingApiService;
import bryan.roca.mareu.ui.di.DI;
import bryan.roca.mareu.utils.SetupDatesForTextViews;
import bryan.roca.mareu.views.MeetingAdapter;


public class MainActivity extends AppCompatActivity implements DatePickerFragment.OnDateChangeListener {

    private MeetingApiService mMeetingApiService;
    private RecyclerView mRecyclerView;
    private List<Meeting> mMeetingList;
    private MeetingAdapter mMeetingAdapter;
    private TextView mTextViewFilterDateBegin;
    private TextView mTextViewFilterDateEnd;
    private FloatingActionButton floatingActionButtonAddMeeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Views
        mRecyclerView = findViewById(R.id.recyclerView);
        floatingActionButtonAddMeeting = findViewById(R.id.floatingButton_addMeeting);

        // Data
        List<Collaborator> collaboratorList = Arrays.asList(new Collaborator("bryan.ferreras@gmail.com"), new Collaborator("solene.moussion@gmail.com"), new Collaborator("marineducap33@free.fr"));
        mMeetingApiService = DI.getMeetingApiService();
        Meeting meeting = new Meeting(DateTime.now(), DateTime.now().plusMinutes(45), mMeetingApiService.getMeetingRooms().get(0), "Test", collaboratorList);
        Meeting meeting2 = new Meeting(DateTime.now().plusMinutes(60), DateTime.now().plusMinutes(120), mMeetingApiService.getMeetingRooms().get(1), "Test 2", collaboratorList);
        mMeetingApiService.addMeeting(meeting);
        mMeetingApiService.addMeeting(meeting2);

        this.configureRecyclerView();
        this.configureFloatingActionButtonAddMeeting();
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

    @Override
    protected void onResume() {
        super.onResume();
        this.configureRecyclerView();
    }

    /**
     * Configure the Floating Action Button Add Meeting listener
     */
    private void configureFloatingActionButtonAddMeeting() {
        floatingActionButtonAddMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                Intent intent = new Intent(getBaseContext(), AddMeetingActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * Configure the Recyclerview for display
     */
    private void configureRecyclerView() {
        this.initList();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    /**
     * Initialize and update the Recyclerview's list
     */
    private void initList() {
        mMeetingList = mMeetingApiService.getMeetings();
        mMeetingAdapter = new MeetingAdapter(mMeetingList);
        mRecyclerView.setAdapter(mMeetingAdapter);
    }

    /**
     * Fired when the user tap the Image Remove Button
     * @param pDeleteMeetingEvent the Meeting to remove
     */
    @Subscribe
    public void onRemoveMeeting(DeleteMeetingEvent pDeleteMeetingEvent) {
        mMeetingApiService.removeMeeting(pDeleteMeetingEvent.getMeeting());
        this.initList();
    }

    // Inflate the filter menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Fired when filter button was clicked
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ( item.getItemId() == R.id.menuMain_item_filter ) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.fragment_filter_meetings, null);

            mTextViewFilterDateBegin = view.findViewById(R.id.fragment_filter_textView_dateBegin);
            mTextViewFilterDateEnd = view.findViewById(R.id.fragment_filter_textView_dateEnd);
            SetupDatesForTextViews setup = new SetupDatesForTextViews();
            setup.setup();
            mTextViewFilterDateBegin.setHint(setup.getDayOfMonthBegin() + "/" + setup.getMonthOfYearBegin() + "/" + setup.getYear());
            mTextViewFilterDateEnd.setHint(setup.getDayOfMonthEnd() + "/" + setup.getMonthOfYearEnd() + "/" + setup.getYear());

            mTextViewFilterDateBegin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment dialogFragment = new DatePickerFragment();
                    dialogFragment.show(getSupportFragmentManager(), "filterDateBeginPicker");
                }
            });
            mTextViewFilterDateEnd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment dialogFragment = new DatePickerFragment();
                    dialogFragment.show(getSupportFragmentManager(), "filterDateEndPicker");
                }
            });
            final Spinner spinner = view.findViewById(R.id.fragment_filter_spinner_MeetingRoom);
            final List<MeetingRoom> meetingRoomsFiltered = new ArrayList<>();
            ArrayAdapter<MeetingRoom> meetingRoomArrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, meetingRoomsFiltered);
            for (Meeting meeting : mMeetingList) {
                if (!meetingRoomsFiltered.contains(meeting.getPlace())) {
                    meetingRoomsFiltered.add(meeting.getPlace());
                }
            }
            spinner.setAdapter(meetingRoomArrayAdapter);
            builder.setView(view);
            builder.setPositiveButton("Filter", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    List<Meeting> filteredMeetings = mMeetingApiService.getMeetings((MeetingRoom)spinner.getSelectedItem());
                    mMeetingAdapter = new MeetingAdapter(filteredMeetings);
                    mRecyclerView.setAdapter(mMeetingAdapter);
                }
            });
            builder.setNeutralButton("Reset", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mMeetingAdapter = new MeetingAdapter(mMeetingList);
                    mRecyclerView.setAdapter(mMeetingAdapter);
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    // Update the Date TextView for filter
    @Override
    public void onDateChange(String pTag, String pI, String pI1, String pI2) {
        switch (pTag) {
            case "filterDateBeginPicker":
                mTextViewFilterDateBegin.setText(pI2 + "/" + pI1 + "/" +pI);
                break;
            case "filterDateEndPicker":
                mTextViewFilterDateEnd.setText(pI2 + "/" + pI1 + "/" +pI);
                break;
        }
    }
}