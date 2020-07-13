package bryan.roca.mareu.controllers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import bryan.roca.mareu.R;
import bryan.roca.mareu.models.Meeting;
import bryan.roca.mareu.models.MeetingRoom;
import bryan.roca.mareu.service.DummyMeetingApiService;
import bryan.roca.mareu.service.MeetingApiService;

public class AddMeetingActivity extends AppCompatActivity {

    // UI
    private Spinner mSpinner;
    private EditText mEditTextMeetingsName;
    private TextView mTextViewDateBegin;
    private TextView mTextViewDateEnd;
    private TextView mTextViewTimeBegin;
    private TextView mTextViewTimeEnd;
    private TextView mTextViewTheParticipantsList;
    private ImageButton mImageButtonAddParticipant;
    private ImageButton mImageButtonAddMeeting;

    // Service
    private MeetingApiService mMeetingApiService;
    List<Meeting> mMeetingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        // Service
        mMeetingApiService = new DummyMeetingApiService();

        // UI
        mEditTextMeetingsName = findViewById(R.id.editText_addMeeting_activity_meetingName);
        mTextViewDateBegin = findViewById(R.id.textView_addMeeting_activity_dateBegin);
        mTextViewDateEnd = findViewById(R.id.textView_addMeeting_activity_dateEnd);
        mTextViewTimeBegin = findViewById(R.id.textView_addMeeting_activity_timeBegin);
        mTextViewTimeEnd = findViewById(R.id.textView_addMeeting_activity_timeEnd);
        mTextViewTheParticipantsList = findViewById(R.id.textView_addMeeting_activity_theListOfParticipants);
        mImageButtonAddParticipant = findViewById(R.id.imageButton_addMeeting_activity_addParticipant);
        mImageButtonAddMeeting = findViewById(R.id.imageButton_addMeeting_activity_addMeeting);
        mImageButtonAddMeeting.setEnabled(false);
        mSpinner = findViewById(R.id.spinner_addMeeting_activity_meetingRoom);
        this.configureMeetingRoomSpinner();
    }

    private void configureMeetingRoomSpinner() {
        List<MeetingRoom> meetingList = mMeetingApiService.getMeetingRooms();
        ArrayAdapter<MeetingRoom> meetingRoomArrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, meetingList);
        mSpinner.setAdapter(meetingRoomArrayAdapter);
    }
}