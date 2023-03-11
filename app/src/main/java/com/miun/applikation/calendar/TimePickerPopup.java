package com.miun.applikation.calendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.miun.applikation.R;

public class TimePickerPopup extends DialogFragment {

    public interface DialogListener {
        void onDialogPositiveClick(String time, NewEvent.Caller caller);
    }
    DialogListener dialogListener;
    NewEvent.Caller caller;
    NumberPicker numberPicker;

    String[] displayedValues;

    public TimePickerPopup(DialogListener dialogListener, NewEvent.Caller caller){
        this.dialogListener = dialogListener;
        this.caller = caller;
        this.displayedValues = new String[9];
        for(int i = 10; i < 19; i++){
            displayedValues[i-10] = i +":00";
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.calendar_time_select_popup, null);
        numberPicker = view.findViewById(R.id.np_picker);

        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(8);

        numberPicker.setDisplayedValues(displayedValues);


        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Ok", (dialog, id) -> {
                    dialogListener.onDialogPositiveClick(displayedValues[numberPicker.getValue()], caller);
                })
                .setNegativeButton("Avbryt", (dialog, id) -> {

                })
                .create();

        AlertDialog alertDialog = builder.show();

        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);

        // Set the text color of the buttons to black
        positiveButton.setTextColor(Color.BLACK);
        negativeButton.setTextColor(Color.BLACK);

        return alertDialog;
    }
}
