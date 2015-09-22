package ca.peidevs.model.meetupEvent;

import ca.peidevs.model.common.Group;
import ca.peidevs.model.common.Venue;

import java.time.LocalDate;

public class Event {

    private Venue venue;
    private long headcount;
    private String visibility;
    private String description;
    private String name;
    private String id;
    private long time;
    private Group group;

    public Venue getVenue() {
        return venue;
    }
    public long getHeadcount() {
        return headcount;
    }
    public String getDescription() {
        return description;
    }
    public String getName() {
        return name;
    }
    public Group getGroup(){
        return group;
    }
    public String getId() {
        return id;
    }
    public LocalDate getDate() {
        long epochDate = time / (1000 * 60 * 60 *24 );
        return LocalDate.ofEpochDay( epochDate );
    }
}
