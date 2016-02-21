package edu.purdue.agrawa49.proxyhunt;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    //TableView tv = new TableView();




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
        request.clear();
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
                   // int c = 0;
                   // for (int i = 0; i < courselist.size(); i++) {
                    //    if (courselist.get(i).equalsIgnoreCase(info.getCourse()))
                          //  c++;
                  //  }
                   // if(c == 0)
                    courselist.add("Name:\t" + info.getCourse() + "\tLocation:\t" + info.getCloc() +
                            "\tClass time:\t" + info.getTime());
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
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AcceptRequest.this);
                builder.setTitle("Confirmation")
                        .setMessage("Do you accept this request?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlertDialog.Builder builder1 = new AlertDialog.Builder(AcceptRequest.this);
                                builder1.setTitle("Details:")
                                        .setMessage("Course:\t" + request.get(position).getCourse() + "\nLocation of class:\t"
                                                + request.get(position).getCloc() + "\nTime:\t"
                                                + request.get(position).getTime() + "\nRequester's location:\t"
                                                + request.get(position).getLoc() + "\nContact #:\t"
                                                + request.get(position).getCellNumber())
                                        .setCancelable(false)
                                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                request.remove(position);
                                                firebaseRef.removeValue();
                                                for (int i = 0; i < request.size(); i++) {
                                                    String course = request.get(i).getCourse();
                                                    String time = request.get(i).getTime();
                                                    String loc = request.get(i).getLoc();
                                                    String cloc = request.get(i).getCloc();
                                                    String num = request.get(i).getCellNumber();
                                                    firebaseRef.push().setValue(new SendInfo(course, time, loc, cloc, num));
                                                }
                                            }
                                        });
                                AlertDialog alertDialog1 = builder1.create();
                                alertDialog1.show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                prepareListData();
                //       System.out.println(request.toArray().toString());
//                for (int i = 0; i < request.size(); i++) {
//                    SendInfo si = new SendInfo(request.get(i).getCourse(), request.get(i).getTime(), request.get(i).getLoc(), request.get(i).getCloc(), request.get(i).getCellNumber());
//                   firebaseRef.push().setValue(si);

//                }
            }
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent next = new Intent(getApplicationContext(), TableView.class);
//                startActivity(next);
//                String name = courselist.get(position);
//                //String num = tv.addList(name, request);
//                //firebaseRef.child(num).removeValue();
//                prepareListData();
//            }
        });
    }
}