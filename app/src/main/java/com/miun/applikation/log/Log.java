package com.miun.applikation.log;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miun.applikation.chat.Chat;
import com.miun.applikation.chat.ChatAdapter;
import com.miun.applikation.chat.CurrentChat;
import com.miun.applikation.misc.CustomerListAdapter;
import com.miun.applikation.misc.User;
import com.miun.applikation.utils.HelperFunctions;
import com.miun.applikation.MainActivity;
import com.miun.applikation.R;
import com.miun.applikation.utils.ChatLogUtils;
import com.miun.retrofit.InterfaceAPI;
import com.miun.retrofit.models.Message;
import com.miun.retrofit.models.Person;
import com.miun.retrofit.retrofitClient;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Log extends AppCompatActivity implements View.OnClickListener {

    ChatLogUtils fillers = new ChatLogUtils();
    Button btn_goBack, btn_goToChat, btn_submit, btn_imagePicker;
    EditText inputText;
    Uri image = null;
    TextView name, id;
    RecyclerView customerList, log;

    String baseurl = "http://10.82.227.191:8080/";

    retrofitClient client = new InterfaceAPI(baseurl).createRetrofitClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);

        inputText = (EditText) findViewById(R.id.inputText);
        customerList = findViewById(R.id.Customers);
        name = findViewById(R.id.name);
        id = findViewById(R.id.id);
        fillers.fillLog();

        Call<List<Person>> caller = client.getAllPersons();

        caller.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                List<Person> APIdata = response.body();

                List<User> users = new ArrayList<>();

                fillers.fillList(APIdata, users);

                RecyclerView chatView = findViewById(R.id.Customers);
                chatView.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(Log.this);
                chatView.setLayoutManager(layoutManager);
                RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> mAdapter = new CustomerListAdapter(users, name, id);
                chatView.setAdapter(mAdapter);
            }
            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                System.out.println("error" + t);
            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                fillList();
            }
        });

        customersManager();
        logManager();

        btn_goBack = findViewById(R.id.goBackLog);
        btn_goToChat = findViewById(R.id.chatBtn);
        btn_submit = findViewById(R.id.submit);
        btn_goBack.setOnClickListener(this);
        btn_goToChat.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        log = (RecyclerView) findViewById(R.id.log);
        customerList = (RecyclerView) findViewById(R.id.Customers);
        hideKeyBoard(log);
        hideKeyBoard(customerList);
    }

    private void fillList() {
        Call<List<Message>> message = client.getMessages(id.getText().toString());
        message.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                List<Message> APIdata = response.body();

                List<CurrentChat> chatter = new ArrayList<>();

                fillers.fillChat(APIdata, chatter, name.getText().toString());

                RecyclerView chat = findViewById(R.id.Chat);
                LinearLayoutManager chatManager = new LinearLayoutManager(Log.this);
                chatManager.setStackFromEnd(true);
                chat.setLayoutManager(chatManager);
                RecyclerView.Adapter<ChatAdapter.ChatViewHolder> cAdapter = new ChatAdapter(Log.this, chatter);
                chat.setAdapter(cAdapter);
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                System.out.println("ERROR: " + t);
            }
        });
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
                logManager();

                if (!message.isEmpty()) {
                    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                    fillers.logger.add(new CurrentLog(0, "Anders Martinsson", message, timestamp));
                    inputText.getText().clear();
                    HelperFunctions.hideSoftKeyboard(this);
                } else {
                    inputText.setError("Empty Field!");
                }
                break;
        }
    }

    private void customersManager() {
        List<User> users = new ArrayList<>();
        RecyclerView logView = findViewById(R.id.Customers);
        logView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Log.this);
        logView.setLayoutManager(layoutManager);
        RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> mAdapter = new CustomerListAdapter(users, name, id);
        logView.setAdapter(mAdapter);
    }

    private void logManager(){
        RecyclerView log = findViewById(R.id.log);
        LinearLayoutManager logManager = new LinearLayoutManager(Log.this);
        logManager.setStackFromEnd(true);
        log.setLayoutManager(logManager);
        RecyclerView.Adapter<LogAdapter.LogViewHolder> logAdapter = new LogAdapter(fillers.logger);
        log.setAdapter(logAdapter);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void hideKeyBoard(RecyclerView recyclerView){
        recyclerView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event){

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                return false;
            }
        });
    }

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            int resultCode = result.getResultCode();
            Intent data = result.getData();
            if (resultCode == RESULT_OK && data != null) {
                image = data.getData();
                btn_imagePicker.setText("Ta bort vald bild");
            }
        }
    });
}