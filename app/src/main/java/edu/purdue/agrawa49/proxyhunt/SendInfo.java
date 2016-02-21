package edu.purdue.agrawa49.proxyhunt;

import android.widget.EditText;

/**
 * Created by Rohan on 2/20/16.
 */
public class SendInfo {
    private String course;
    private String time;
    private String loc;
    private String cloc;
    private String cellNumber;
    public SendInfo(){
    }
    public SendInfo(String course, String time, String loc, String cloc, String cellNumber) {
        this.course = course;
        this.time = time;
        this.loc = loc;
        this.cloc = cloc;
        this.cellNumber = cellNumber;
    }
    public String getCourse() {
        return course;
    }
    public String getTime() {
        return time;
    }
    public String getLoc() {
        return loc;
    }
    public String getCloc() { return cloc; }
    public String getCellNumber() { return cellNumber; }
}

