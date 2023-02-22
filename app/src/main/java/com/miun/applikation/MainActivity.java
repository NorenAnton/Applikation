package com.miun.applikation;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_Chatt, btn_Log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Chatt = findViewById(R.id.chat);
        btn_Log = findViewById(R.id.logg);
        btn_Chatt.setOnClickListener(this);
        btn_Log.setOnClickListener(this);

    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.chat:
                intent = new Intent(this, Chat.class);
                startActivity(intent);
                break;
            case R.id.logg:
                intent = new Intent(this, Log.class);
                startActivity(intent);
                break;

        }
    }
}