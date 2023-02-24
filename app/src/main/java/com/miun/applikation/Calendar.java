package com.miun.applikation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.time.LocalDate;

public class Calendar extends AppCompatActivity{

    CalendarView kalender;
    TextView test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        kalender = findViewById(R.id.calendarView);
        test = findViewById(R.id.textView);

        kalender.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
             @Override
             public void onSelectedDayChange(
                     @NonNull CalendarView view,
                     int year,
                     int month,
                     int dayOfMonth)
             {
                 String Date = year + "-" + (month + 1) + "-" + dayOfMonth;


                 test.setText(Date);
             }
        });
    }
}