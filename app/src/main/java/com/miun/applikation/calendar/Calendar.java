package com.miun.applikation.calendar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;

import com.miun.applikation.MainActivity;
import com.miun.applikation.R;
import com.miun.applikation.chat.Chat;
import com.miun.applikation.chat.ChatAdapter;
import com.miun.applikation.utils.CalendarUtils;
import com.miun.applikation.utils.ChatLogUtils;
import com.miun.retrofit.InterfaceAPI;
import com.miun.retrofit.RequestInterface;
import com.miun.retrofit.retrofitClient;
import com.miun.retrofit.models.CalenderModel;

import java.text.DateFormat;
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
    Context context;
    List<HourEvent> hourEvents;

    String baseurl = "http://10.82.227.191:8080/";

    retrofitClient client = new InterfaceAPI(baseurl).createRetrofitClient();

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
                     date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
                     dayOfWeek = new SimpleDateFormat("EEE yyyy-M-d", Locale.ENGLISH).format(date);
                 } catch (ParseException e) {
                     throw new RuntimeException(e);
                 }

                 today.setText(dayOfWeek);
             }
        });
    }

    public void HourLayoutManager(){
        //new RequestInterface<>(client.getAllCalenderEvent(), (List<CalenderModel> container))
        RecyclerView dayView = findViewById(R.id.hourRecyclerView);
        LinearLayoutManager hourManager = new LinearLayoutManager(Calendar.this, LinearLayoutManager.HORIZONTAL, false);
        dayView.setLayoutManager(hourManager);
        RecyclerView.Adapter<HourAdapter.HourViewHolder> hAdapter = new HourAdapter(context, hourEventList());
        dayView.setAdapter(hAdapter);
    }

    private ArrayList<HourEvent> hourEventList()
    {
        ArrayList<HourEvent> list = new ArrayList<>();

        for(int hour = 10; hour < 19; hour++)
        {
            LocalTime startTime = LocalTime.of(hour, 0);
            LocalTime endTime = LocalTime.of((hour+1), 0);
            HourEvent hourEvent = new HourEvent(null, null, null, null, startTime, endTime);
            list.add(hourEvent);
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