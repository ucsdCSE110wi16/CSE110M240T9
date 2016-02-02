package ucsd.fungineers.eventhunters;

import java.util.ArrayList;

public class Event {

    public Event (ArrayList<AttendeeComponent> attendees, int eventID,
                  RestrictionStatus restrictionStatus, Genre genre) {
        this.attendees = attendees;
        //this.host = host; remember to put this back in the event ctor
        this.eventID = eventID;
        this.restrictionStatus = restrictionStatus;
        this.genre = genre;
    }

    //The list of people attending the event.
    private ArrayList<AttendeeComponent> attendees;

    //The host who created the event.
    private HostComponent host;

    //The id that the event has in the database.
    private int eventID;

    //How restricted an event is. Check for explanation in User.java
    private RestrictionStatus restrictionStatus;

    //What kind of event it is. Check for explanation at bottom of this file.
    private Genre genre;

    //Setters and getters? Might be easier to make things public.
    public ArrayList<AttendeeComponent> getAttendees() {
        return attendees;
    }

    public void setAttendees(ArrayList<AttendeeComponent> attendees) {
        this.attendees = attendees;
    }

    public HostComponent getHost() {
        return host;
    }

    public void setHost(HostComponent host) {
        this.host = host;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public RestrictionStatus getRestrictionStatus() {
        return restrictionStatus;
    }

    public void setRestrictionStatus(RestrictionStatus restrictionStatus) {
        this.restrictionStatus = restrictionStatus;
    }

    public Genre getGenre() {

        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}

/**
 * This enum keeps track of the types of events possible. One per event!
 * TODO: expand to tag system. Event can have multiple tags. Easier to search.
 */
enum Genre{
    MUSIC, PARTY;
}