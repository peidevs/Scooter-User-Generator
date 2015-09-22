package ca.peidevs.model.rsvp;

import ca.peidevs.model.common.Group;

public class Rsvp {
    private int guests;
    private Member member;
    private String response;

    public int getNumGuests() {
        return guests;
    }
    public Member getMember() {
        return member;
    }
    public boolean isAttending(){
        return "yes".equals( response );
    }
}