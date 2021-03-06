package edu.purdue.agrawa49.proxyhunt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {

    private static final String Firebase_url = "https://blinding-fire-6276.firebaseio.com";
    private Firebase firebaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        firebaseRef = new Firebase(Firebase_url);
        Button b1 = (Button) findViewById(R.id.accept);
        Button b2 = (Button) findViewById(R.id.request);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent next = new Intent(getApplicationContext(), PostRequest.class);
                startActivity(next);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                Intent next = new Intent(getApplicationContext(), AcceptRequest.class);
                startActivity(next);
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
    @Override
    public void onBackPressed() {
    }
}
