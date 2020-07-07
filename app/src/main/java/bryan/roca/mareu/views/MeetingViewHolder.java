package bryan.roca.mareu.views;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import bryan.roca.mareu.R;
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


    public MeetingViewHolder(@NonNull View itemView) {
        super(itemView);

        // Views declarations
        mTextViewMeetingName =  itemView.findViewById(R.id.textView_meetingName);
        mTextViewMeetingRoom =  itemView.findViewById(R.id.textView_meetingRoom);
        mTextViewDateBegin =  itemView.findViewById(R.id.textView_dateBegin);
        mTextViewDateEnd =  itemView.findViewById(R.id.textView_dateEnd);
        mTextViewTimeBegin =  itemView.findViewById(R.id.textView_timeBegin);
        mTextViewTimeEnd =  itemView.findViewById(R.id.textView_timeEnd);
        mTextViewNumberOfParticipants = itemView.findViewById(R.id.textView_numberOfParticipants);
    }

    public void updateUI(Meeting pMeeting) {
        mTextViewMeetingName.setText(pMeeting.getSubject());
        mTextViewMeetingRoom.setText(pMeeting.getPlace().getName());
        //mTextViewDateBegin.setText(pMeeting.getDateBegin().toString());
        //mTextViewDateEnd.setText(pMeeting.getDateEnd().toString());
        mTextViewTimeBegin.setText(Integer.toString(pMeeting.getDateBegin().getHourOfDay()));
        mTextViewTimeEnd.setText(Integer.toString(pMeeting.getDateEnd().getHourOfDay()));
        mTextViewNumberOfParticipants.setText(Integer.toString(pMeeting.getParticipantsList().size()));
    }
}
