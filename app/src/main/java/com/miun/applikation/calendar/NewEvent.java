package com.miun.applikation.calendar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.miun.applikation.R;
import com.miun.applikation.utils.CalendarUtils;

import java.time.LocalTime;

public class NewEvent extends AppCompatActivity implements View.OnClickListener{

    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;
    private LocalTime time;
    Button btn_cancel;

    @SuppressLint({"SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event);
        initWidgets();
        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        eventTimeTV.setText("Time: " + CalendarUtils.formattedTime(time));

        btn_cancel = findViewById(R.id.cancelEvent);
        btn_cancel.setOnClickListener(this);
    }

    private void initWidgets()
    {
        eventNameET = findViewById(R.id.eventNameET);
        eventDateTV = findViewById(R.id.eventDateTV);
        eventTimeTV = findViewById(R.id.eventTimeTV);
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