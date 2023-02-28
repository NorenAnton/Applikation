package com.miun.applikation.log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.miun.applikation.R;

import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> {


    List<CurrentLog> logger;

    public LogAdapter(List<CurrentLog> logger){
        this.logger = logger;
    }

    @NonNull
    @Override
    public LogAdapter.LogViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.log_item, parent, false);
        return new LogAdapter.LogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogAdapter.LogViewHolder holder, int position){
        holder.tv_logName.setText(logger.get(position).getName());
        holder.tv_message.setText(logger.get(position).getMessage());
        holder.tv_date.setText(logger.get(position).getDate());
    }


    @Override
    public int getItemCount(){
        return logger.size();
    }

    public static class LogViewHolder extends RecyclerView.ViewHolder{
        TextView tv_logName;
        TextView tv_date;
        TextView tv_message;

        public LogViewHolder(@NonNull View itemView){
            super(itemView);
            tv_logName = itemView.findViewById(R.id.tv_logName);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_message = itemView.findViewById(R.id.tv_message);
        }
    }
}