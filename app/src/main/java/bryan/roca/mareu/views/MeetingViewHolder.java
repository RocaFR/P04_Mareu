package bryan.roca.mareu.views;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import bryan.roca.mareu.R;
import bryan.roca.mareu.models.Collaborator;
import bryan.roca.mareu.models.Meeting;

/**
 * Mareu - bryan.roca.mareu.views
 * 02/07/20
 * Author Bryan Ferreras-Roca
 */
class MeetingViewHolder extends RecyclerView.ViewHolder {

    // Views
    final private TextView mTextViewMeetingName;
    final private TextView mTextViewMeetingRoom;
    final private TextView mTextViewTimeBegin;
    public ImageButton mImageButtonRemoveMeeting;
    final private TextView mTextViewTheListOfParticipants;
    final private ImageView mCircleShape;

    public MeetingViewHolder(@NonNull final View itemView) {
        super(itemView);

        // Views declarations
        mTextViewMeetingName =  itemView.findViewById(R.id.textView_meetingName);
        mTextViewMeetingRoom =  itemView.findViewById(R.id.textView_meetingRoom);
        mTextViewTimeBegin =  itemView.findViewById(R.id.textView_timeBegin);
        mImageButtonRemoveMeeting = itemView.findViewById(R.id.imageButton_removeMeeting);
        mTextViewTheListOfParticipants = itemView.findViewById(R.id.imageView_list_item_theListOfParticipants);
        mCircleShape = itemView.findViewById(R.id.shape_meeting);
    }

    public void updateUI(Meeting pMeeting) {
        Drawable drawable = (Drawable) mCircleShape.getBackground().mutate();
        drawable.setTint(pMeeting.getShapeColor());

        // Date Formatter
        DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/yyyy");
        // Time Formatter
        fmt = DateTimeFormat.forPattern("HH:mm");
        String timeBegin = fmt.print(pMeeting.getDateBegin());

        mTextViewMeetingName.setText(pMeeting.getSubject() + " - ");
        mTextViewMeetingRoom.setText(pMeeting.getPlace().getName() + " - ");
        mTextViewTimeBegin.setText(timeBegin);

        for (Collaborator collaborator : pMeeting.getParticipantsList()) {
            if (TextUtils.isEmpty(mTextViewTheListOfParticipants.getText())) {
                mTextViewTheListOfParticipants.setText(collaborator.getId());
            } else {
                String str = mTextViewTheListOfParticipants.getText().toString();
                mTextViewTheListOfParticipants.setText(str + ", " + collaborator.getId());
            }
        }
    }
}
