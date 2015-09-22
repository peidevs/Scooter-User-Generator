package ca.peidevs.formatter;

import ca.peidevs.model.Guest;
import ca.peidevs.model.GuestList;

/**
 * each user in the array has their own object. name value pairs
 * [ { name : 'foo' } ]
 */
public class ScooterAngularFormatter extends ScooterFormatter {
    @Override
    public String format(GuestList guestList) {
        String result = "[ ";

        for(Guest guest : guestList.getGuests() ){
            result = result.concat("{ \"name\" : \"" + guest.getName() + "\" }, ");

            for(int guestNum=1; guestNum <= guest.getNumGuests(); guestNum++){
                result = result.concat("{ \"name\" : \"" + guest.getName() + " +" + guestNum + "\" }, ");
            }
        }

        result = result.substring(0, result.length()-2);
        result = result.concat( " ];" );
        return result;
    }
}
