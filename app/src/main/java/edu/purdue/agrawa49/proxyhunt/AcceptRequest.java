package edu.purdue.agrawa49.proxyhunt;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class AcceptRequest extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_accept_request);
//    }
//    private ExpandableListAdapter listAdapter;
//    private ExpandableListView expListView;
    List<SendInfo> request = new ArrayList<>();
    List<String> courselist=new ArrayList<>();
    private SwipeRefreshLayout swipeContainer;
    ArrayAdapter<String> adapter;
    TableView tv = new TableView();




//    private HashMap<String, List<String>> listDataChild;

    private static final String Firebase_url = "https://blinding-fire-6276.firebaseio.com";
    private Firebase firebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept_request);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                //prepareListData();
                onResume();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //prepareListData();

    }

    @Override
    protected void onResume()
    {
        super.onResume();
        firebaseRef = new Firebase(Firebase_url);
       prepareListData();
      //  updateList();

    }

    public  void prepareListData() {
        firebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (adapter != null) {
                    adapter.clear();
                }                //  System.out.println("There are " + snapshot.getChildrenCount() + " blog posts");
                //Log.e("aviral", "on data change entered");
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    SendInfo info = postSnapshot.getValue(SendInfo.class);
                    request.add(info);
                    int c = 0;
                    for (int i = 0; i < courselist.size(); i++) {
                        if (courselist.get(i).equalsIgnoreCase(info.getCourse()))
                            c++;
                    }
                    if(c == 0)
                    courselist.add(info.getCourse());
                        //Log.e("aviral", info.toString());

                    /**
                     * custom adapter List<SendInfo>
                     *     getView
                     *             create rows taking the data SendInfo
                     */
                }
                updateList();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }


    public void updateList() {
        //String[] myitems = {"sfsf", "dawda"};
//        String[] myitems = new String[courselist.size()];
//        for (int i = 0; i < courselist.size(); i++) {
//            myitems[i] = courselist.get(i);
//        }
//        if (adapter != null && swipeContainer.isRefreshing()) {
//            adapter.clear();
//        }
        //prepareListData();
    adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, courselist);
        ListView list = (ListView) findViewById(R.id.listViewAccept);
        list.setAdapter(adapter);
        swipeContainer.setRefreshing(false);
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent next = new Intent(getApplicationContext(), TableView.class);
                startActivity(next);
                String name = courselist.get(position);
                //String num = tv.addList(name, request);
                //firebaseRef.child(num).removeValue();
                prepareListData();
            }
        });

        //e1.setText(course);
    }
//    TextView e1 = (TextView) findViewById(R.id.cv);
//    public void updateList(String course) {
//        e1.setText(course);
//    }

        // get the listview
        // expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        //prepareListData();

        // listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
     //   expListView.setAdapter(listAdapter);
    }
    /*
     * Preparing the list data
     */
//    public static void prepareListData(String c, String t, String l) {
//        listDataHeader = new ArrayList<String>();
//        listDataChild = new HashMap<String, List<String>>();
//
//        // Adding child data
//        listDataHeader.add(c);
//        // Adding child data
//        List<String> x = new ArrayList<String>();
//        x.add(t);
////        top250.add("The Godfather");
////        top250.add("The Godfather: Part II");
////        top250.add("Pulp Fiction");
////        top250.add("The Good, the Bad and the Ugly");
////        top250.add("The Dark Knight");
////        top250.add("12 Angry Men");
////
////        List<String> nowShowing = new ArrayList<String>();
////        nowShowing.add("The Conjuring");
////        nowShowing.add("Despicable Me 2");
////        nowShowing.add("Turbo");
////        nowShowing.add("Grown Ups 2");
////        nowShowing.add("Red 2");
////        nowShowing.add("The Wolverine");
////
////        List<String> comingSoon = new ArrayList<String>();
////        comingSoon.add("2 Guns");
////        comingSoon.add("The Smurfs 2");
////        comingSoon.add("The Spectacular Now");
////        comingSoon.add("The Canyons");
////        comingSoon.add("Europa Report");
//
//        listDataChild.put(listDataHeader.get(0), x); // Header, Child data
////        listDataChild.put(listDataHeader.get(1), nowShowing);
////        listDataChild.put(listDataHeader.get(2), comingSoon);
//    }

