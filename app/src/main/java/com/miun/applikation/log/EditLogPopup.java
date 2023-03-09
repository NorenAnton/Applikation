package com.miun.applikation.log;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.miun.applikation.R;

public class EditLogPopup extends DialogFragment {

    public interface DialogListener {
        void onDialogPositiveClick(TextView textView, int position);
    }
    private final TextView textView;
    private final int position;
    private final DialogListener dialogListener;

    public EditLogPopup(TextView textView, int position, DialogListener dialogListener){
        this.textView = textView;
        this.position = position;
        this.dialogListener = dialogListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.edit_log_dialog, null);

        TextView textView1 = view.findViewById(R.id.tv_msgText);
        textView1.setText(textView.getText().toString());

        builder.setView(view)
                // Add action buttons
                .setPositiveButton("Ändra", (dialog, id) -> dialogListener.onDialogPositiveClick(textView1, position))
                .setNegativeButton("Avbryt", (dialog, id) -> {});
        return builder.create();
    }






}
