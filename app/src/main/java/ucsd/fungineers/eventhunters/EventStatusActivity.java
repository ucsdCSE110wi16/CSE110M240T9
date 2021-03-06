package ucsd.fungineers.eventhunters;

    import android.app.AlertDialog;
    import android.content.DialogInterface;
    import android.content.Intent;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.support.v7.widget.SwitchCompat;
    import android.util.Log;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.CheckBox;
    import android.widget.CompoundButton;
    import android.widget.EditText;
    import android.widget.ImageView;
    import android.widget.RatingBar;
    import android.widget.Switch;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.parse.ParseException;

    import java.text.SimpleDateFormat;
    import java.util.List;
    import java.util.Locale;

    public class EventStatusActivity extends AppCompatActivity {

        private TextView title;
        private TextView subtitle;
        private TextView date;
        private TextView time;
        private TextView location;
        private TextView genre;
        private TextView restriction;
        private TextView description;
        private TextView attendeeNum;
        private CheckBox switch1;
        private RatingBar eventRatingBar;

        //private boolean editable = false;
        private static final String TAG = EventStatusActivity.class.getSimpleName();
        private static final SimpleDateFormat mDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.US);
        private static final SimpleDateFormat mTimeFormat = new SimpleDateFormat("h:mm aa",Locale.US);
        private Event event;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_attendee_event_status);

        event = (Event) getIntent().getSerializableExtra(getString(R.string.KEY_EVENT_OBJ));
        boolean attending = (Boolean) getIntent().getSerializableExtra("attending");
        //editable = getIntent().getBooleanExtra(getString(R.string.KEY_EVENT_EDITABLE), false);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = (TextView) findViewById(R.id.event_title);
        subtitle = (TextView) findViewById(R.id.event_subtitle);
        date = (TextView) findViewById(R.id.event_date);
        time = (TextView) findViewById(R.id.event_time);
        location = (TextView) findViewById(R.id.event_location);
        genre = (TextView) findViewById(R.id.event_genre);
        restriction = (TextView) findViewById(R.id.event_restriction);
        description = (TextView) findViewById(R.id.event_description);
        attendeeNum = (TextView) findViewById(R.id.event_attendeeNum);
        switch1 = (CheckBox) findViewById(R.id.Switch);
        //eventRatingBar = (RatingBar) findViewById(R.id.event_rating_bar);

        title.setText(event.getName());
        //subtitle.setText("Hosted by " + event.getHost());
        date.setText(mDateFormat.format(event.getDate().getTime()));
        time.setText(mTimeFormat.format(event.getDate().getTime()));
        location.setText(event.getLocation());
        genre.setText(event.getGenre().toString());
        restriction.setText(event.getRestrictionStatus().toString());
        description.setText(event.getDescription());
        attendeeNum.setText("" + event.getAttendees().size());
        switch1.setChecked(attending);

        /*eventRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(EventStatusActivity.this, String.valueOf(ratingBar.getRating()),
                        Toast.LENGTH_SHORT).show();
            }
        });
*/
        ImageView closeButton = (ImageView) findViewById(R.id.close_activity_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        CheckBox repeatChkBx = ( CheckBox ) findViewById( R.id.Switch );
        repeatChkBx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    try {
                        System.instance.addEventsToUser(System.EventType.ATTENDING,event.getEventID());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else
                {
                    try {
                     System.instance.removeEventsFromUser(System.EventType.ATTENDING,event.getEventID());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.event_status_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_event_home:
                //Toast.makeText(EventStatusActivity.this, "TODO jump to home", Toast.LENGTH_SHORT).show();
                Log.i("clicks", "Home");
                DialogInterface.OnClickListener home_clickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface d, int id) {
                        switch (id) {
                            case DialogInterface.BUTTON_POSITIVE:
                                Intent i = new Intent(EventStatusActivity.this, AttendingEvents.class);
                                startActivity(i);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder home_b = new AlertDialog.Builder(this);
                home_b.setMessage("Are you sure you want to go to the Home Page?")
                        .setTitle("Home Page")
                        .setPositiveButton("Yes", home_clickListener)
                        .setNegativeButton("No", home_clickListener)
                        .show();
                break;
            default:
                break;
        }

        return true;
    }
}
