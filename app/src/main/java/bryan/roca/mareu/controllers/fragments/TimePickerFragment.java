package bryan.roca.mareu.controllers.fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private OnTimeSetListener mCallBack;

    public interface OnTimeSetListener {
        void onTimeSet(TimePicker pTimePicker, int pI, int pI1);
    }

    public TimePickerFragment() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        this.createCallBack();

        return new TimePickerDialog(getActivity(),this, hour, minute, true);
    }

    @Override
    public void onTimeSet(TimePicker pTimePicker, int pI, int pI1) {
        mCallBack.onTimeSet(pTimePicker, pI, pI1);
    }

    private void createCallBack() {
        try {
            mCallBack = (OnTimeSetListener) getActivity();
        } catch (ClassCastException pE) {
            throw new ClassCastException(pE.toString());
        }
    }
}