package ca.peidevs.service;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import ca.peidevs.model.meetup.event.MeetupEvent;
import ca.peidevs.model.meetup.rsvp.RsvpList;
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
