package com.miun.applikation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeView(View view){
        switch (view.getId()){
            case(R.id.kalender):
                break;
            case(R.id.chatt):
                setContentView(R.layout.chatt);
                break;
            case(R.id.logg):
                break;
            case(R.id.goBack):
                setContentView(R.layout.activity_main);
        }
    }

    public void onClick(View view){
        //TextInputLayout tv = findViewById(R.id.text);
        //EditText text = tv.getEditText();
        ((TextView)findViewById(R.id.textView)).setText("changes");
    }
}