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
import com.miun.retrofit.InterfaceAPI;
import com.miun.retrofit.RequestInterface;
import com.miun.retrofit.models.CalenderEventModel;
import com.miun.retrofit.models.Person;
import com.miun.retrofit.retrofitClient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;

public class NewEvent extends AppCompatActivity implements View.OnClickListener, TimePickerPopup.DialogListener {

    private EditText  eventFreetextET;
    private DatePicker eventDatePicker;
    private String startTime;
    private String endTime;
    Button btn_cancel;

    String baseurl = "http://10.82.227.191:8080/";

    Spinner subjectSpinner, customerSpinner;

    @Override
    public void onDialogPositiveClick(String time, Caller caller) {
        switch (caller){
            case startTime:
                this.startTime = time + ":00";
                ((TextView)findViewById(R.id.tv_startTime)).setText(time);
                ((TextView)findViewById(R.id.tv_endTime)).setText("");
                break;
            case endTime:
                this.endTime = time + ":00";
                ((TextView)findViewById(R.id.tv_endTime)).setText(time);
                break;
        }
    }

    public enum Caller {
        startTime, endTime
    }

    retrofitClient client = new InterfaceAPI(baseurl).createRetrofitClient();

    @SuppressLint({"SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event);
        initWidgets();

        ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new String[]{"Reservation", "Reparation", "Övrigt"});
        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(sAdapter);


        new RequestInterface<>(client.getAllPersons(), (List<Person> container)->{
            List<String> customerList = new ArrayList<String>();
            for(Person p: container){
                customerList.add(p.getId() + ". " + p.getFname() + " " + p.getLname());
            }

            Spinner customerSpinner = findViewById(R.id.eventCustomer);
            ArrayAdapter<String> cAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, customerList);
            cAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            customerSpinner.setAdapter(cAdapter);

        });


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
        customerSpinner = findViewById(R.id.eventCustomer);
        eventFreetextET = findViewById(R.id.eventFreetextET);
        eventDatePicker = findViewById(R.id.eventDatePicker);
    }

    public void saveEventAction(View view) throws ParseException {

        String eventSubject = this.subjectSpinner.getSelectedItem().toString();
        String eventFreetext = eventFreetextET.getText().toString();

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //String dateString = String.format("%d-%d-%d", eventDatePicker.getYear(), (eventDatePicker.getMonth() + 1), eventDatePicker.getDayOfMonth());
        //sdf.format(dateString);
        //String eventDate = eventDatePicker.getYear() + "-" + eventDatePicker.getMonth() + "-" + eventDatePicker.getDayOfMonth();
        String eventPerson = this.customerSpinner.getSelectedItem().toString();

        String dateString = String.format("%d-%d-%d", eventDatePicker.getYear(), (eventDatePicker.getMonth() + 1), eventDatePicker.getDayOfMonth());
        Date date = null;
        String dayOfWeek;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
            dayOfWeek = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String[] tokens = eventPerson.split("\\.");
        Integer ID = Integer.parseInt(tokens[0]);
        CalenderEventModel newCalendarEvent = new CalenderEventModel(-1, startTime, endTime, dayOfWeek, dayOfWeek, eventSubject, eventFreetext, -1, ID);

        new RequestInterface<>(client.addCalenderEvent(newCalendarEvent),(CalenderEventModel container)->{});

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