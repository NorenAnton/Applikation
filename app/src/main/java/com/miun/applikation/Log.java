package com.miun.applikation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Log extends AppCompatActivity {

    Fillers fillers = new Fillers();
    Button btn_goBack, btn_goToChat;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);

        inputText = (EditText) findViewById(R.id.inputText);
        fillers.fillList();

        customersManager();
        logManager();

        btn_goBack = findViewById(R.id.goBack);
        btn_goToChat = findViewById(R.id.chatBtn);

        btn_goBack.setOnClickListener(view -> {
            Intent intentBack = new Intent(this, MainActivity.class);
            startActivity(intentBack);
        });

        btn_goToChat.setOnClickListener(view -> {
            Intent intentChat = new Intent(this, Chat.class);
            startActivity(intentChat);
        });
    }

    private void customersManager() {
        RecyclerView logView = findViewById(R.id.Customers);
        logView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Log.this);
        logView.setLayoutManager(layoutManager);
        RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> mAdapter = new CustomerListAdapter(fillers.users);
        logView.setAdapter(mAdapter);
    }

    private void logManager(){
        RecyclerView log = findViewById(R.id.log);
        LinearLayoutManager chatManager = new LinearLayoutManager(Log.this);
        chatManager.setStackFromEnd(true);
        log.setLayoutManager(chatManager);
        RecyclerView.Adapter<ChatAdapter.ChatViewHolder> cAdapter = new ChatAdapter(fillers.chatter);
        log.setAdapter(cAdapter);
    }
}