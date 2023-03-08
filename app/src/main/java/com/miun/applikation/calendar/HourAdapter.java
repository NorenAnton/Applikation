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

    public HourAdapter(@NonNull Context context, List<HourEvent> hourEvents)
    {
        this.context = context;
        this.hourEvents = hourEvents;
    }

    @NonNull
    public HourAdapter.HourViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        HourEvent event = null;
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hour_cell, parent, false);
        setHour(convertView, event.time);
        setEvents(convertView, event.events);

        return new HourViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull HourAdapter.HourViewHolder holder, int position){
        holder.event1.setText((CharSequence) hourEvents.get(position).getEvents());
    }


    @Override
    public int getItemCount(){
        return hourEvents.size();
    }

    public static class HourViewHolder extends RecyclerView.ViewHolder{
        TextView event1;

        public HourViewHolder(@NonNull View itemView){
            super(itemView);
            event1 = itemView.findViewById(R.id.event1);
        }
    }

    private void setHour(View convertView, SimpleDateFormat time)
    {
        TextView timeTV = convertView.findViewById(R.id.tv_hourItem);
        timeTV.setText(CalendarUtils.formattedShortTime(String.valueOf(time)));
    }

    private void setEvents(View convertView, ArrayList<Event> events)
    {
        TextView event1 = convertView.findViewById(R.id.event1);

        if(events.size() == 0)
        {
            hideEvent(event1);
        }
        else if(events.size() == 1)
        {
            setEvent(event1, events.get(0));
        }
        else if(events.size() == 2)
        {
            setEvent(event1, events.get(0));
        }
        else if(events.size() == 3)
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
