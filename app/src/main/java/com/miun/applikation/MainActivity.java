package com.miun.applikation;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_Chatt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Chatt = findViewById(R.id.chatt);

        btn_Chatt.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Chat.class);
            startActivity(intent);
        });

    }


}