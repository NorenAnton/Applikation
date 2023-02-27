package com.miun.applikation.chat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miun.applikation.misc.CustomerListAdapter;
import com.miun.applikation.utils.HelperFunctions;
import com.miun.applikation.MainActivity;
import com.miun.applikation.R;
import com.miun.applikation.log.Log;
import com.miun.applikation.utils.ChatLogUtils;

public class Chat extends AppCompatActivity implements View.OnClickListener {

    Button btn_goBack, btn_goToLog, btn_submit;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        inputText = findViewById(R.id.inputText);
        ChatLogUtils.fillList();
        ChatLogUtils.fillChat();

        customerManager();
        chatManager();

        btn_goBack = findViewById(R.id.goBackChat);
        btn_goToLog = findViewById(R.id.logBtn);
        btn_submit = findViewById(R.id.submit);
        btn_goBack.setOnClickListener(this);
        btn_goToLog.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

    }


    public void customerManager(){
        RecyclerView chatView = findViewById(R.id.Customers);
        chatView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Chat.this);
        chatView.setLayoutManager(layoutManager);
        RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> mAdapter = new CustomerListAdapter(ChatLogUtils.users);
        chatView.setAdapter(mAdapter);
    }

    public void chatManager(){
        RecyclerView chat = findViewById(R.id.Chat);
        LinearLayoutManager chatManager = new LinearLayoutManager(Chat.this);
        chatManager.setStackFromEnd(true);
        chat.setLayoutManager(chatManager);
        RecyclerView.Adapter<ChatAdapter.ChatViewHolder> cAdapter = new ChatAdapter(ChatLogUtils.chatter);
        chat.setAdapter(cAdapter);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.goBackChat:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.logBtn:
                intent = new Intent(this, Log.class);
                startActivity(intent);
                break;
            case R.id.submit:
                String message = inputText.getText().toString();
                chatManager();

                if (!message.isEmpty()) {
                    ChatLogUtils.chatter.add(new CurrentChat(0, "Anders Martinsson", message));
                    inputText.getText().clear();
                    HelperFunctions.hideSoftKeyboard(this);
                } else {
                    inputText.setError("Empty Field!");
                }
                break;
        }
    }
}