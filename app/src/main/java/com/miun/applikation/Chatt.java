package com.miun.applikation;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Chatt extends AppCompatActivity {


    List<User> users = new ArrayList<>();

    Button btn_goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatt);

        fillList();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Chatt.this);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> mAdapter = new CustomerListAdapter(users);
        recyclerView.setAdapter(mAdapter);


        btn_goBack = findViewById(R.id.goBack);

        btn_goBack.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }

    private void fillList() {
        users.add(new User("Vincent Johansson", 0));
        users.add(new User("Gillian Persson", 1));
        users.add(new User("Anton Noren", 2));
        users.add(new User("Anton Noren", 2));
        users.add(new User("Anton Noren", 2));
        users.add(new User("Anton Noren", 2));
        users.add(new User("Anton Noren", 2));
    }


}
