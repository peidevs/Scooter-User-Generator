package ca.peidevs.model.meetup.rsvp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RsvpList {
    @SerializedName("results")
    private List<Rsvp> rsvp = new ArrayList<>();

    public List<Rsvp> getRsvps() {
        return rsvp;
    }

    public void setRsvps(List<Rsvp> rsvps) {
        this.rsvp = rsvps;
    }
}
