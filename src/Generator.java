import ca.peidevs.formatter.ScooterFormatter;
import ca.peidevs.model.meetup.event.Event;
import ca.peidevs.model.meetup.event.MeetupEvent;
import ca.peidevs.model.meetup.rsvp.RsvpList;
import ca.peidevs.service.MeetupService;
import ca.peidevs.service.MeetupUrlGenerator;

public class Generator {

    public static void main( String args[]){

        String meetupGroup = args[0];
        String meetupDate = args[1];
        String key = args[2];

        System.out.println( "Querying for members, give it a minute");

        MeetupService meetupService = new MeetupService( new MeetupUrlGenerator( key ));
        try{

            MeetupEvent events = meetupService.getUpcomingMeetupEvents( meetupGroup );
            Event event = events.getEventByDate( meetupDate );

            if( event.getId() == null ){
                System.out.println( "No Event found for this date");
                return;
            }

            System.out.println("Event Name : " + event.getName());

            if( event.getVenue() != null ){
                System.out.println("Event Location : " + event.getVenue().getName());
            }

            RsvpList rsvpList = meetupService.getRsvpList( event.getId() );

            ScooterFormatter formatter = new ScooterFormatter( rsvpList );

            System.out.println("\nList for Dojo Version");
            System.out.println("---------------------");
            System.out.println( formatter.formatDojo() );

            System.out.println("\nList for Angular Version");
            System.out.println("------------------------");
            System.out.println( formatter.formatAngular() );
        } catch( Exception ex){
            ex.printStackTrace();
        }
    }
}
