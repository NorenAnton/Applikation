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

import java.time.LocalDate;

public class Calendar extends AppCompatActivity implements View.OnClickListener{

    CalendarView calendar;
    TextView test;
    Button btn_goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        calendar = findViewById(R.id.calendarView);
        test = findViewById(R.id.textView);

        btn_goBack = findViewById(R.id.goBackCalendar);
        btn_goBack.setOnClickListener(this);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
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
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.goBackCalendar:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
        }
    }
}