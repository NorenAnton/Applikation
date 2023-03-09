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

import java.text.SimpleDateFormat;
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
        SimpleDateFormat time = new SimpleDateFormat();
        ArrayList<Event> events = Event.eventsForDateAndTime(CalendarUtils.selectedDate, time);
        HourEvent event = new HourEvent(time, events);
        setHour(convertView, event.time);
        setEvents(convertView, event.events);

        return new HourViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull HourAdapter.HourViewHolder holder, int position){
        for(Event h: hourEvents.get(position).getEvents()){
            holder.event1.setText((CharSequence) h);
        }
    }


    @Override
    public int getItemCount(){
        return Math.min(hourEvents.size(), limit);
    }

    public static class HourViewHolder extends RecyclerView.ViewHolder{
        TextView event1;

        public HourViewHolder(@NonNull View itemView){
            super(itemView);
            event1 = itemView.findViewById(R.id.event1);
        }
    }

    private void setHour(@NonNull View convertView, SimpleDateFormat time)
    {
        TextView timeTV = convertView.findViewById(R.id.tv_hourItem);
        timeTV.setText(CalendarUtils.formattedShortTime(String.valueOf(time)));
    }

    private void setEvents(@NonNull View convertView, @NonNull ArrayList<Event> events)
    {
        TextView event1 = convertView.findViewById(R.id.event1);

        if(events.isEmpty())
        {
            hideEvent(event1);
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

    private void hideEvent(TextView tv)
    {
        tv.setVisibility(View.INVISIBLE);
    }
}
