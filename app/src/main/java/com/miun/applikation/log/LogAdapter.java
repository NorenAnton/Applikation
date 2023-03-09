package com.miun.applikation.log;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.miun.applikation.R;

import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.LogViewHolder> implements EditLogPopup.DialogListener {

    FragmentManager manager;
    List<CurrentLog> logger;

    public LogAdapter(FragmentManager manager, List<CurrentLog> logger){
        this.manager = manager;
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

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(holder);
            }
        });

    }


    @Override
    public int getItemCount(){
        return logger.size();
    }

    public void showDialog(LogAdapter.LogViewHolder holder){
        EditLogPopup editLogPopup = new EditLogPopup(holder.tv_message, holder.getAdapterPosition(), this);
        editLogPopup.show(manager, "edit");
    }


    @Override
    public void onDialogPositiveClick(TextView textView, int position) {
        if (logger.get(position).getMessage() != textView.getText().toString() && !textView.getText().toString().isEmpty()) {
            this.logger.get(position).setMessage(textView.getText().toString());
            notifyItemChanged(position);
        }
    }

    public static class LogViewHolder extends RecyclerView.ViewHolder{
        TextView tv_logName;
        TextView tv_date;
        TextView tv_message;

        ConstraintLayout constraintLayout;

        public LogViewHolder(@NonNull View itemView){
            super(itemView);
            tv_logName = itemView.findViewById(R.id.tv_logName);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_message = itemView.findViewById(R.id.tv_message);
            constraintLayout = itemView.findViewById(R.id.ConstraintLayout);
        }
    }
}