package com.varunb.drawertest;

/**
 * Created by rajeevbansal on 5/28/15.
 */
public class Session {
    private int _id;
    private String date;
    private String duration;

    public Session() {}

    public Session(int _id, String date, String duration) {
        this._id = _id;
        this.date = date;
        this.duration = duration;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
