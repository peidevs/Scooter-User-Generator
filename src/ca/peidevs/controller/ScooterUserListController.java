package ca.peidevs.controller;

import ca.peidevs.model.meetup.elder.Elders;
import ca.peidevs.model.meetup.event.Event;
import ca.peidevs.model.meetup.event.MeetupEvent;
import ca.peidevs.model.meetup.rsvp.Rsvp;
import ca.peidevs.model.meetup.rsvp.RsvpList;
import ca.peidevs.service.MeetupService;
import ca.peidevs.view.IView;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ScooterUserListController {

    private final IView view;
    private final MeetupService meetupService;

    public ScooterUserListController( IView view, MeetupService service ){
        this.view = view;
        this.meetupService = service;
    }

    public void retrieveMeetupAttendees(String groupName, LocalDate eventDate, String key){

        try{
            MeetupEvent events = meetupService.getUpcomingMeetupEvents( groupName, key );
            Event event = events.getEventByDate(eventDate);

            if( event.getId() != null ){
                view.setMeetupName(event.getName());
                view.setMeetupLocation(event.getVenue());

                RsvpList rsvpList = meetupService.getRsvpList( event.getId(), key );
                Elders elders = meetupService.getEldersList(groupName, key);

                RsvpList attendingMembers = filterEldersFromRsvp(rsvpList, elders);

                view.setUserList(attendingMembers);
            } else {
                view.setMeetupName("No Meetup Found");
                view.setMeetupLocation(null);
                view.setUserList(null);
            }
        } catch( Exception ex){
            view.setMeetupName( "Error loading meetup data");
        }
    }

    private RsvpList filterEldersFromRsvp(RsvpList rsvpList, Elders elders) {
        RsvpList result = new RsvpList();

        List<Rsvp> collect = rsvpList.getRsvps().stream().filter(member -> {
            return elders.getResults().stream().noneMatch(elder -> {
                return elder.getMemberId() == member.getMember().getMemberId();
            });
        }).collect(Collectors.toList());

        result.setRsvps(collect );

        return result;
    }
}
