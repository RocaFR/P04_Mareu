package bryan.roca.mareu.controllers.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bryan.roca.mareu.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddAParticipantFragmentDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddAParticipantFragmentDialog extends DialogFragment {

    public AddAParticipantFragmentDialog() {
        // Required empty public constructor
    }

    public static AddAParticipantFragmentDialog newInstance() {
        AddAParticipantFragmentDialog fragment = new AddAParticipantFragmentDialog();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("lol", "newInstance: coucou");
        return inflater.inflate(R.layout.fragment_add_a_participant, container, false);
    }
}