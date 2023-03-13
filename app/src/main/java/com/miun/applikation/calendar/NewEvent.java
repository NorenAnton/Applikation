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
import com.miun.retrofit.models.CalenderModel;
import com.miun.retrofit.models.Person;
import com.miun.retrofit.retrofitClient;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

        ArrayAdapter<String> sAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new String[]{"Reservation", "Reparation", "Övrigt"});
        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(sAdapter);

        retrofitClient client = new InterfaceAPI(baseurl).createRetrofitClient();
        new RequestInterface<>(client.getAllPersons(), (List<Person> container)->{
            String[] customers = new String[0];
            for(Person p: container){
                customers = new String[]{p.getId() + ". " + p.getFname() + " " + p.getLname()};
            }
            Spinner customerSpinner = findViewById(R.id.eventCustomer);
            ArrayAdapter<String> cAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, customers);
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
        DateFormat formatter = new SimpleDateFormat("HH");

        String eventSubject = this.subjectSpinner.getSelectedItem().toString();
        String eventFreetext = eventFreetextET.getText().toString();
        String eventDate = eventDatePicker.getYear() + "-" + eventDatePicker.getMonth() + "-" + eventDatePicker.getDayOfMonth();
        String eventPerson = this.customerSpinner.getSelectedItem().toString();

        java.sql.Date eventDateFormat = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(eventDate);

        String[] tokens = eventPerson.split(".");
        int ID = Integer.parseInt(tokens[0]);
        java.sql.Time startTimeValue = new java.sql.Time(formatter.parse(startTime).getTime());
        java.sql.Time endTimeValue = new java.sql.Time(formatter.parse(endTime).getTime());
        new CalenderModel(0, startTimeValue, endTimeValue, eventDateFormat, eventDateFormat, eventSubject, eventFreetext, (int) Math.random(), ID);
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