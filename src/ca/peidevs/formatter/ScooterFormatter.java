package ca.peidevs.formatter;

import ca.peidevs.model.meetup.rsvp.Rsvp;
import ca.peidevs.model.meetup.rsvp.RsvpList;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ScooterFormatter {

    private final RsvpList rsvpList;

    public ScooterFormatter( RsvpList rsvpList){
        this.rsvpList = rsvpList;
    };

    /**
     * Formats the RSVP List in Dojo Scooter Format
     * @return
     */
    public String formatDojo(){
        String result = "var ATTENDEE_LIST = [ ";

        for(String guest : flattenRsvpList()){
            result = result.concat("\"" + guest + "\", ");
        }

        result = result.substring(0, result.length()-2);
        result = result.concat( " ];" );
        return result;
    };

    /**
     * Formats the RSVP List in Angular Dojo Format
     * @return
     */
    public String formatAngular(){
        String result = "[ ";

        for(String guest : flattenRsvpList() ){
            result = result.concat("{ \"name\" : \"" + guest + "\" }, ");
        }

        result = result.substring(0, result.length()-2);
        result = result.concat( " ]" );
        return result;
    };


    private List<String> flattenRsvpList() {
        List<String> names = new ArrayList<>();

        for(Rsvp rsvp : rsvpList.getRsvps()){
            names.add( rsvp.getMember().getName() );

            for(int i=1; i <= rsvp.getNumGuests(); i++ ){
                names.add( rsvp.getMember().getName() + " +" + i );
            }
        }

        return names;
    }
}
