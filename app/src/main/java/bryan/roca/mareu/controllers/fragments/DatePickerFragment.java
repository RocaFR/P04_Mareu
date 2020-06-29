package bryan.roca.mareu.controllers.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Mareu - bryan.roca.mareu
 * 12/06/20
 * Author Bryan Ferreras-Roca
 */
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private OnDateChangeListener mCallBack;

    public interface OnDateChangeListener {
        void onDateChange(DatePicker pDatePicker, int pI, int pI1, int pI2);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        this.createCallBack();

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker pDatePicker, int pI, int pI1, int pI2) {
        mCallBack.onDateChange(pDatePicker, pI, pI1, pI2);
    }

    private void createCallBack() {
        try {
            mCallBack = (OnDateChangeListener) getActivity();
        } catch (ClassCastException pE) {
            throw new ClassCastException(getEnterTransition().toString());
        }
    }
}
