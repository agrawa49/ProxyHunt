package edu.purdue.agrawa49.proxyhunt;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class TableView extends AppCompatActivity {

    ArrayAdapter<String> adapter;
    String num = "";
    List<String> details = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);
    }

    public String addList(String name, final List<SendInfo> info) {
        for (int i = 0; i < info.size(); i++) {
            SendInfo si = info.get(i);
            if (name.equalsIgnoreCase(si.getCourse()))
                details.add(si.getCloc() + "\t" + si.getTime() + "\t" + si.getLoc() + "\t" + si.getCellNumber());
        }
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, details);
        ListView list = (ListView) findViewById(R.id.allInfo);
        list.setAdapter(adapter);
        System.out.println("fdsaw");

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                builder.setTitle("Confirmation");
                builder.setMessage("Do you accept this request?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int index = details.get(position).lastIndexOf("\t");
                                num = details.get(position).substring(index);
                                details.remove(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    return num;
    }
}
