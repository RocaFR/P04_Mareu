package bryan.roca.mareu.utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

import bryan.roca.mareu.R;

public class RemoveMeetingAction implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.imageButton_removeMeeting);
        button.performClick();
    }
}
