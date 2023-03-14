package com.miun.applikation.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miun.applikation.R;
import com.miun.applikation.utils.CalendarUtils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {

    Context context;
    List<HourEvent> hourEvents;
    private final int limit = 9;

    public HourAdapter(@NonNull Context context, List<HourEvent> hourEvents)
    {
        this.context = context;
        this.hourEvents = hourEvents;
    }

    @NonNull
    public HourAdapter.HourViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hour_cell, parent, false);
        return new HourViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull HourViewHolder holder, int position){
        for(HourEvent h: hourEvents.get(position).){
            holder.customer.setText((CharSequence) h);
            holder.time.setText((CharSequence) h);
            holder.subject.setText((CharSequence) h);
            holder.freetext.setText((CharSequence) h);
        }
        HourEvent hourEvent = getItem(position);
        setHour(holder.timeTV, hourEvent.getStartTime(), hourEvent.getEndTime());
        setEvents(holder.customer, holder.time, holder.subject, holder.freetext, hourEvent.hourEvents);
    }


    @Override
    public int getItemCount(){
        return Math.min(hourEvents.size(), limit);
    }

    public static class HourViewHolder extends RecyclerView.ViewHolder{
        TextView customer, time, subject, freetext, timeTV;

        public HourViewHolder(@NonNull View itemView){
            super(itemView);
            customer = itemView.findViewById(R.id.customer);
            time = itemView.findViewById(R.id.time);
            subject = itemView.findViewById(R.id.subject);
            freetext = itemView.findViewById(R.id.freetext);
            timeTV = itemView.findViewById(R.id.tv_hourItem);
        }
    }

    private void setHour(TextView timeTV, LocalTime startTime, LocalTime endTime)
    {
       timeTV.setText(CalendarUtils.formattedShortTime(startTime) + " - " + CalendarUtils.formattedShortTime(endTime));
    }

    private void setEvents(TextView customer, TextView time, TextView subject, TextView freetext, @NonNull ArrayList<HourEvent> hourEvents)
    {
        if(hourEvents.isEmpty())
        {
            customer.setVisibility(View.INVISIBLE);
            time.setVisibility(View.INVISIBLE);
            subject.setVisibility(View.INVISIBLE);
            freetext.setVisibility(View.INVISIBLE);
        }
        else if(hourEvents.size() == 1)
        {
            setEvent(customer, time, subject, freetext, hourEvents.get(0));
        }
        else
        {
            setEvent(customer, time, subject, freetext, hourEvents.get(0));
            String eventsNotShown = String.valueOf(hourEvents.size() - 2);
            eventsNotShown += " More Events";
        }
    }

    private void setEvent(TextView customer, TextView time, TextView subject, TextView freetext, HourEvent hourEvent)
    {
        customer.setText(hourEvent.getPersonID());
        customer.setVisibility(View.VISIBLE);
        time.setText(hourEvent.getStartEndTime());
        time.setVisibility(View.VISIBLE);
        subject.setText(hourEvent.getSubject());
        subject.setVisibility(View.VISIBLE);
        freetext.setText(hourEvent.getFreetext());
        freetext.setVisibility(View.VISIBLE);
    }

    public HourEvent getItem(int position){
        return hourEvents.get(position);
    }
}
