package com.miun.applikation.calendar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.miun.applikation.R;

import org.w3c.dom.Text;

public class NewEvent extends AppCompatActivity implements View.OnClickListener, TimePickerPopup.DialogListener {

    private EditText  eventFreetextET;
    private DatePicker eventDatePicker;
    private String startTime;
    private String endTime;
    Button btn_cancel;

    Spinner subjectSpinner;

    @Override
    public void onDialogPositiveClick(String time, Caller caller) {
        switch (caller){
            case startTime:
                this.startTime = time;
                ((TextView)findViewById(R.id.tv_startTime)).setText(time);
                ((TextView)findViewById(R.id.tv_endTime)).setText("");
                break;
            case endTime:
                this.endTime = time;
                ((TextView)findViewById(R.id.tv_endTime)).setText(time);
                break;
        }
    }

    public enum Caller {
        startTime, endTime
    }

    @SuppressLint({"SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event);
        initWidgets();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new String[]{"Reservation", "Reparation", "Övrigt"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(adapter);

        btn_cancel = findViewById(R.id.cancelEvent);
        btn_cancel.setOnClickListener(this);

        findViewById(R.id.tv_startTime).setOnClickListener(view -> {
            showTimeDialog(Caller.startTime);
        });
        findViewById(R.id.tv_endTime).setOnClickListener(view -> {
            showTimeDialog(Caller.endTime);
        });


    }

    private void showTimeDialog(Caller caller) {
        if (caller.equals(Caller.endTime) && this.startTime == null)
            return;
        TimePickerPopup timePickerPopup = new TimePickerPopup(this, caller, (startTime != null && !caller.equals(Caller.startTime))? Integer.parseInt(startTime.substring(0, startTime.indexOf(":"))) : -1);
        timePickerPopup.show(getSupportFragmentManager(), "TimePopup");
    }

    private void initWidgets()
    {
        subjectSpinner = findViewById(R.id.eventSubject);
        eventFreetextET = findViewById(R.id.eventFreetextET);
        eventDatePicker = findViewById(R.id.eventDatePicker);
    }

    public void saveEventAction(View view)
    {
        String eventSubject = this.subjectSpinner.getSelectedItem().toString();
        String eventFreetext = eventFreetextET.getText().toString();
        String eventDate = eventDatePicker.getYear() + "-" + eventDatePicker.getMonth() + "-" + eventDatePicker.getDayOfMonth();
        //LocalTime eventTime = LocalTime.parse(eventTimePicker.getHour() + ":" + eventTimePicker.getMinute());
       // Event newEvent = new Event(eventSubject, eventFreetext, eventDate, null);
        //Event.eventsList.add(newEvent);
        finish();
    }

    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.cancelEvent:
                intent = new Intent(this, Calendar.class);
                startActivity(intent);
                break;
        }
    }
}