package bryan.roca.mareu.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import bryan.roca.mareu.R;
import bryan.roca.mareu.event.DeleteMeetingEvent;
import bryan.roca.mareu.models.Meeting;

/**
 * Mareu - bryan.roca.mareu.views
 * 07/07/20
 * Author Bryan Ferreras-Roca
 */
public class MeetingAdapter extends RecyclerView.Adapter<MeetingViewHolder> {
    private List<Meeting> mMeetingList;

    public MeetingAdapter(List<Meeting> pMeetingList) {
        super();
        this.mMeetingList = pMeetingList;
    }

    @NonNull
    @Override
    public MeetingViewHolder onCreateViewHolder(@NonNull ViewGroup pViewGroup, int pI) {
        Context context = pViewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, pViewGroup, false);

        return new MeetingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MeetingViewHolder pMeetingViewHolder, final int pI) {
        // Update the UI
        pMeetingViewHolder.updateUI(mMeetingList.get(pMeetingViewHolder.getAdapterPosition()));

        // Event post for remove a Meeting
        pMeetingViewHolder.mImageButtonRemoveMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View pView) {
                EventBus.getDefault().post(new DeleteMeetingEvent(mMeetingList.get(pMeetingViewHolder.getAdapterPosition())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMeetingList.size();
    }
}
