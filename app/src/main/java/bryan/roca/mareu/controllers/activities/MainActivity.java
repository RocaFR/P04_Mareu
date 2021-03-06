package bryan.roca.mareu.controllers.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import bryan.roca.mareu.App;
import bryan.roca.mareu.R;
import bryan.roca.mareu.controllers.fragments.DatePickerFragment;
import bryan.roca.mareu.event.DeleteMeetingEvent;
import bryan.roca.mareu.models.Meeting;
import bryan.roca.mareu.models.MeetingRoom;
import bryan.roca.mareu.service.MeetingApiService;
import bryan.roca.mareu.DI.DI;
import bryan.roca.mareu.utils.SetupDatesForTextViews;
import bryan.roca.mareu.views.MeetingAdapter;


public class MainActivity extends AppCompatActivity implements DatePickerFragment.OnDateChangeListener {

    private RecyclerView mRecyclerView;
    private List<Meeting> mMeetingList;
    private MeetingAdapter mMeetingAdapter;
    private TextView mTextViewFilterDateBegin;
    private TextView mTextViewPickAMeetingRoomText;
    private TextView mTextViewFilterDateEnd;
    private FloatingActionButton floatingActionButtonAddMeeting;
    private int mFilterMode;
    public static final int FILTER_MODE_MEETINGROOM = 0;
    public static final int FILTER_MODE_DATE = 1;
    public static final String ALERTDIALOG_FILTER_POSITIVE_TEXT = "Filter";
    public static final String ALERTDIALOG_FILTER_NEUTRAL_TEXT = "Reset";
    public static final String ALERTDIALOG_FILTER_NEGATIVE_TEXT = "Cancel";
    private TextView mTextViewNoMeeting;
    private ImageView mImageViewPeople;
    private static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();

        setContentView(R.layout.activity_main);

        // Views
        mRecyclerView = findViewById(R.id.recyclerView);
        floatingActionButtonAddMeeting = findViewById(R.id.floatingButton_addMeeting);
        mTextViewNoMeeting = findViewById(R.id.activity_main_textView_noMeeting);
        mImageViewPeople = findViewById(R.id.activity_main_imageView_people);

