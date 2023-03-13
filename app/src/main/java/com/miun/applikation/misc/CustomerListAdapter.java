package com.miun.applikation.misc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.miun.applikation.R;

import org.w3c.dom.Text;

import java.util.List;

// Adapter for chatlist recycler view
public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.MyViewHolder> {

    List<User> users;
    TextView name;
    TextView id;

    public CustomerListAdapter(List<User> users, TextView name, TextView id) {
        this.users = users;
        this.name = name;
        this.id = id;
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
        holder.customerID.setText(Integer.toString(users.get(position).getPersonId()) + ". ");
        holder.layout.setOnClickListener(view -> {
            String personId = Integer.toString(users.get(position).getPersonId());
            id.setText(personId);
            name.setText(users.get(position).getName());
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_customerName, customerID;
        ConstraintLayout layout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_customerName = itemView.findViewById(R.id.tv_customerName);
            customerID = itemView.findViewById(R.id.customerID);
            layout = itemView.findViewById(R.id.user_item);
        }
    }
}


