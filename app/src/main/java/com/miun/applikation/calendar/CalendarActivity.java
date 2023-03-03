package com.miun.applikation.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.miun.applikation.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity implements CalendarView.OnDateChangeListener {

    private CalendarView mCalendarView;
    private TextView mDateTextView;
    private RecyclerView mRecyclerView;
    private DailyViewAdapter mAdapter;
    private List<Event> mEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        mCalendarView = findViewById(R.id.calendarView);
        mDateTextView = findViewById(R.id.textView);
        mRecyclerView = findViewById(R.id.recyclerView);

        mEvents = new ArrayList<>();

        // Set up the recycler view
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DailyViewAdapter( mEvents);
        mRecyclerView.setAdapter(mAdapter);

        // Set up the calendar view
        mCalendarView.setOnDateChangeListener(this);
        mCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
        // Update the date text view
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.set(year, month, dayOfMonth);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault());
        mDateTextView.setText(sdf.format(selectedDate.getTime()));

        // Update the events for the selected date
        List<Event> eventsForDate = getEventsForDate(selectedDate);
        mAdapter.setEvents(eventsForDate);
    }

    private List<Event> getEventsForDate(Calendar date) {
        List<Event> eventsForDate = new ArrayList<>();
        for (Event event : mEvents) {
            if (event.getStartTime().get(Calendar.YEAR) == date.get(Calendar.YEAR) &&
                    event.getStartTime().get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR)) {
                eventsForDate.add(event);
            }
        }
        return eventsForDate;
    }

    public void addEvent(View view) {
        // Launch the AddEventActivity to add a new event
        AddEventActivity.start(this);
    }

    public void editEvent(Event event) {
        // Launch the AddEventActivity to edit an existing event
        AddEventActivity.start(this, event.getId());
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Reload the events when the activity is resumed
        mEvents = EventRepository.getInstance().getEvents();
        mAdapter.setEvents(mEvents);
    }
}
    /*@Override
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
}*/