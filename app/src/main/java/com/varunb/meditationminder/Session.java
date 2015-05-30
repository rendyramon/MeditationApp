package com.varunb.meditationminder;


/**
 * Created by rajeevbansal on 5/28/15.
 */
public class Session implements Comparable {

    private int _id;
    private String date;
    private String duration;
    private long machine_date;
    private int minutes;

    public Session() {
    }

    public Session(String date, String duration, long mach_date, int minutes) {
        super();
        this.date = date;
        this.duration = duration;
        this.machine_date = mach_date;
        this.minutes = minutes;
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

    public long getMachineDate() {
        return machine_date;
    }

    public void setMachineDate(long machine_date) {
        this.machine_date = machine_date;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    @Override
    public int compareTo(Object another) {
        long compareDate = ((Session) another).getMachineDate();
        return Long.compare(compareDate, this.machine_date);
    }
}
