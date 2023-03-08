package com.miun.applikation.calendar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.miun.applikation.R;
import com.miun.applikation.utils.CalendarUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NewEvent extends AppCompatActivity implements View.OnClickListener{

    private EditText eventNameET;
    private DatePicker eventDatePicker;
    private TimePicker eventTimePicker;
    private SimpleDateFormat time;
    Button btn_cancel;

    @SuppressLint({"SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event);
        initWidgets();

        btn_cancel = findViewById(R.id.cancelEvent);
        btn_cancel.setOnClickListener(this);
    }

    private void initWidgets()
    {
        eventNameET = findViewById(R.id.eventNameET);
        eventDatePicker = findViewById(R.id.eventDatePicker);
        eventTimePicker = findViewById(R.id.eventTimePicker);
    }

    public void saveEventAction(View view)
    {
        String eventName = eventNameET.getText().toString();
        Event newEvent = new Event(eventName, CalendarUtils.selectedDate, time);
        Event.eventsList.add(newEvent);
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