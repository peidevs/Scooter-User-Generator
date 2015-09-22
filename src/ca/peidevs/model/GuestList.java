package ca.peidevs.model;

import java.util.ArrayList;
import java.util.List;

public class GuestList {
    private List<Guest> guests = new ArrayList<>();

    public List<Guest> getGuests(){
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }
}
