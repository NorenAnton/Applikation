package com.miun.applikation.calendar;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.net.ParseException;

import com.miun.applikation.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddEventActivity extends AppCompatActivity {

    private EditText mTitleEditText;
    private EditText mDescriptionEditText;
    private TextView mDateTextView;
    private TextView mStartTimeTextView;
    private TextView mEndTimeTextView;
    private Button mSaveButton;

    private Calendar mCalendar;

    public static void start(Context context) {
        Intent intent = new Intent(context, AddEventActivity.class);
        context.startActivity(intent);
    }

    public static void start(Context context, long eventId) {
        Intent intent = new Intent(context, AddEventActivity.class);
        intent.putExtra("eventId", eventId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        mTitleEditText = findViewById(R.id.titleEditText);
        mDescriptionEditText = findViewById(R.id.descriptionEditText);
        mDateTextView = findViewById(R.id.dateTextView);
        mStartTimeTextView = findViewById(R.id.startTimeTextView);
        mEndTimeTextView = findViewById(R.id.endTimeTextView);
        mSaveButton = findViewById(R.id.saveButton);

        mCalendar = Calendar.getInstance();

        mDateTextView.setOnClickListener(v -> showDatePicker());
        mStartTimeTextView.setOnClickListener(v -> showTimePicker(mStartTimeTextView));
        mEndTimeTextView.setOnClickListener(v -> showTimePicker(mEndTimeTextView));

        mSaveButton.setOnClickListener(v -> saveEvent());
    }

    private void showDatePicker() {
        DatePickerDialog.OnDateSetListener listener = (view, year, month, dayOfMonth) -> {
            mCalendar.set(Calendar.YEAR, year);
            mCalendar.set(Calendar.MONTH, month);
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDateLabel();
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                R.style.DatePickerDialogStyle, listener,
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    private void showTimePicker(final TextView textView) {
        TimePickerDialog.OnTimeSetListener listener = (view, hourOfDay, minute) -> {
            mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mCalendar.set(Calendar.MINUTE, minute);
            updateTimeLabel(textView);
        };

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                R.style.TimePickerDialogStyle, listener,
                mCalendar.get(Calendar.HOUR_OF_DAY),
                mCalendar.get(Calendar.MINUTE), true);

        timePickerDialog.show();
    }

    private void updateDateLabel() {
        String dateFormat = "EEE, MMM dd yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat, Locale.getDefault());
        mDateTextView.setText(sdf.format(mCalendar.getTime()));
    }

    private void updateTimeLabel(TextView textView) {
        String timeFormat = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat, Locale.getDefault());
        textView.setText(sdf.format(mCalendar.getTime()));
    }

    private long getDateInMillis() {
        mCalendar.set(Calendar.SECOND, 0);
        mCalendar.set(Calendar.MILLISECOND, 0);
        return mCalendar.getTimeInMillis();
    }

    private void saveEvent() {
        String title = mTitleEditText.getText().toString().trim();
        String description = mDescriptionEditText.getText().toString().trim();
        long start = getDateInMillis() + getTimeInMillis(mStartTimeTextView);
        long end = getDateInMillis() + getTimeInMillis(mEndTimeTextView);

        Event event = new Event(title, description, start, end);
        EventRepository.getInstance().addEvent(event);

        finish();
    }
    private long getTimeInMillis(int year, int month, int dayOfMonth, int hourOfDay, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    private void saveEvent() {
        // Get the input fields from the layout
        EditText titleEditText = findViewById(R.id.eventTitleEditText);
        EditText descriptionEditText = findViewById(R.id.eventDescriptionEditText);
        EditText locationEditText = findViewById(R.id.eventLocationEditText);
        EditText startTimeEditText = findViewById(R.id.eventStartTimeEditText);
        EditText endTimeEditText = findViewById(R.id.eventEndTimeEditText);

        // Get the values from the input fields
        String title = titleEditText.getText().toString();
        String description = descriptionEditText.getText().toString();
        String location = locationEditText.getText().toString();
        String startTimeString = startTimeEditText.getText().toString();
        String endTimeString = endTimeEditText.getText().toString();

        // Convert the start and end time strings to Calendar objects
        Calendar startTimeCalendar = Calendar.getInstance();
        try {
            startTimeCalendar.setTime(sdf.parse(startTimeString));
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        Calendar endTimeCalendar = Calendar.getInstance();
        try {
            endTimeCalendar.setTime(sdf.parse(endTimeString));
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        // Get the start and end times in milliseconds
        long startTimeMillis = startTimeCalendar.getTimeInMillis();
        long endTimeMillis = endTimeCalendar.getTimeInMillis();

        // Create a new event object with the input values
        Event event = new Event(title, startTimeMillis, endTimeMillis, description);

        // Add the new event to the repository
        EventRepository.getInstance().addEvent(event);

        // Finish the activity
        finish();
    }