package com.miun.applikation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.miun.applikation.calendar.Calendar;
import com.miun.applikation.chat.Chat;
import com.miun.applikation.log.Log;
import com.miun.retrofit.InterfaceAPI;
import com.miun.retrofit.RequestInterface;
import com.miun.retrofit.models.CalenderEventModel;
import com.miun.retrofit.retrofitClient;

import java.time.LocalDate;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_Chat, btn_Log, btn_Calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocalDate.now();

        String baseurl = "http://10.82.227.191:8080/";

        retrofitClient client = new InterfaceAPI(baseurl).createRetrofitClient();

        new RequestInterface<>(client.getAllCalenderEvent(), (List<CalenderEventModel> container)->{
            System.out.println("hej");
        });

        btn_Calendar = findViewById(R.id.Calendar);
        btn_Chat = findViewById(R.id.Chat);
        btn_Log = findViewById(R.id.Log);
        btn_Calendar.setOnClickListener(this);
        btn_Chat.setOnClickListener(this);
        btn_Log.setOnClickListener(this);
    }



    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.Calendar:
                intent = new Intent(this, Calendar.class);
                startActivity(intent);
                break;
            case R.id.Chat:
                intent = new Intent(this, Chat.class);
                startActivity(intent);
                break;
            case R.id.Log:
                intent = new Intent(this, Log.class);
                startActivity(intent);
                break;
        }
    }
}