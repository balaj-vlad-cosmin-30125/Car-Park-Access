package isp.lab8.carparkaccess;

import java.util.Date;

public class Action {
    Car c;
    Date entryTime;
    long periodInSeconds;
    String message;

    public Date getEntryTime() {
        return entryTime;
    }

    public long getPeriodInSeconds() {
        return periodInSeconds;
    }

    public String getMessage() {
        return message;
    }

    public Car getC() {
        return c;
    }

    public void setExitTime(long periodInSeconds) {
        this.periodInSeconds = periodInSeconds;
    }

    public Action(Car c, Date entryTime, long periodInSeconds, String message) {
        this.c = c;
        this.entryTime = entryTime;
        this.periodInSeconds = periodInSeconds;
        this.message = message;

    }


}