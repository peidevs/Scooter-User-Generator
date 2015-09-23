import ca.peidevs.formatter.ScooterAngularFormatter;
import ca.peidevs.formatter.ScooterDojoFormatter;
import ca.peidevs.formatter.ScooterFormatter;
import ca.peidevs.model.meetupEvent.Event;
import ca.peidevs.model.meetupEvent.MeetupEvent;
import ca.peidevs.model.rsvp.RsvpList;
import ca.peidevs.service.MeetupService;
import ca.peidevs.service.MeetupUrlGenerator;
import ca.peidevs.model.GuestList;

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

            if( "".equals( event.getId()) ){
                System.out.println( "No Event found for this date");
                return;
            }

            System.out.println("Event Name : " + event.getName());

            RsvpList rsvp = meetupService.getRsvpList( event.getId() );




//            ScooterFormatter dFormatter = new ScooterDojoFormatter();
//            String dojoResult = dFormatter.format( guestList );
//
//            System.out.println("\nList for Dojo Version");
//            System.out.println("---------------------");
//            System.out.println(dojoResult);
//
//            ScooterFormatter aFormatter = new ScooterAngularFormatter();
//            String angularResult = aFormatter.format( guestList );
//
//            System.out.println("\nList for Angular Version");
//            System.out.println("------------------------");
//            System.out.println( angularResult );


        } catch( Exception ex){
            ex.printStackTrace();
        }
    }
}
