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
        for(Event h: hourEvents.get(position).getEvents()){
            holder.customer.setText((CharSequence) h);
            holder.time.setText((CharSequence) h);
            holder.subject.setText((CharSequence) h);
            holder.freetext.setText((CharSequence) h);
        }
        HourEvent event = getItem(position);
        setHour(holder.timeTV, event.time);
        setEvents(holder.customer, holder.time, holder.subject, holder.freetext, event.events);
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

    private void setHour(TextView timeTV, LocalTime time)
    {
       timeTV.setText(CalendarUtils.formattedShortTime(time));
    }

    private void setEvents(TextView customer, TextView time, TextView subject, TextView freetext, @NonNull ArrayList<Event> events)
    {
        if(events.isEmpty())
        {
            customer.setVisibility(View.INVISIBLE);
            time.setVisibility(View.INVISIBLE);
            subject.setVisibility(View.INVISIBLE);
            freetext.setVisibility(View.INVISIBLE);
        }
        else if(events.size() == 1)
        {
            setEvent(customer, time, subject, freetext, events.get(0));
        }
        else
        {
            setEvent(customer, time, subject, freetext, events.get(0));
            String eventsNotShown = String.valueOf(events.size() - 2);
            eventsNotShown += " More Events";
        }
    }

    private void setEvent(TextView customer, TextView time, TextView subject, TextView freetext, Event event)
    {
        customer.setText(event.getPersonID());
        customer.setVisibility(View.VISIBLE);
        //time.setText(event.getStartEndTime());
        time.setVisibility(View.VISIBLE);
        subject.setText(event.getSubject());
        subject.setVisibility(View.VISIBLE);
        freetext.setText(event.getFreetext());
        freetext.setVisibility(View.VISIBLE);
    }

    public HourEvent getItem(int position){
        return hourEvents.get(position);
    }
}
