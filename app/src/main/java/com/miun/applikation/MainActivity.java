package com.miun.applikation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_Chatt, btn_Log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Chatt = findViewById(R.id.chat);
        btn_Log = findViewById(R.id.logg);

        btn_Chatt.setOnClickListener(view -> {
            Intent intentChat = new Intent(MainActivity.this, Chat.class);
            startActivity(intentChat);
        });

        btn_Log.setOnClickListener(view -> {
            Intent intentLog = new Intent(MainActivity.this, log.class);
            startActivity(intentLog);
        });

    }


}