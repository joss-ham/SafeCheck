package uk.edu.le.part2.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class SafetyCheck {
    /* The parent entity which will sit in recycler cards
     */
    @PrimaryKey
    private int checkId;
    private Date checkDate;
    private String vehicleRegistration;
    private String driverName;
    private String overallStatus;

    public SafetyCheck(int checkId, Date checkDate, String vehicleRegistration, String driverName, String overallStatus){
        this.checkId = checkId;
        this.checkDate = checkDate;
        this.vehicleRegistration = vehicleRegistration;
        this.driverName = driverName;
        this.overallStatus = overallStatus;
    }

    public String getOverallStatus() {
        return overallStatus;
    }

    public void setOverallStatus(String overallStatus) {
        this.overallStatus = overallStatus;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getVehicleRegistration() {
        return vehicleRegistration;
    }

    public void setVehicleRegistration(String vehicleRegistration) {
        this.vehicleRegistration = vehicleRegistration;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }
}


