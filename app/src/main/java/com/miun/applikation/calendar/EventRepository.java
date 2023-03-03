package com.miun.applikation.calendar;

import java.util.ArrayList;
import java.util.List;

public class EventRepository {

    private static EventRepository sInstance;

    private List<Event> mEvents;

    private EventRepository() {
        mEvents = new ArrayList<>();
    }

    public static EventRepository getInstance() {
        if (sInstance == null) {
            sInstance = new EventRepository();
        }
        return sInstance;
    }

    public List<Event> getEvents() {
        return mEvents;
    }

    public void addEvent(Event event) {
        mEvents.add(event);
    }

    public void editEvent(Event event) {
        int index = findEventIndexById(event.getId());
        if (index >= 0) {
            mEvents.set(index, event);
        }
    }

    public void deleteEvent(Event event) {
        mEvents.remove(event);
    }

    private int findEventIndexById(int id) {
        for (int i = 0; i < mEvents.size(); i++) {
            Event event = mEvents.get(i);
            if (event.getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
