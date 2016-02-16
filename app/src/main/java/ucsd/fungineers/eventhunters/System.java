package ucsd.fungineers.eventhunters;

import android.app.Activity;
import android.location.Location;
import android.util.Log;

import com.facebook.FacebookSdk;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class will communicate with the database and will be how to go from id to object.
 * Remember how the last PA, the tree on disk worked for CSE 12? It'll be kinda like that.
 *
 * Use main.System to access.
 */
public class System {

    static System instance;

    static ParseUser currentUser;

    //If there is a connection to the database, this variable will be true. If not active, any calls
    //will return null.
    boolean connectionEstablished = false;

    //Keeps track of every event ever. Soon to be replaced by database.
    public ArrayList<Event> tempEventList;


    //Keeps track of every user ever. Soon to be replaced by database.
    public ArrayList<User> tempUserList;

    /**
     * This constructor will start a connection to the database and set the connectionEstablished
     * variable.
     */
    public System(Activity activity)
    {
        //How to print example. In logcat, search your label.
        Log.d("test", "System created!");
        tempEventList = new ArrayList<Event>();
        tempUserList  = new ArrayList<User>();
        connect();
        if(instance == null) {
            fbLogin(activity);
        }
        instance = this;





    }

    /**
     * This constructor is used on login. It will populate the user data for the current user.
     * @param userID The user id to start populating things with. Saved on device to keep user
     *               logged in.
     */
    public System(int userID)
    {

    }

    /**
     * Try to connect to the database if not already connected.
     * @return
     */
    public boolean connect()
    {
        connectionEstablished = true;
        return connectionEstablished;
    }

    /**
     * Communicates with the database to get an event's data.
     * @param eventID
     * @return
     */
    public Event getEvent(int eventID){return null;}

    /**
     * Communicates with the database to create a user's data.
     * @param userID
     * @return
     */
    public User getUser (int userID) {return null;}

    public void fbLogin (Activity activity)
    {
        //Parse.enableLocalDatastore(this);
        Parse.initialize(activity);
        FacebookSdk.sdkInitialize(activity.getApplicationContext());
        ParseFacebookUtils.initialize(activity.getApplicationContext() );

        //ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        ParseACL.setDefaultACL(defaultACL, true);

        defaultACL.setPublicWriteAccess(true);
        ParseFacebookUtils.logInWithReadPermissionsInBackground(activity, null, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                if (user == null) {
                    Log.d("MyApp", "Uh oh. The user cancelled the Facebook login.");
                } else if (user.isNew()) {
                    Log.d("MyApp", "User signed up and logged in through Facebook!");
                    currentUser = user;
                    currentUser.put("AttendingEvents", new ArrayList<Integer>());
                    currentUser.put("HostingEvents", new ArrayList<Integer>());
                    currentUser.saveInBackground();
                } else {
                    Log.d("MyApp", "User logged in through Facebook!");
                    currentUser = user;
                }
                testAddEvent();

            }
        });
    }

    public List<Integer> getEvents (EventType type)
    {
        List<Integer> array = new ArrayList<Integer>();
        if(type == EventType.ATTENDING)
        {
            array = currentUser.getList("AttendingEvents");
        }
        else
        {
            array = currentUser.getList("HostingEvents");
        }


        return array;
    }

    public void addEvents (EventType type, int eventID)
    {
        List<Integer> array = getEvents(type);

        array.add(eventID);

        if(type == EventType.ATTENDING)
        {
            currentUser.put("AttendingEvents", array);
        }
        else
        {
            currentUser.put("HostingEvents", array);
        }
        currentUser.saveInBackground();
    }

    enum EventType {HOSTING, ATTENDING};

    public void createEvent(String title, Date date, RestrictionStatus status, Location location, Genre genre)
    {
        ParseObject event = new ParseObject("Events");
        event.put("Title", title);
    }

    public void testAddEvent() {
        Log.d("test", "add event");
        addEvents(EventType.HOSTING, 101);


    }


}
