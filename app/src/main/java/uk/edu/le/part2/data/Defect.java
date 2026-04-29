package uk.edu.le.part2.data;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(
        entity = SafetyCheck.class,
        parentColumns = "checkId",
        childColumns = "checkId",
        onDelete = ForeignKey.CASCADE
)})
public class Defect {
    /* Child class
     */

    @PrimaryKey(autoGenerate = true)
    private int defectId;
    private String description;
    private String severity;
    private int checkId;

    // Constructor
    public Defect(int defectId, String description, String severity, int checkId) {
        this.defectId = defectId;
        this.description = description;
        this.severity = severity;
        this.checkId = checkId;
    }

    // Getters and Setters
    public int getCheckId() {
        return checkId;
    }

    public void setCheckId(int checkId) {
        this.checkId = checkId;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDefectId() {
        return defectId;
    }

    public void setDefectId(int defectId) {
        this.defectId = defectId;
    }
}