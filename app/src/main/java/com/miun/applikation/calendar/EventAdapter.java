package com.miun.applikation.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.miun.applikation.R;
import com.miun.applikation.utils.CalendarUtils;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {

    public EventAdapter(@NonNull Context context, List<Event> events)
    {
        super(context, 0, events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Event event = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_cell, parent, false);
        }

        TextView tv_eventCell = convertView.findViewById(R.id.tv_eventCell);

        String eventTitle = event.getName() +" "+ CalendarUtils.formattedTime(event.getTime());
        tv_eventCell.setText(eventTitle);
        return convertView;
    }
}
