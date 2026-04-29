package uk.edu.le.part2.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SafetyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCheck(SafetyCheck safetyCheck);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDefect(Defect defect);

    @Update
    void updateCheck(SafetyCheck safetyCheck);

    @Query("SELECT * FROM Defect WHERE checkId = :checkId")
    LiveData<List<Defect>> getDefectsForCheck(long checkId);

    @Query("SELECT * FROM SafetyCheck ORDER BY checkDate DESC")
    LiveData<List<SafetyCheck>> getAllChecks();

    @Query("SELECT * FROM SafetyCheck WHERE checkId = :checkId")
    LiveData<SafetyCheck> getCheckById(long checkId);

    @Delete
    void deleteCheck(SafetyCheck safetyCheck);

}
