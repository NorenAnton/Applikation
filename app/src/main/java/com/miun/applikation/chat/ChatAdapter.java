package com.miun.applikation.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.miun.applikation.R;

import java.util.List;

//Adapter for current chat
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    Context context;
    List<CurrentChat> chatter;

    int test;

    public ChatAdapter(Context context, List<CurrentChat> chatter){
        this.context = context;
        this.chatter = chatter;
        test = 0;
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
        if (test % 3 == 0)
            Glide.with(context).load("https://cdn.pixabay.com/photo/2014/06/03/19/38/board-361516__340.jpg").override(600,500).into(holder.iv_image);
        ++test;
    }


    @Override
    public int getItemCount(){
        return chatter.size();
    }

    public static class ChatViewHolder extends RecyclerView.ViewHolder{
        TextView tv_chatName;
        TextView tv_date;
        TextView tv_message;
        ImageView iv_image;

        public ChatViewHolder(@NonNull View itemView){
            super(itemView);
            tv_chatName = itemView.findViewById(R.id.tv_chatName);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_message = itemView.findViewById(R.id.tv_message);
            iv_image = itemView.findViewById(R.id.iv_image);
        }
    }
}
