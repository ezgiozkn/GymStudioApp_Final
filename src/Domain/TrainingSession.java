package Domain;

import java.io.Serializable;

public class TrainingSession implements Serializable {

    private String sessionId;
    private String day;
    private String hour;
    private String packageID;

    public TrainingSession(String sessionId, String day, String hour, String packageID) {
        this.sessionId = sessionId;
        this.day = day;
        this.hour = hour;
        this.packageID = packageID;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getPackage() {
        return packageID;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }
}
