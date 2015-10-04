package ca.peidevs.view.console;

import ca.peidevs.formatter.ScooterFormatter;
import ca.peidevs.model.meetup.common.Venue;
import ca.peidevs.model.meetup.rsvp.RsvpList;
import ca.peidevs.view.IView;

public class ConsoleUserInterface implements IView {
    @Override
    public void setMeetupName(String name) {
        System.out.println("Event Name : " + name );
    }

    @Override
    public void setMeetupLocation(Venue venue) {
        String location = "";

        if( venue != null){
            location = venue.getName();
        }
        System.out.println("Meetup Location: " + location);
    }

    @Override
    public void setUserList(RsvpList attendeeList) {
        ScooterFormatter formatter = new ScooterFormatter( attendeeList );

        System.out.println("\nList for Dojo Version");
        System.out.println("---------------------");
        System.out.println( formatter.formatDojo() );

        System.out.println("\nList for Angular Version");
        System.out.println("------------------------");
        System.out.println( formatter.formatAngular() );
    }
}
