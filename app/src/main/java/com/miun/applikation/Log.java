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

public class Log extends AppCompatActivity implements View.OnClickListener {

    Fillers fillers = new Fillers();
    Button btn_goBack, btn_goToChat, btn_submit;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);

        inputText = (EditText) findViewById(R.id.inputText);
        fillers.fillList();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Log.this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> mAdapter = new CustomerListAdapter(fillers.users);
        recyclerView.setAdapter(mAdapter);


        RecyclerView chat = findViewById(R.id.log);
        chat.setHasFixedSize(true);
        RecyclerView.LayoutManager chatManager = new LinearLayoutManager(Log.this);
        chat.setLayoutManager(chatManager);
        RecyclerView.Adapter<ChatAdapter.ChatViewHolder> cAdapter = new ChatAdapter(fillers.chatter);
        chat.setAdapter(cAdapter);


        btn_goBack = findViewById(R.id.goBack);
        btn_goToChat = findViewById(R.id.chatBtn);
        btn_submit = findViewById(R.id.submit);
        btn_goBack.setOnClickListener(this);
        btn_goToChat.setOnClickListener(this);
        btn_submit.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.goBack:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.chatBtn:
                intent = new Intent(this, Chat.class);
                startActivity(intent);
            case R.id.submit:
                break;
        }
    }
}