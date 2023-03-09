package com.miun.applikation.chat;

import static android.os.SystemClock.sleep;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miun.applikation.misc.CustomerListAdapter;
import com.miun.applikation.misc.User;
import com.miun.applikation.utils.HelperFunctions;
import com.miun.applikation.MainActivity;
import com.miun.applikation.R;
import com.miun.applikation.log.Log;
import com.miun.applikation.utils.ChatLogUtils;
import com.miun.retrofit.InterfaceAPI;
import com.miun.retrofit.models.Message;
import com.miun.retrofit.models.MessageModelPost;
import com.miun.retrofit.models.Person;
import com.miun.retrofit.retrofitClient;

import java.sql.Timestamp;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Chat extends AppCompatActivity implements View.OnClickListener {

    int andersID = 1;
    ChatLogUtils fillers = new ChatLogUtils();
    Button btn_goBack, btn_goToLog, btn_imagePicker, btn_submit;
    EditText inputText;
    Uri image = null;
    TextView name, id;
    RecyclerView customerList, chat;
    String baseurl = "http://10.82.227.191:8080/";
    retrofitClient client = new InterfaceAPI(baseurl).createRetrofitClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        customerList = findViewById(R.id.Customers);
        inputText = findViewById(R.id.inputText);
        chat = findViewById(R.id.Chat);
        name = findViewById(R.id.name);
        id = findViewById(R.id.id);

        Call<List<Person>> caller = client.getAllPersons();
        caller.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                List<Person> APIdata = response.body();

                List<User> users = new ArrayList<>();

                fillers.fillList(APIdata, users);

                RecyclerView chatView = findViewById(R.id.Customers);
                chatView.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(Chat.this);
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

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            id.setText(extras.getString("id"));
            name.setText(extras.getString("name"));
        }

        btn_goBack = findViewById(R.id.goBackChat);
        btn_goToLog = findViewById(R.id.logBtn);
        btn_imagePicker = findViewById(R.id.btn_imagePicker);
        btn_submit = findViewById(R.id.submit);
        btn_goBack.setOnClickListener(this);
        btn_goToLog.setOnClickListener(this);
        btn_imagePicker.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        chatManager();
        customerManager();

        hideKeyBoard(chat);
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
                LinearLayoutManager chatManager = new LinearLayoutManager(Chat.this);
                chatManager.setStackFromEnd(true);
                chat.setLayoutManager(chatManager);
                RecyclerView.Adapter<ChatAdapter.ChatViewHolder> cAdapter = new ChatAdapter(Chat.this, chatter);
                chat.setAdapter(cAdapter);
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
                System.out.println("ERROR: " + t);
            }
        });
    }


    public void chatManager(){
        RecyclerView chat = findViewById(R.id.Chat);
        List<CurrentChat> chatter = new ArrayList<>();
        LinearLayoutManager chatManager = new LinearLayoutManager(Chat.this);
        chatManager.setStackFromEnd(true);
        chat.setLayoutManager(chatManager);
        RecyclerView.Adapter<ChatAdapter.ChatViewHolder> cAdapter = new ChatAdapter(this, chatter);
        chat.setAdapter(cAdapter);
    }

    public void customerManager(){
        List<User> users = new ArrayList<>();
        RecyclerView chatView = findViewById(R.id.Customers);
        chatView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(Chat.this);
        chatView.setLayoutManager(layoutManager);
        RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> mAdapter = new CustomerListAdapter(users, name, id);
        chatView.setAdapter(mAdapter);
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
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("id", id.getText().toString());
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
                if (!message.isEmpty() || image != null) {
                    MessageModelPost send;
                    if(image != null){
                        send = new MessageModelPost(andersID, Integer.parseInt(id.getText().toString()), message, image.toString());
                    }
                    else{
                        send = new MessageModelPost(andersID, Integer.parseInt(id.getText().toString()), message, null);
                    }
                    Call<MessageModelPost> caller = client.addMessage(send);
                    caller.enqueue(new Callback<MessageModelPost>() {
                        @Override
                        public void onResponse(Call<MessageModelPost> call, Response<MessageModelPost> response) {
                        }
                        @Override
                        public void onFailure(Call<MessageModelPost> call, Throwable t) {
                            System.out.println("ERROR" + t);
                        }
                    });
                    image = null;
                    btn_imagePicker.setText("VÄLJ BILD");
                    inputText.getText().clear();
                    HelperFunctions.hideSoftKeyboard(this);
                    sleep(1000);
                    fillList();
                } else {
                    inputText.setError("Empty Field!");
                }
                break;
        }
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
