package ca.peidevs.service;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;

import ca.peidevs.model.Guest;
import ca.peidevs.model.GuestList;
import ca.peidevs.model.meetupEvent.MeetupEvent;
import ca.peidevs.model.rsvp.RsvpList;
import com.google.gson.Gson;

public class MeetupService {
    MeetupUrlGenerator meetupUrlGenerator;
    Gson gson;

    /**
     * Eventually it would be nice to replace all the hard coded fields with a config file.
     */
    public MeetupService( MeetupUrlGenerator meetupUrlGenerator){
        this.meetupUrlGenerator = meetupUrlGenerator;
        this.gson = new Gson();
    };

    /**
     * Cleanup this API, there is no reason it is doing so many things
     *
     * Have this function return the raw MeetupEvent
     * Guest List can be a separate function.
     * This way when there is a GUI in place, when we query we can show the event name and location that is found
     * Currently that level of detail is hidden
     *
     * @param meetupGroup
     * @param meetupDate
     * @return
     */
    public GuestList getGuestList(String meetupGroup, String meetupDate) throws IOException{
        GuestList guestList = new GuestList();

        String eventUrl =  meetupUrlGenerator.generateEventsURL(meetupGroup);
        MeetupEvent events = retrieveData(eventUrl, MeetupEvent.class);

        String meetupId = getMeetupIDByDate( meetupDate, events );

        if( meetupId != "-1") {
            String rsvpUrl = meetupUrlGenerator.getRsvpUrl(meetupId);
            RsvpList rsvps = retrieveData( rsvpUrl, RsvpList.class );

            guestList = getGuestList( rsvps );
        }

        return guestList;
    }

    /**
     *
     * @param rsvps
     * @return
     */
    private GuestList getGuestList(RsvpList rsvps) {
        GuestList guestList = new GuestList();

        List<Guest> guests = rsvps.getRsvps().stream()
                .filter( x -> x.isAttending())
                .map( x -> new Guest( x.getMember().getName(), x.getNumGuests()))
                .collect(Collectors.toList());

        guestList.setGuests( guests );
        return guestList;
    }

    /**
     * Assumes group wont have 2 meetups in the same day
     * @param meetupDate
     * @param events
     * @return
     */
    private String getMeetupIDByDate(String meetupDate, MeetupEvent events) {

        LocalDate dateToFind = LocalDate.parse( meetupDate );

        String id = events.getEvent().stream()
                .filter( e -> dateToFind.isEqual( e.getDate() ))
                .map(e -> e.getId())
                .findFirst()
                .orElse("-1");

        return id;
    }

    /**
     *
     * move it ot of this class
     * @param meetupUrl
     * @return
     * @throws IOException
     */
    private <T> T retrieveData( String meetupUrl, Class<T> type ) throws IOException{
        String json= "";

        URL url = new URL( meetupUrl );

        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();

        try( BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(connection.getInputStream())) ) {
            String response;

            while ((response = bufferedReader.readLine()) != null) {
                json += response;
            }
        } catch( IOException ex){
            ex.printStackTrace();
        }

        return gson.fromJson(json, type);
    }
}
