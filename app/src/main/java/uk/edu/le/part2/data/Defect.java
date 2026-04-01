package uk.edu.le.part2.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(foreignKeys = {@ForeignKey(
        entity = SafetyCheck.class,
        parentColumns = "checkId",
        childColumns = "defectId",
        onDelete = ForeignKey.CASCADE
)})
public class Defect {
/* Child class
 */

    @PrimaryKey
    public int defectId;
    public String description;
    public String severity;
    public int checkId;

}
