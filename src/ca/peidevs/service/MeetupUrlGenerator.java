package ca.peidevs.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MeetupUrlGenerator {

    private String eventsUrl = "https://api.meetup.com/2/events?key=%s&group_urlname=%s&sign=true";
    private String rsvpUrl = "https://api.meetup.com/2/rsvps?key=%s&event_id=%s&sign=true";

    private String characterSet = "UTF-8";

    private String key = "";

    public MeetupUrlGenerator(String key) {
        this.key = key;
    }

    /**
     * URL Encodes the parameters and builds the URL needed to make the Meetup request
     * @param meetupGroup
     * @return
     */
    public String generateEventsURL( String meetupGroup ){
        String response = "";

        try {
            String getUrl = String.format(eventsUrl,
                    URLEncoder.encode(key, characterSet),
                    URLEncoder.encode(meetupGroup.toLowerCase(), characterSet));

            response = getUrl;
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
    public String getRsvpUrl(String meetupId) {
        String response = "";

        try {
            String getUrl = String.format(rsvpUrl,
                    URLEncoder.encode(key, characterSet),
                    URLEncoder.encode(meetupId, characterSet));

            response = getUrl;
        } catch (UnsupportedEncodingException e) {
            response = "What did you mess up that we can't encode this to " + characterSet + "??";
        }

        return response;
    }
}
