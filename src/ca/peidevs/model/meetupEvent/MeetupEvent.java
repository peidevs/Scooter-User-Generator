package ca.peidevs.model.meetupEvent;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MeetupEvent {

    @SerializedName("results")
    private List<Event> events = new ArrayList<>();

    public List<Event> getEvent() {
        return events;
    }

    /**
     * Assumes group wont have 2 meetups in the same day
     *
     * @param meetupDate
     * @return
     */
    public Event getEventByDate(String meetupDate ) {

        LocalDate dateToFind = LocalDate.parse( meetupDate );

        Event event = this.events.stream()
                .filter( e -> dateToFind.isEqual( e.getDate() ))
                .findFirst()
                .orElse( new Event() );

        return event;
    }

}
