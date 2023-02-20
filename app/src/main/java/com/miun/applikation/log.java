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

public class log extends AppCompatActivity {

    List<User> users = new ArrayList<>();

    List<CurrentChat> chatter = new ArrayList<>();
    Button btn_goBack;
    EditText inputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);

        inputText = (EditText) findViewById(R.id.inputText);
        fillList();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(log.this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> mAdapter = new CustomerListAdapter(users);
        recyclerView.setAdapter(mAdapter);


        RecyclerView chat = findViewById(R.id.log);
        chat.setHasFixedSize(true);
        RecyclerView.LayoutManager chatManager = new LinearLayoutManager(log.this);
        chat.setLayoutManager(chatManager);
        RecyclerView.Adapter<ChatAdapter.ChatViewHolder> cAdapter = new ChatAdapter(chatter);
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
    }
}