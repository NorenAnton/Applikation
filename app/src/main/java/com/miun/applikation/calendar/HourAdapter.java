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
    public void onBindViewHolder(@NonNull HourAdapter.HourViewHolder holder, int position){
        for(Event h: hourEvents.get(position).getEvents()){
            holder.event1.setText((CharSequence) h);
        }
        HourEvent event = getItem(position);
        setHour(holder.timeTV, event.time);
        setEvents(holder.event1, event.events);
    }


    @Override
    public int getItemCount(){
        return Math.min(hourEvents.size(), limit);
    }

    public static class HourViewHolder extends RecyclerView.ViewHolder{
        TextView event1;
        TextView timeTV;

        public HourViewHolder(@NonNull View itemView){
            super(itemView);
            event1 = itemView.findViewById(R.id.event1);
            timeTV = itemView.findViewById(R.id.tv_hourItem);
        }
    }

    private void setHour(TextView timeTV, LocalTime time)
    {
       timeTV.setText(CalendarUtils.formattedShortTime(time));
    }

    private void setEvents(TextView event1, @NonNull ArrayList<Event> events)
    {
        if(events.isEmpty())
        {
            event1.setVisibility(View.INVISIBLE);
        }
        else if(events.size() == 1)
        {
            setEvent(event1, events.get(0));
        }
        else
        {
            setEvent(event1, events.get(0));
            String eventsNotShown = String.valueOf(events.size() - 2);
            eventsNotShown += " More Events";
        }
    }

    private void setEvent(TextView textView, Event event)
    {
        textView.setText(event.getName());
        textView.setVisibility(View.VISIBLE);
    }

    public HourEvent getItem(int position){
        return hourEvents.get(position);
    }
}
