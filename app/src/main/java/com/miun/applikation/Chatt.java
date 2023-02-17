package com.miun.applikation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Chatt extends AppCompatActivity {
    List<User> users = new ArrayList<>();

    List<currentChat> chatter = new ArrayList<>();
    Button btn_goBack;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatt);

        inputText = (EditText) findViewById(R.id.inputText);
        fillList();
        fillChat();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Chatt.this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> mAdapter = new CustomerListAdapter(users);
        recyclerView.setAdapter(mAdapter);


        RecyclerView chat = findViewById(R.id.chatt);
        chat.setHasFixedSize(true);
        RecyclerView.LayoutManager chatManager = new LinearLayoutManager(Chatt.this);
        chat.setLayoutManager(chatManager);
        RecyclerView.Adapter<chatAdapter.ChatViewHolder> cAdapter = new chatAdapter(chatter);
        chat.setAdapter(cAdapter);


        btn_goBack = findViewById(R.id.goBack);

        btn_goBack.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }

    private void fillList() {
        users.add(new User("Vincent", "Johansson", 0));
        users.add(new User("Gillian", "Persson", 1));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));
        users.add(new User("Anton", "Noren", 2));

    }

    private void fillChat(){
        chatter.add(new currentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new currentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new currentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new currentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new currentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new currentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new currentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new currentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new currentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new currentChat(0, "Anton Noren", "Hello world"));
        chatter.add(new currentChat(0, "Anton Noren", "Hello world"));
    }

    public void onClick(View view){

    }


}
