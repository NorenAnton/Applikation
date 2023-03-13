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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TimePickerPopup extends DialogFragment {

    public interface DialogListener {
        void onDialogPositiveClick(String time, NewEvent.Caller caller);
    }
    private DialogListener dialogListener;
    private NewEvent.Caller caller;
    private NumberPicker numberPicker;
    private List<String> displayedValues;


    public TimePickerPopup(DialogListener dialogListener, NewEvent.Caller caller, int start){
        this.dialogListener = dialogListener;
        this.caller = caller;
        displayedValues = new ArrayList<>();
        int i = start;
        if (start == -1) {
            displayedValues.addAll(Arrays.asList("10:00", "11:00", "13:00", "14:00", "15:00", "16:00", "17:00"));
        }
        else if (start < 12) {
            while (i != 13) {
                if (i != start) {
                    displayedValues.add(i + ":00");
                }
                i++;
            }
        }
        else {
            while (i != 19) {
                if (i != start) {
                    displayedValues.add(i + ":00");
                }
                i++;
            }
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
        int sizeOfList = displayedValues.size() - 1;
        numberPicker.setMaxValue((sizeOfList != -1)? sizeOfList:0);

        String[] data = new String[displayedValues.size()];
        data = displayedValues.toArray(data);

        numberPicker.setDisplayedValues(data);


        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        String[] finalData = data;
        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Ok", (dialog, id) -> {
                    dialogListener.onDialogPositiveClick(finalData[numberPicker.getValue()], caller);
                })
                .setNegativeButton("Cancel", (dialog, id) -> {

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


/*

        for(int i = 10; i < 19; i++) {
            displayedValues[i - 10] = i + ":00";
        }
 */