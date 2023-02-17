package com.miun.applikation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Adapter for current chat
public class chatAdapter extends RecyclerView.Adapter<chatAdapter.ChatViewHolder> {

    List<currentChat> chatter;

    public chatAdapter(List<currentChat> chatter){
        this.chatter = chatter;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatt_item, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position){
        holder.tv_chatName.setText(chatter.get(position).getName());
        holder.tv_message.setText(chatter.get(position).getMessage());
        holder.tv_time.setText(chatter.get(position).getDate() + " " + chatter.get(position).getTime());
    }

    @Override
    public int getItemCount(){
        return chatter.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder{
        TextView tv_chatName;
        TextView tv_time;
        TextView tv_message;

        public ChatViewHolder(@NonNull View itemView){
            super(itemView);
            tv_chatName = itemView.findViewById(R.id.tv_chatName);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_message = itemView.findViewById(R.id.tv_message);
        }
    }
}