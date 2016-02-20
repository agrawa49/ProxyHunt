package edu.purdue.agrawa49.proxyhunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PostRequest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_request);

        final AcceptRequest acceptRequest = new AcceptRequest();
        Button b1 = (Button) findViewById(R.id.submit);
        EditText e1 = (EditText) findViewById(R.id.course);
        EditText e2 = (EditText) findViewById(R.id.time);
        EditText e3 = (EditText) findViewById(R.id.location);

        final String course = String.valueOf(e1.getText());
        final String time = String.valueOf(e2.getText());
        final String loc = String.valueOf(e3.getText());

        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                acceptRequest.prepareListData(course, time, loc);
                Intent next = new Intent(getApplicationContext(), AcceptRequest.class);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