        this.configureHomeView();
        this.configureFloatingActionButtonAddMeeting();
    }

    // Utils to get Resources from a non Activity Class
    public static Context findResources() {
        return mContext;
    }

    /**
     * Configure the Home view when the list is empty, or not !
     */
    private void configureHomeView() {
        mMeetingList = App.service.getMeetings();
        if (mMeetingList.isEmpty()) {
            mTextViewNoMeeting.setVisibility(View.VISIBLE);
            mImageViewPeople.setVisibility(View.VISIBLE);
        } else {
            mTextViewNoMeeting.setVisibility(View.GONE);
            mImageViewPeople.setVisibility(View.GONE);
        }
        this.configureRecyclerView();
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
        this.configureHomeView();
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
    }

    /**
     * Initialize and update the Recyclerview's list
     */
    private void initList() {
        mMeetingList = App.service.getMeetings();
        mMeetingAdapter = new MeetingAdapter(mMeetingList);
        mRecyclerView.setAdapter(mMeetingAdapter);
    }

    /**
     * Fired when the user tap the Image Remove Button
     *
     * @param pDeleteMeetingEvent the Meeting to remove
     */
    @Subscribe
    public void onRemoveMeeting(DeleteMeetingEvent pDeleteMeetingEvent) {
        App.service.removeMeeting(pDeleteMeetingEvent.getMeeting());
        this.configureHomeView();
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
        if (item.getItemId() == R.id.menuMain_item_filter) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = LayoutInflater.from(this);
            View view = inflater.inflate(R.layout.fragment_filter_meetings, null);

            mTextViewFilterDateBegin = view.findViewById(R.id.fragment_filter_textView_dateBegin);
            mTextViewFilterDateEnd = view.findViewById(R.id.fragment_filter_textView_dateEnd);
            mTextViewPickAMeetingRoomText = view.findViewById(R.id.fragment_filter_textView_pickAMeetingRoom);

            SetupDatesForTextViews setup = new SetupDatesForTextViews();
            setup.setup();
            mTextViewFilterDateBegin.setHint(setup.getDayOfMonthBegin() + "/" + setup.getMonthOfYearBegin() + "/" + setup.getYear());
            mTextViewFilterDateEnd.setHint(setup.getDayOfMonthEnd() + "/" + setup.getMonthOfYearEnd() + "/" + setup.getYear());

            // Pop the Date picker for the beginning date
            mTextViewFilterDateBegin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment dialogFragment = new DatePickerFragment();
                    dialogFragment.show(getSupportFragmentManager(), "filterDateBeginPicker");
                }
            });
            // Pop the Date picker for ending date
            mTextViewFilterDateEnd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment dialogFragment = new DatePickerFragment();
                    dialogFragment.show(getSupportFragmentManager(), "filterDateEndPicker");
                }
            });

            // Configure the Spinner
            final Spinner spinner = view.findViewById(R.id.fragment_filter_spinner_MeetingRoom);
            final List<MeetingRoom> meetingRoomsFiltered = new ArrayList<>();
            ArrayAdapter<MeetingRoom> meetingRoomArrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, meetingRoomsFiltered);
            for (Meeting meeting : mMeetingList) {
                if (!meetingRoomsFiltered.contains(meeting.getPlace())) {
                    meetingRoomsFiltered.add(meeting.getPlace());
                }
            }
            spinner.setAdapter(meetingRoomArrayAdapter);
            // Set the filter mode
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mFilterMode = FILTER_MODE_MEETINGROOM;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            builder.setView(view);
            builder.setPositiveButton(ALERTDIALOG_FILTER_POSITIVE_TEXT, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Adapt the AlertDialog UI depending the filter mode
                    List<Meeting> filteredMeetings;
                    if (mFilterMode == FILTER_MODE_MEETINGROOM) {
                        filteredMeetings = App.service.getMeetings((MeetingRoom) spinner.getSelectedItem());
                        mMeetingAdapter = new MeetingAdapter(filteredMeetings);
                        mRecyclerView.setAdapter(mMeetingAdapter);
                    } else if (mFilterMode == FILTER_MODE_DATE) {
                        if (!TextUtils.isEmpty(mTextViewFilterDateBegin.getText()) && !TextUtils.isEmpty(mTextViewFilterDateEnd.getText())) {
                            // Fetching Dates datas
                            DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
                            String dateBeginFromInput = mTextViewFilterDateBegin.getText().toString();
                            String dateEndFromInput = mTextViewFilterDateEnd.getText().toString();
                            DateTime dateBegin = formatter.parseDateTime(dateBeginFromInput);
                            DateTime dateEnd = formatter.parseDateTime(dateEndFromInput);
                            filteredMeetings = App.service.getMeetings(dateBegin, dateEnd);
                            mMeetingAdapter = new MeetingAdapter(filteredMeetings);
                            mRecyclerView.setAdapter(mMeetingAdapter);
                        } else {
                            Toast.makeText(getBaseContext(), R.string.pick_date_filter, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
            builder.setNeutralButton(ALERTDIALOG_FILTER_NEUTRAL_TEXT, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mMeetingAdapter = new MeetingAdapter(mMeetingList);
                    mRecyclerView.setAdapter(mMeetingAdapter);
                }
            });
            builder.setNegativeButton(ALERTDIALOG_FILTER_NEGATIVE_TEXT, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setTitle("By Date OR Meeting Room");
            AlertDialog dialog = builder.create();
            dialog.show();

            mTextViewFilterDateBegin.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    mTextViewPickAMeetingRoomText.setVisibility(View.GONE);
                    spinner.setVisibility(View.GONE);
                    mFilterMode = FILTER_MODE_DATE;
                }
            });
            mTextViewFilterDateEnd.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    mTextViewPickAMeetingRoomText.setVisibility(View.GONE);
                    spinner.setVisibility(View.GONE);
                    mFilterMode = FILTER_MODE_DATE;
                }
            });
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
                mTextViewFilterDateBegin.setText(pI2 + "/" + pI1 + "/" + pI);
                break;
            case "filterDateEndPicker":
                mTextViewFilterDateEnd.setText(pI2 + "/" + pI1 + "/" + pI);
                break;
        }
    }
}