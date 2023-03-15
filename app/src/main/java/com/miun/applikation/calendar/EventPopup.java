package com.miun.applikation.calendar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.miun.applikation.R;
import com.miun.applikation.log.Log;

public class EventPopup extends DialogFragment {
    private final HourEvent event;

    public EventPopup(HourEvent event) {
        this.event = event;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.inspect_event_popup, null);

        TextView subject = view.findViewById(R.id.tv_eventSubject);
        TextView customer = view.findViewById(R.id.tv_eventCustomer);
        TextView time = view.findViewById(R.id.tv_eventTime);
        TextView description = view.findViewById(R.id.tv_eventDesc);

        subject.setText(event.getSubject());
        customer.setText("Kund: ");
        time.setText("Time: "+ event.getStartEndTime());
        description.setText(event.getFreetext());

        builder.setView(view)
                .setPositiveButton("Go to chat", (dialog, id) -> {
                    Intent intent = new Intent(getContext(), Log.class);
                    intent.putExtra("name", "");
                    intent.putExtra("id", event.getPersonID());
                    startActivity(intent);
                })
                .setNeutralButton("Close", (dialog, id) -> {
                    dialog.cancel();
                })
                .setNegativeButton("Delete", (dialog, id) -> {

                })
                .create();

        AlertDialog alertDialog = builder.show();

        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        Button neutralButton = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);

        // Set the text color of the buttons to black
        positiveButton.setTextColor(Color.BLACK);
        negativeButton.setTextColor(Color.BLACK);
        neutralButton.setTextColor(Color.BLACK);

        return alertDialog;
    }
}
