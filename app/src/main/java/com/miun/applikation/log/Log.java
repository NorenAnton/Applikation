package com.miun.applikation.log;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miun.applikation.chat.Chat;
import com.miun.applikation.misc.CustomerListAdapter;
import com.miun.applikation.utils.HelperFunctions;
import com.miun.applikation.MainActivity;
import com.miun.applikation.R;
import com.miun.applikation.utils.ChatLogUtils;

public class Log extends AppCompatActivity implements View.OnClickListener {

    ChatLogUtils fillers = new ChatLogUtils();

    Button btn_goBack, btn_goToChat, btn_submit;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);

        inputText = (EditText) findViewById(R.id.inputText);
        fillers.fillList();
        fillers.fillLog();

        customersLayoutManager();
        logLayoutManager();

        btn_goBack = findViewById(R.id.goBackLog);
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
            case R.id.goBackLog:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.chatBtn:
                intent = new Intent(this, Chat.class);
                startActivity(intent);
                break;
            case R.id.submit:
                String message = inputText.getText().toString();
                logLayoutManager();

                if (!message.isEmpty()) {
                    fillers.logger.add(new CurrentLog(0, "Anders Martinsson", message));
                    inputText.getText().clear();
                    HelperFunctions.hideSoftKeyboard(this);
                } else {
                    inputText.setError("Empty Field!");
                }
                break;
        }
    }

    private void customersLayoutManager() {
        RecyclerView logView = findViewById(R.id.Customers);
        logView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Log.this);
        logView.setLayoutManager(layoutManager);
        RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> mAdapter = new CustomerListAdapter(fillers.users);
        logView.setAdapter(mAdapter);
    }

    private void logLayoutManager(){
        RecyclerView log = findViewById(R.id.log);
        LinearLayoutManager logManager = new LinearLayoutManager(Log.this);
        logManager.setStackFromEnd(true);
        log.setLayoutManager(logManager);
        RecyclerView.Adapter<LogAdapter.LogViewHolder> logAdapter = new LogAdapter(fillers.logger);
        log.setAdapter(logAdapter);
    }
}