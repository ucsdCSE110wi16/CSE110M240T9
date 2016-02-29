package ucsd.fungineers.eventhunters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by akshitgupta on 2/28/16.
 */
public class Attendee_Event_Status extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.attendee_event_status);

        Bundle transmitted_Data = getIntent().getExtras();

      /*  if(transmitted_Data != null) {
            LinearLayout l = (LinearLayout) findViewById(R.id.event_status);
            for (int i = 0; i < l.getChildCount(); i++) {
                if(l.getChildAt(i) instanceof TextView)
                {
                    TextView eventInfo = (TextView)l.getChildAt(i);
                    for(int j = 0; j < transmitted_Data.size(); j++)
                    {
                        if(transmitted_Data.getString(eventInfo.get))
                    }
                }
            }
        }*/



        if(transmitted_Data!= null){
            TextView eventName = (TextView) findViewById(R.id.data_Name);
            eventName.setText(eventName.getText() + transmitted_Data.getString("eventName"));
            TextView eventDate = (TextView) findViewById(R.id.data_Date);
            eventDate.setText(eventDate.getText() + transmitted_Data.getString("eventDate"));
            TextView eventLocation = (TextView) findViewById(R.id.data_Location);
            eventLocation.setText(eventLocation.getText() + transmitted_Data.getString("eventLocation"));
            TextView eventRestriction = (TextView) findViewById(R.id.data_Restriction);
            eventRestriction.setText(eventRestriction.getText() + transmitted_Data.getString("eventRestriction"));
            TextView eventDescription = (TextView) findViewById(R.id.data_Description);
            eventDescription.setText(eventDescription.getText() + transmitted_Data.getString("eventDescription"));

        }

    }
    public void button_Click(View view){
        String button_name = ((Button) view).getText().toString();
        if(button_name.equals("Home"))
        {
            Log.i("clicks", "Home");
            Intent i = new Intent(this, Main.class);
            startActivity(i);
        }
        else if (button_name.equals("Going"))
        {
            Log.i("clicks", "Going");

        }
        /*else if (button_name.equals("Not Going"))
        {
            Log.i("clicks", "Not Going");
        }*/
        else if (button_name.equals("Other Stuff"))
        {
            Log.i("clicks", "Other Stuff");
        }
    }
}
