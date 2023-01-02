package Domain;

import java.io.Serializable;
public class TrainingPackage implements Serializable{
    private String packageID;
    private String description;
    private int totalCount;
    private int completedSession;
    private String memberName;

    public TrainingPackage(String packageID, String description, int totalCount, int completedSession, String memberName){
        this.packageID = packageID;
        this.description = description;
        this.totalCount = totalCount;
        this. completedSession = completedSession;
        this.memberName = memberName;
    }

    public String getPackageID(){ return packageID;}
    public String getDescription(){ return description;}
    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCompletedSession() {
        return completedSession;
    }

    public void setCompletedSession(int completedSession) {
        this.completedSession = completedSession;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}
