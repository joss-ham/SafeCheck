package uk.edu.le.part2.data;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {SafetyCheck.class, Defect.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract SafetyDao safetyCheckDao();


    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) { //only build if singleton
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "safecheck_database"
            ).build();
        }
        return instance;
    }
}
