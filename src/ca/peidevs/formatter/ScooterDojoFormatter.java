package ca.peidevs.formatter;

import ca.peidevs.model.scooter.Guest;
import ca.peidevs.model.scooter.GuestList;

/**
 * Dojo Format is array with comma separated names
 */
public class ScooterDojoFormatter extends ScooterFormatter {

    @Override
    public String format(GuestList guestList) {
        String result = "var ATTENDEE_LIST = [ ";

        for(Guest guest : guestList.getGuests() ){
            result = result.concat("\"" + guest.getName() + "\", ");

            for(int guestNum=1; guestNum <= guest.getNumGuests(); guestNum++){
                result = result.concat("\"" + guest.getName() + " +" + guestNum + "\", ");
            }
        }

        result = result.substring(0, result.length()-2);
        result = result.concat( " ];" );
        return result;
    }
}
