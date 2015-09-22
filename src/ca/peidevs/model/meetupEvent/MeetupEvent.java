package ca.peidevs.model.meetupEvent;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MeetupEvent {

    @SerializedName("results")
    private List<Event> events = new ArrayList<>();

    public List<Event> getEvent() {
        return events;
    }

}
