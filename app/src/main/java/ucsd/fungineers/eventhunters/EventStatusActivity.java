package ucsd.fungineers.eventhunters;

    import android.content.Intent;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.ImageView;
    import android.widget.RatingBar;
    import android.widget.TextView;
    import android.widget.Toast;

    import java.text.SimpleDateFormat;
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
        private RatingBar eventRatingBar;

        private boolean editable = false;
        private static final String TAG = EventStatusActivity.class.getSimpleName();
        private static final SimpleDateFormat mDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy", Locale.US);
        private static final SimpleDateFormat mTimeFormat = new SimpleDateFormat("h:mm aa",Locale.US);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_status);

        Event event = (Event) getIntent().getSerializableExtra(getString(R.string.KEY_EVENT_OBJ));
        editable = getIntent().getBooleanExtra(getString(R.string.KEY_EVENT_EDITABLE), false);

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
        eventRatingBar = (RatingBar) findViewById(R.id.event_rating_bar);

        title.setText(event.getName());
        subtitle.setText("Hosted by " + event.getHost());
        date.setText(mDateFormat.format(event.getDate().getTime()));
        time.setText(mTimeFormat.format(event.getDate().getTime()));
        location.setText(event.getLocation());
        genre.setText(event.getGenre().toString());
        restriction.setText(event.getRestrictionStatus().toString());
        description.setText(event.getDescription());

        eventRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                // TODO save the newer set rating for this user
            }
        });

        ImageView closeButton = (ImageView) findViewById(R.id.close_activity_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.event_status_menu, menu);

        if (!editable) {
            menu.removeItem(R.id.action_event_delete);
            menu.removeItem(R.id.action_event_edit);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_event_delete :
                // TODO delete this event
                Toast.makeText(EventStatusActivity.this, "TODO Delete event", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_event_edit :
                // TODO edit this event
                Toast.makeText(EventStatusActivity.this, "TODO Edit event", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_event_home :
                // TODO jump to home
                Toast.makeText(EventStatusActivity.this, "TODO jump to home", Toast.LENGTH_SHORT).show();
                Log.i("clicks", "Home");
                Intent i = new Intent(this, AttendingEvents.class);
                startActivity(i);
                break;
            default:
                break;
        }

        return true;
    }
}
