package ucsd.fungineers.eventhunters;

import android.util.Log;

import java.util.ArrayList;

/**
 * This class will keep track of the attendee data.
 * For example, their upcoming events
 */
public class AttendeeData{
    //Upcoming events this user is attending.
    ArrayList<Event> upcomingEventList;

    //Previous attended events.
    ArrayList<Event> pastEventList;

    //What the status of the users age is. Attendees cannot view or join events that
    // are higher restriction
    RestrictionStatus restrictionStatus;

    //Rating of the user as an attendee
    float rating;

    //A reference to the user object to get the rest of the data.
    User user;

    /**
     * This method adds this attendee to the event, provided their restriction level is adequate.
     * Note: Even though the event is changed, System must be informed to write to the database.
     * Use Main.system to access.
     * Haven't fully decided how to do that.
     * @param eventToJoin The event to join
     * @return true if successful. May replace with multiple error system.
     */
    public boolean joinEvent(Event eventToJoin)
    {
        //Use Main.system to update.
        return true;
    }

    /**
     * Test ctor to make attendees. Perhaps for new users?
     * Really though you should be using the other ctor.
     */
    public AttendeeData()
    {
        super();

    }

    /**
     * A contructor that takes in an id and makes calls to System to get the object.
     * @param hostID
     */
    public AttendeeData(int hostID)
    {
        //Read data and then call the multiarg ctor.
    }

    /**
     * Attendee ctor. Called from the hostID ctor to create AttendeeData from database.
     */
    public AttendeeData(ArrayList<Event>upcomingEventList,
                        ArrayList<Event> pastEventList,
                        RestrictionStatus restrictionStatus,
                        float rating,
                        User user)
    {
        this.upcomingEventList = upcomingEventList;
        this.pastEventList = pastEventList;
        this.restrictionStatus = restrictionStatus;
        this.rating = rating;
        this.user = user;
    }
}
