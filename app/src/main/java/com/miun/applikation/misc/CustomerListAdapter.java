package com.miun.applikation.misc;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.miun.applikation.R;

import java.util.List;

// Adapter for chatlist recycler view
public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> {

    List<User> users;
    TextView name;

    public CustomerListAdapter(List<User> users, TextView name) {
        this.users = users;
        this.name = name;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tv_customerName.setText(users.get(position).getName());
        holder.layout.setOnClickListener(view -> {
            // stuff happens..........
            Log.d("ChatList", "User id:" + users.get(position).getPersonId());
            name.setText(users.get(position).getName());
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_customerName;
        ConstraintLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_customerName = itemView.findViewById(R.id.tv_customerName);
            layout = itemView.findViewById(R.id.user_item);
        }
    }
}


