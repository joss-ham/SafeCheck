package uk.edu.le.part2.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SafetyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCheck(SafetyCheck safetyCheck);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDefect(Defect defect);

    @Query("SELECT * FROM Defect WHERE checkId = :checkId")
    LiveData<List<Defect>> getDefectsForCheck(long checkId);

}
