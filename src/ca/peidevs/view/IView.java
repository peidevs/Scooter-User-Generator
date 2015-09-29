package ca.peidevs.view;

import ca.peidevs.model.meetup.common.Venue;
import ca.peidevs.model.meetup.rsvp.RsvpList;

public interface IView {

    void setMeetupName( String name );
    void setMeetupLocation( Venue venue);
    void setUserList( RsvpList attendeeList );
}
