package com.miun.applikation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {

    Fillers fillers = new Fillers();
    Button btn_goBack, btn_goToLog;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatt);

        inputText = (EditText) findViewById(R.id.inputText);
        fillers.fillList();
        fillers.fillChat();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Chat.this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> mAdapter = new CustomerListAdapter(fillers.users);
        recyclerView.setAdapter(mAdapter);


        RecyclerView chat = findViewById(R.id.chat);
        chat.setHasFixedSize(true);
        RecyclerView.LayoutManager chatManager = new LinearLayoutManager(Chat.this);
        chat.setLayoutManager(chatManager);
        RecyclerView.Adapter<ChatAdapter.ChatViewHolder> cAdapter = new ChatAdapter(fillers.chatter);
        chat.setAdapter(cAdapter);


        btn_goBack = findViewById(R.id.goBack);
        btn_goToLog = findViewById(R.id.logBtn);

        btn_goBack.setOnClickListener(view -> {
            Intent intentBack = new Intent(this, MainActivity.class);
            startActivity(intentBack);
        });

        btn_goToLog.setOnClickListener(view -> {
            Intent intentLog = new Intent(this, Log.class);
            startActivity(intentLog);
        });

    }

    public void onClick(View view){

    }


}
