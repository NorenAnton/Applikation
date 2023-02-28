package com.miun.applikation.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.miun.applikation.MainActivity;
import com.miun.applikation.R;
import com.miun.applikation.log.Log;
import com.miun.applikation.utils.CalendarUtils;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Calendar extends AppCompatActivity implements View.OnClickListener{

    CalendarView calendar;
    TextView today;
    Button btn_goBack, btn_newEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        calendar = findViewById(R.id.calendarView);
        today = findViewById(R.id.today);

        String dateTime;
        SimpleDateFormat sdf = new SimpleDateFormat("EEE yyyy-M-dd", Locale.getDefault());
        dateTime = sdf.format(new Date());
        today.setText(dateTime);

        btn_goBack = findViewById(R.id.goBackCalendar);
        btn_goBack.setOnClickListener(this);

        btn_newEvent = findViewById(R.id.newEvent);
        btn_newEvent.setOnClickListener(this);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
             @Override
             public void onSelectedDayChange(
                     @NonNull CalendarView view,
                     int year,
                     int month,
                     int dayOfMonth)
             {
                 String Date = year + "-" + (month + 1) + "-" + dayOfMonth;


                 today.setText(Date);
             }
        });
    }

    private ArrayList<HourEvent> hourEventList()
    {
        ArrayList<HourEvent> list = new ArrayList<>();

        for(int hour = 0; hour < 24; hour++)
        {
            LocalTime time = LocalTime.of(hour, 0);
            ArrayList<Event> events = Event.eventsForDateAndTime(CalendarUtils.selectedDate, time);
            HourEvent hourEvent = new HourEvent(time, events);
            list.add(hourEvent);
        }

        return list;
    }

    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.goBackCalendar:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.newEvent:
                intent = new Intent(this, NewEvent.class);
                startActivity(intent);
                break;
        }
    }
}