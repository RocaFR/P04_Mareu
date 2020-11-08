package bryan.roca.mareu.views;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Random;

import bryan.roca.mareu.App;
import bryan.roca.mareu.R;
import bryan.roca.mareu.controllers.activities.AddMeetingActivity;
import bryan.roca.mareu.controllers.activities.MainActivity;
import bryan.roca.mareu.event.DeleteMeetingEvent;
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
    final private ImageView mcircleShape;

    public MeetingViewHolder(@NonNull final View itemView) {
        super(itemView);

        // Views declarations
        mTextViewMeetingName =  itemView.findViewById(R.id.textView_meetingName);
        mTextViewMeetingRoom =  itemView.findViewById(R.id.textView_meetingRoom);
        mTextViewTimeBegin =  itemView.findViewById(R.id.textView_timeBegin);
        mImageButtonRemoveMeeting = itemView.findViewById(R.id.imageButton_removeMeeting);
        mTextViewTheListOfParticipants = itemView.findViewById(R.id.imageView_list_item_theListOfParticipants);
        mcircleShape = itemView.findViewById(R.id.shape_meeting);
    }

    public void updateUI(Meeting pMeeting) {
        Drawable drawable = (Drawable) mcircleShape.getBackground().mutate();
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
