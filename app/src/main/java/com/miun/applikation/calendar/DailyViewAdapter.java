package com.miun.applikation.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miun.applikation.R;

import java.text.SimpleDateFormat;
import java.util.List;

public class DailyViewAdapter extends RecyclerView.Adapter<DailyViewAdapter.EventViewHolder> {

    private List<Event> mEvents;
    private List<Event> events;

    public DailyViewAdapter(List<Event> events) {
        mEvents = events;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View eventView = inflater.inflate(R.layout.event_item, parent, false);
        EventViewHolder viewHolder = new EventViewHolder(eventView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = mEvents.get(position);

        holder.titleTextView.setText(event.getTitle());

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String startTime = sdf.format(event.getStartTime().getTime()) + " - " +
                sdf.format(event.getEndTime().getTime());
        holder.timeTextView.setText(startTime);
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView timeTextView;

        public EventViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.event_title);
            timeTextView = itemView.findViewById(R.id.event_time);
        }
    }
}





