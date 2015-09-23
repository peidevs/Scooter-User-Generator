package ca.peidevs.service;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
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

    public MeetupEvent getUpcomingMeetupEvents( String meetupGroup ) throws IOException{
        String eventUrl =  meetupUrlGenerator.generateEventsURL(meetupGroup);
        MeetupEvent events = retrieveData(eventUrl, MeetupEvent.class);

        return events;
    };

    public RsvpList getRsvpList( String meetupId) throws IOException{
        String rsvpUrl = meetupUrlGenerator.getRsvpUrl(meetupId);
        RsvpList rsvpList = retrieveData( rsvpUrl, RsvpList.class );

        return rsvpList;
    };


    /**
     * Move this to RSvpList model?? Does this make sense? RSVP to GuestList is more of a transform then a pull from the model
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
     *
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
