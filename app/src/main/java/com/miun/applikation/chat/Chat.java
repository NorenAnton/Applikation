package com.miun.applikation.chat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miun.applikation.misc.CustomerListAdapter;
import com.miun.applikation.utils.HelperFunctions;
import com.miun.applikation.MainActivity;
import com.miun.applikation.R;
import com.miun.applikation.log.Log;
import com.miun.applikation.utils.ChatLogUtils;

import java.net.URI;

public class Chat extends AppCompatActivity implements View.OnClickListener {

    ChatLogUtils fillers = new ChatLogUtils();
    Button btn_goBack, btn_goToLog, btn_imagePicker, btn_submit;
    EditText inputText;
    Uri image = null;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        inputText = findViewById(R.id.inputText);
        fillers.fillList();
        fillers.fillChat();

        customerManager();
        chatManager();

        btn_goBack = findViewById(R.id.goBackChat);
        btn_goToLog = findViewById(R.id.logBtn);
        btn_imagePicker = findViewById(R.id.btn_imagePicker);
        btn_submit = findViewById(R.id.submit);
        btn_goBack.setOnClickListener(this);
        btn_goToLog.setOnClickListener(this);
        btn_imagePicker.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

    }

    public void customerManager(){
        RecyclerView chatView = findViewById(R.id.Customers);
        chatView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Chat.this);
        chatView.setLayoutManager(layoutManager);
        RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> mAdapter = new CustomerListAdapter(fillers.users);
        chatView.setAdapter(mAdapter);
    }

    public void chatManager(){
        RecyclerView chat = findViewById(R.id.Chat);
        LinearLayoutManager chatManager = new LinearLayoutManager(Chat.this);
        chatManager.setStackFromEnd(true);
        chat.setLayoutManager(chatManager);
        RecyclerView.Adapter<ChatAdapter.ChatViewHolder> cAdapter = new ChatAdapter(this, fillers.chatter);
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
            case R.id.btn_imagePicker:
                if(image == null) {
                    intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncher.launch(intent);
                }
                else {
                    btn_imagePicker.setText("VÄLJ BILD");
                    image = null;
                }
                break;
            case R.id.submit:
                String message = inputText.getText().toString();
                chatManager();
                if (!message.isEmpty() || image != null) {
                    fillers.chatter.add(new CurrentChat(0, "Anders Martinsson", message, image));
                    image = null;
                    inputText.getText().clear();
                    HelperFunctions.hideSoftKeyboard(this);
                } else {
                    inputText.setError("Empty Field!");
                }
                break;
        }
    }
}
