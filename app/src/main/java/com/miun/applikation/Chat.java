package com.miun.applikation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

        customerManager();
        chatManager();

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

    public void customerManager(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Chat.this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> mAdapter = new CustomerListAdapter(fillers.users);
        recyclerView.setAdapter(mAdapter);
    }

    public void chatManager(){
        RecyclerView chat = findViewById(R.id.chat);
        LinearLayoutManager chatManager = new LinearLayoutManager(Chat.this);
        chatManager.setStackFromEnd(true);
        chat.setLayoutManager(chatManager);
        RecyclerView.Adapter<ChatAdapter.ChatViewHolder> cAdapter = new ChatAdapter(fillers.chatter);
        chat.setAdapter(cAdapter);
    }

    public void onClick(View view){
        String message = inputText.getText().toString();
        chatManager();

        fillers.chatter.add(new CurrentChat(0, "Anders", message));
        inputText.getText().clear();
        hideSoftKeyboard(this);
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if(inputMethodManager.isAcceptingText()){
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }
}
