package ucsd.fungineers.eventhunters;

import java.util.ArrayList;

/**
 * This class will keep track of the host data.
 * For example, their upcoming events.
 */
public class HostData{

    //Upcoming events this user is hosting.
    ArrayList<Event> upcomingEventList;

    //Previous hosted events.
    ArrayList<Event> pastEventList;

    //What the status of the users age is. Hosts cannot make events that are higher restriction
    RestrictionStatus restrictionStatus;

    //Rating of the user as a host
    float rating;

    //A reference to the user object to get the rest of the data.
    User user;

    /**
     * This method creates a new event. The host's upcoming list must be updated, and the system
     * must be notified to write to the database. Use Main.system to access.
     * @return true if successful. May replace with multiple error system.
     */
    public boolean createEvent()
    {
        //Use Main.System to update the list.
        return true;
    }

    /**
     * Test ctor to make attendees. Perhaps for new users?
     * Really though you should be using the other ctor.
     */
    public HostData() {
    }

    /**
     * A contructor that takes in an id and makes calls to System to get the object.
     * @param hostID
     */
    public HostData(int hostID)
    {
        //Read data and then call the multiarg ctor.
    }

    /**
     * HostData ctor. Called from the hostID ctor to create HostData from database.
     */
    public HostData(ArrayList<Event> upcomingEventList,
                    ArrayList<Event> pastEventList,
                    RestrictionStatus restrictionStatus,
                    float rating,
                    User user) {
        this.upcomingEventList = upcomingEventList;
        this.pastEventList = pastEventList;
        this.restrictionStatus = restrictionStatus;
        this.rating = rating;
        this.user = user;
    }
}
