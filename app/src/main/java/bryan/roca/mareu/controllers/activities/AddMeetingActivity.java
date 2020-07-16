package bryan.roca.mareu.controllers.activities;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bryan.roca.mareu.R;
import bryan.roca.mareu.models.Meeting;
import bryan.roca.mareu.models.MeetingRoom;
import bryan.roca.mareu.service.DummyMeetingApiService;
import bryan.roca.mareu.service.MeetingApiService;
import bryan.roca.mareu.utils.IsEmailValid;

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
    private ImageButton mImageButtonEmptyParticipantList;

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
        mImageButtonEmptyParticipantList = findViewById(R.id.imageButton_addMeeting_activity_emptyParticipantList);

        this.configureMeetingRoomSpinner();

        this.configureImageButtonAddMeeting();
        this.configureImageButtonAddAParticipantAndParticipantList();
    }

    private void configureImageButtonAddAParticipantAndParticipantList() {
        mImageButtonAddParticipant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                configureAlertDialogAddParticipant();
            }
        });
        mImageButtonEmptyParticipantList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(mTextViewTheParticipantsList.getText())) {
                    mTextViewTheParticipantsList.setText(null);
                    Toast.makeText(getBaseContext(), "List of participants emptied !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Configure the ImageButton state
     */
    private void configureImageButtonAddMeeting() {
        mEditTextMeetingsName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence pCharSequence, int pI, int pI1, int pI2) {

            }

            @Override
            public void onTextChanged(CharSequence pCharSequence, int pI, int pI1, int pI2) {
                if (checkAllFields()) {
                    mImageButtonAddMeeting.setEnabled(true);
                    mImageButtonAddMeeting.getDrawable().mutate().setTint(getResources().getColor(R.color.buttonAddMeetingTrue));
                } else {
                    mImageButtonAddMeeting.setEnabled(false);
                    mImageButtonAddMeeting.getDrawable().mutate().setTint(getResources().getColor(R.color.buttonAddMeetingFalse));
                }
            }

            @Override
            public void afterTextChanged(Editable pEditable) {

            }
        });
        mTextViewTheParticipantsList.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence pCharSequence, int pI, int pI1, int pI2) {

            }

            @Override
            public void onTextChanged(CharSequence pCharSequence, int pI, int pI1, int pI2) {
                if (checkAllFields()) {
                    mImageButtonAddMeeting.setEnabled(true);
                    mImageButtonAddMeeting.getDrawable().mutate().setTint(getResources().getColor(R.color.buttonAddMeetingTrue));
                } else {
                    mImageButtonAddMeeting.setEnabled(false);
                    mImageButtonAddMeeting.getDrawable().mutate().setTint(getResources().getColor(R.color.buttonAddMeetingFalse));
                }
            }

            @Override
            public void afterTextChanged(Editable pEditable) {

            }
        });
    }

    /**
     * Util for configureImageButtonAddMeeting()
     * @return
     */
    private Boolean checkAllFields() {
        return mEditTextMeetingsName.length() > 0 && mTextViewTheParticipantsList.length() > 0;
    }

    /**
     * Configure the Meeting Rooms's Spinner
     */
    private void configureMeetingRoomSpinner() {
        List<MeetingRoom> meetingList = mMeetingApiService.getMeetingRooms();
        ArrayAdapter<MeetingRoom> meetingRoomArrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, meetingList);
        mSpinner.setAdapter(meetingRoomArrayAdapter);
    }

    private void configureAlertDialogAddParticipant() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.fragment_add_a_participant, null);
        builder.setView(view);
        final EditText mEditText = view.findViewById(R.id.fragment_add_participant_editText_mail_address);
        builder.setPositiveButton(R.string.alert_dialog_positive_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface pDialogInterface, int pI) {
                if (IsEmailValid.isEmailAddressValid(mEditText.getText().toString())) {
                    if (mEditText.length() > 1) {
                        String str = mTextViewTheParticipantsList.getText().toString();

                        if (TextUtils.isEmpty(str)) {
                            mTextViewTheParticipantsList.setText(mEditText.getText());
                        } else {
                            mTextViewTheParticipantsList.setText(str +", " + mEditText.getText());
                        }
                        Toast.makeText(AddMeetingActivity.this, "Participant added", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getBaseContext(), "Incorrect e-mail address", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton(R.string.alert_dialog_negative_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface pDialogInterface, int pI) {
                Toast.makeText(AddMeetingActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}