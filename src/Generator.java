import ca.peidevs.controller.ScooterUserListController;
import ca.peidevs.service.MeetupService;
import ca.peidevs.service.MeetupUrlGenerator;
import ca.peidevs.view.console.ConsoleUserInterface;
import ca.peidevs.view.IView;

import java.time.LocalDate;

public class Generator {

    public static void main( String args[]){

        if( args.length !=3){
            Generator.printConsoleUsage();
        } else {
            String groupName = args[0];
            String meetupDate = args[1];
            String key = args[2];

            Generator.startConsoleApplication(groupName, LocalDate.parse(meetupDate), key);
        }
    }

    private static void printConsoleUsage(){
        System.out.println("The application requires 3 parameters. group name, meetup date and api key");
        System.out.println("group name - As found on meetup. This is the name of the group you are searching for");
        System.out.println("meetup date - Date of the meetup in the form of yyyy-MM-dd. i.e. 2015-01-31");
        System.out.println("api key - Each user profile on meetup has an API key. Look on meetup to get the one " +
                "associated with your profile. The api key is required to make queries to the meetup site");
    }

    private static void startConsoleApplication(String groupName, LocalDate meetupDate, String key){
        IView view = new ConsoleUserInterface();
        MeetupService meetupService = new MeetupService( new MeetupUrlGenerator());

        ScooterUserListController controller = new ScooterUserListController(view, meetupService);

        controller.retrieveMeetupAttendees( groupName, meetupDate, key );
    }
}
