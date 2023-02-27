package com.miun.applikation.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miun.applikation.R;

import java.util.List;

//Adapter for current chat
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    List<CurrentChat> chatter;

    public ChatAdapter(List<CurrentChat> chatter){
        this.chatter = chatter;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position){
        holder.tv_chatName.setText(chatter.get(position).getName());
        holder.tv_message.setText(chatter.get(position).getMessage());
        holder.tv_date.setText(chatter.get(position).getDate());
    }


    @Override
    public int getItemCount(){
        return chatter.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder{
        TextView tv_chatName;
        TextView tv_date;
        TextView tv_message;

        public ChatViewHolder(@NonNull View itemView){
            super(itemView);
            tv_chatName = itemView.findViewById(R.id.tv_chatName);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_message = itemView.findViewById(R.id.tv_message);
        }
    }
}
