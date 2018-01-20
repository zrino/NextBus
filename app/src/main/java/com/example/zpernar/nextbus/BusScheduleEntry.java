package com.example.zpernar.nextbus;


public class BusScheduleEntry {
    private String time;
    private String from;
    private String to;

    BusScheduleEntry(String time, String from, String to) {
        this.time = time;
        this.from = from;
        this.to = to;
    }

    BusScheduleEntry() {
        ;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
