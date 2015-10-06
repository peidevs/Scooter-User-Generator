package ca.peidevs.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * This class is very generic and repetitive. We could remove this class.
 */
public class MeetupUrlGenerator {

    private String eventsUrl = "https://api.meetup.com/2/events?key=%s&group_urlname=%s";
    private String rsvpUrl = "https://api.meetup.com/2/rsvps?key=%s&event_id=%s";
    private String eldersUrl = "https://api.meetup.com/2/profiles?key=%s&group_urlname=%s&role=leads";

    private String characterSet = "UTF-8";

    /**
     * URL Encodes the parameters and builds the URL needed to make the Meetup request
     * @param meetupGroup
     * @return
     */
    public String generateEventsURL( String meetupGroup, String key ){
        String response = "";

        try {
            response = String.format(eventsUrl,
                    URLEncoder.encode(key, characterSet),
                    URLEncoder.encode(meetupGroup.toLowerCase(), characterSet));
        } catch (UnsupportedEncodingException e) {
            response = "What did you mess up that we can't encode this to " + characterSet + "??";
        }

        return response;
    }

    /**
     *
     * @param meetupId
     * @return
     */
    public String getRsvpUrl(String meetupId, String key) {
        String response = "";

        try {
            response = String.format(rsvpUrl,
                    URLEncoder.encode(key, characterSet),
                    URLEncoder.encode(meetupId, characterSet));
        } catch (UnsupportedEncodingException e) {
            response = "What did you mess up that we can't encode this to " + characterSet + "??";
        }

        return response;
    }

    public String getEldersUrl( String meetupName, String key ){
        String response = "";

        try {
            response = String.format(eldersUrl,
                    URLEncoder.encode(key, characterSet),
                    URLEncoder.encode(meetupName, characterSet));
        } catch (UnsupportedEncodingException e) {
            response = "What did you mess up that we can't encode this to " + characterSet + "??";
        }

        return response;
    }
}
