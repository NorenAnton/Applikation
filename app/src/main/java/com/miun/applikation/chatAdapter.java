package com.miun.applikation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.text.format.DateFormat;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

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
        /*
        holder.tv_chatName.setText(chatter.get(position).getName());
        holder.tv_message.setText(chatter.get(position).getMessage());
        holder.tv_time.setText(chatter.get(position).getDate() + " " + chatter.get(position).getTime());
        */
        String chatName = chatter.get(position).getName();
        String message = chatter.get(position).getMessage();
        String timeStamp = chatter.get(position).getTime();
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(Long.parseLong(timeStamp));
        String timeDate = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();
        holder.tv_chatName.setText(chatName);
        holder.tv_message.setText(message);
        holder.tv_time.setText(timeDate);
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
