package com.miun.applikation.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miun.applikation.R;
import com.miun.applikation.utils.CalendarUtils;
import com.miun.retrofit.InterfaceAPI;
import com.miun.retrofit.RequestInterface;
import com.miun.retrofit.models.Person;
import com.miun.retrofit.retrofitClient;

import java.time.LocalTime;
import java.util.List;

public class HourAdapter extends RecyclerView.Adapter<HourAdapter.HourViewHolder> {

    String baseurl = "http://10.82.227.191:8080/";

    retrofitClient client = new InterfaceAPI(baseurl).createRetrofitClient();

    Context context;
    FragmentManager manager;
    List<HourEvent> hourEvents;
    private final int limit = 9;

    public HourAdapter(@NonNull Context context,FragmentManager manager, List<HourEvent> hourEvents)
    {
        this.context = context;
        this.manager = manager;
        this.hourEvents = hourEvents;
    }

    @NonNull
    public HourAdapter.HourViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hour_cell, parent, false);
        return new HourViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull HourViewHolder holder, int position){

        holder.timeTV.setText(String.valueOf(position+10)+":00");

        if (hourEvents.get(position).getPersonID() == 0) {
            holder.customer.setVisibility(View.INVISIBLE);
            holder.time.setVisibility(View.INVISIBLE);
            holder.subject.setVisibility(View.INVISIBLE);
            holder.freetext.setVisibility(View.INVISIBLE);
        } else if (hourEvents.size() >= 1) {
            Integer ID = hourEvents.get(position).getPersonID();
            new RequestInterface<>(client.getPerson(ID.toString()), (Person container)->{
                holder.customer.setText(container.getFname() + " " + container.getLname());
            });
            holder.customer.setVisibility(View.VISIBLE);
            holder.time.setText(hourEvents.get(position).getStartEndTime());
            holder.time.setVisibility(View.VISIBLE);
            holder.subject.setText(hourEvents.get(position).getSubject());
            holder.subject.setVisibility(View.VISIBLE);
            holder.freetext.setText(hourEvents.get(position).getFreetext());
            holder.freetext.setVisibility(View.VISIBLE);
        }



        holder.linearLayout.setOnClickListener(view -> {
            EventPopup eventPopup = new EventPopup(hourEvents.get(position));
            eventPopup.show(manager, "EventPopup");
        });
    }


    @Override
    public int getItemCount(){
        return Math.min(hourEvents.size(), limit);
    }

    public static class HourViewHolder extends RecyclerView.ViewHolder{
        TextView timeTV, customer, time, subject, freetext;
        LinearLayout linearLayout;

        public HourViewHolder(@NonNull View itemView){
            super(itemView);
            timeTV = itemView.findViewById(R.id.tv_hourItem);
            customer = itemView.findViewById(R.id.customer);
            time = itemView.findViewById(R.id.time);
            subject = itemView.findViewById(R.id.subject);
            freetext = itemView.findViewById(R.id.freetext);
            linearLayout = itemView.findViewById(R.id.event_linearlayout);
        }
    }

    private void setEvents(TextView customer, TextView time, TextView subject, TextView freetext, @NonNull List<HourEvent> hourEvents)
    {
        for (HourEvent h: hourEvents) {
            if (h.getPersonID() == 0) {
                customer.setVisibility(View.INVISIBLE);
                time.setVisibility(View.INVISIBLE);
                subject.setVisibility(View.INVISIBLE);
                freetext.setVisibility(View.INVISIBLE);
            } else if (hourEvents.size() >= 1) {
                setEvent(customer, time, subject, freetext, h);
            }
        }
    }

    private void setEvent(TextView customer, TextView time, TextView subject, TextView freetext, HourEvent hourEvent)
    {
        customer.setText(hourEvent.getPersonID().toString());
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
