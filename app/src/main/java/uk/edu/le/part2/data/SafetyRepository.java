package uk.edu.le.part2.data;

import android.app.Application;
import androidx.lifecycle.LiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SafetyRepository {

    private SafetyDao safetyCheckDao;
    private ExecutorService executorService;
    private LiveData<List<SafetyCheck>> allChecks;

    public SafetyRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        safetyCheckDao = db.safetyCheckDao();
        executorService = Executors.newSingleThreadExecutor();
        allChecks = safetyCheckDao.getAllChecks();
    }

    //reads

    public LiveData<List<SafetyCheck>> getAllChecks() {
        return allChecks;
    }

    public LiveData<SafetyCheck> getCheckById(long checkId) {
        return safetyCheckDao.getCheckById(checkId);
    }

    public LiveData<List<Defect>> getDefectsForCheck(long checkId) {
        return safetyCheckDao.getDefectsForCheck(checkId);
    }

    //writes

    public void updateCheck(SafetyCheck safetyCheck) {
        executorService.execute(() -> safetyCheckDao.updateCheck(safetyCheck));
    }

    public void insertSafetyCheck(SafetyCheck safetyCheck) {
        executorService.execute(() -> safetyCheckDao.insertCheck(safetyCheck));
    }

    public void insertDefect(Defect defect) {
        executorService.execute(() -> safetyCheckDao.insertDefect(defect));
    }

    public void deleteSafetyCheck(SafetyCheck safetyCheck) {
        executorService.execute(() -> safetyCheckDao.deleteCheck(safetyCheck));
    }
}
