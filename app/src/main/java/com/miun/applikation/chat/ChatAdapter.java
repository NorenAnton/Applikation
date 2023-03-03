package com.miun.applikation.chat;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.miun.applikation.R;

import java.io.File;
import java.net.URI;
import java.util.List;

//Adapter for current chat
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    Context context;
    List<CurrentChat> chatter;

    public ChatAdapter(Context context, List<CurrentChat> chatter){
        this.context = context;
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
        if (chatter.get(position).getImage() != null) {
            Glide.with(context).load(chatter.get(position).getImage().toString()).override(600, 500).into(holder.iv_image);
            holder.iv_image.setOnClickListener(view -> {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setDataAndType(chatter.get(holder.getAdapterPosition()).getImage(), "image/*");
                context.startActivity(intent);
            });
        }
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
