package ca.peidevs.model;

public class Guest {
    private String name;
    private int guests;

    public Guest(String name, int numGuests) {
        this.name = name;
        this.guests = numGuests;
    }

    public int getNumGuests() {
        return guests;
    }

    public String getName() {

        return name;
    }
}
