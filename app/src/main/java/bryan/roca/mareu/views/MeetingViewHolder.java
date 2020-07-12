package bryan.roca.mareu.views;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import bryan.roca.mareu.R;
import bryan.roca.mareu.controllers.activities.AddMeetingActivity;
import bryan.roca.mareu.event.DeleteMeetingEvent;
import bryan.roca.mareu.models.Meeting;

/**
 * Mareu - bryan.roca.mareu.views
 * 02/07/20
 * Author Bryan Ferreras-Roca
 */
class MeetingViewHolder extends RecyclerView.ViewHolder {

    // Views
    private TextView mTextViewMeetingName;
    private TextView mTextViewMeetingRoom;
    private TextView mTextViewDateBegin;
    private TextView mTextViewDateEnd;
    private TextView mTextViewTimeBegin;
    private TextView mTextViewTimeEnd;
    private TextView mTextViewNumberOfParticipants;
    public ImageButton mImageButtonRemoveMeeting;

    public MeetingViewHolder(@NonNull final View itemView) {
        super(itemView);

        // Views declarations
        mTextViewMeetingName =  itemView.findViewById(R.id.textView_meetingName);
        mTextViewMeetingRoom =  itemView.findViewById(R.id.textView_meetingRoom);
        mTextViewDateBegin =  itemView.findViewById(R.id.textView_dateBegin);
        mTextViewDateEnd =  itemView.findViewById(R.id.textView_dateEnd);
        mTextViewTimeBegin =  itemView.findViewById(R.id.textView_timeBegin);
        mTextViewTimeEnd =  itemView.findViewById(R.id.textView_timeEnd);
        mTextViewNumberOfParticipants = itemView.findViewById(R.id.textView_numberOfParticipants);
        mImageButtonRemoveMeeting = itemView.findViewById(R.id.imageButton_removeMeeting);
    }

    public void updateUI(Meeting pMeeting) {
        // Date Formatter
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
        String dateBegin = fmt.print(pMeeting.getDateBegin());
        String dateEnd = fmt.print(pMeeting.getDateEnd());
        // Time Formatter
        fmt = DateTimeFormat.forPattern("HH:mm");
        String timeBegin = fmt.print(pMeeting.getDateBegin());
        String timeEnd = fmt.print(pMeeting.getDateEnd());

        mTextViewMeetingName.setText(pMeeting.getSubject());
        mTextViewMeetingRoom.setText(pMeeting.getPlace().getName());
        mTextViewDateBegin.setText(dateBegin);
        mTextViewDateEnd.setText(dateEnd);
        mTextViewTimeBegin.setText(timeBegin);
        mTextViewTimeEnd.setText(timeEnd);
        mTextViewNumberOfParticipants.setText(String.format("%x", pMeeting.getParticipantsList().size()));
    }
}
