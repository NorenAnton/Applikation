package com.miun.applikation.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.miun.applikation.MainActivity;
import com.miun.applikation.R;
import com.miun.retrofit.InterfaceAPI;
import com.miun.retrofit.RequestInterface;
import com.miun.retrofit.retrofitClient;
import com.miun.retrofit.models.CalenderEventModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Calendar extends AppCompatActivity implements View.OnClickListener{
    CalendarView calendar;
    TextView today;
    Button btn_goBack, btn_newEvent;

    String baseurl = "http://10.82.227.191:8080/";

    retrofitClient client = new InterfaceAPI(baseurl).createRetrofitClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        calendar = findViewById(R.id.calendarView);
        today = findViewById(R.id.today);

        String dateTime;
        SimpleDateFormat sdf = new SimpleDateFormat("EEE yyyy-MM-dd", Locale.getDefault());
        dateTime = sdf.format(new Date());
        today.setText(dateTime);

        HourLayoutManager();

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
                     int dayOfMonth) {
                 String dateString = String.format("%d-%d-%d", year, (month + 1), dayOfMonth);
                 Date date = null;
                 String dayOfWeek;
                 try {
                     date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                     dayOfWeek = new SimpleDateFormat("EEE yyyy-MM-dd", Locale.ENGLISH).format(date);
                 } catch (ParseException e) {
                     throw new RuntimeException(e);
                 }

                 today.setText(dayOfWeek);
                 HourLayoutManager();
             }
        });
    }

    public void HourLayoutManager(){
        new RequestInterface<>(client.getAllCalenderEvent(), (List<CalenderEventModel> container)->{
            RecyclerView dayView = findViewById(R.id.hourRecyclerView);
            LinearLayoutManager hourManager = new LinearLayoutManager(Calendar.this, LinearLayoutManager.HORIZONTAL, false);
            dayView.setLayoutManager(hourManager);
            RecyclerView.Adapter<HourAdapter.HourViewHolder> hAdapter = new HourAdapter(this, getSupportFragmentManager(), hourEventList(container), today.getText().toString());
            dayView.setAdapter(hAdapter);
        });
    }

    private ArrayList<HourEvent> hourEventList(List<CalenderEventModel> container, String s)
    {
        ArrayList<HourEvent> list = new ArrayList<>();
        String[] selectedDate = s.split(" ");


        for(int hour = 10; hour < 19; hour++)
        {
            Integer counter = 0;
            LocalTime beginTime = LocalTime.of(hour, 0,0);
            LocalTime endTime = LocalTime.of(hour + 1, 0,0);
            if(container != null) {
                for (CalenderEventModel c : container) {
                    if(c.getStartDate().equals(selectedDate[1]) &&
                            (LocalTime.parse(c.getStartTime()).equals(beginTime) ||
                                    (LocalTime.parse(c.getStartTime()).isBefore(endTime) && LocalTime.parse(c.getStopTime()).isAfter(beginTime)))) {

                        LocalTime startTime = LocalTime.parse(c.getStartTime());
                        LocalTime stopTime = LocalTime.parse(c.getStopTime());

                        list.add(new HourEvent(c.getPersonId(), c.getSubject(), c.getFreeText(), c.getStartDate(), startTime, stopTime));
                        counter++;
                    }
                }
                if(counter == 0){
                    list.add(new HourEvent(0, null, null, null, null, null));
                }
            }
        }
        return list;
    }

    private void loadEvent() {

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