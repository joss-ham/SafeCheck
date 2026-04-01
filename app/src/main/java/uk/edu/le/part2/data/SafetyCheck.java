package uk.edu.le.part2.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class SafetyCheck {
    /* The parent entity
     */
    @PrimaryKey
    public int checkId;
    public Date date;
    public String vehicleRegistration;
    public String driverName;
    public String overallStatus;
}
