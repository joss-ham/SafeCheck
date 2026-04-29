package uk.edu.le.part2;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import uk.edu.le.part2.data.Defect;
import uk.edu.le.part2.data.SafetyCheck;
import uk.edu.le.part2.data.SafetyRepository;

public class SafetyViewModel extends AndroidViewModel {

    private SafetyRepository repository;
    private LiveData<List<SafetyCheck>> allChecks;

    public SafetyViewModel(Application application) {
        super(application);
        repository = new SafetyRepository(application);
        allChecks = repository.getAllChecks();
    }

    public LiveData<List<SafetyCheck>> getAllChecks() {
        return allChecks;
    }

    public LiveData<SafetyCheck> getCheckById(int checkId) {
        return repository.getCheckById(checkId);
    }

    public LiveData<List<Defect>> getDefectsForCheck(int checkId) {
        return repository.getDefectsForCheck(checkId);
    }

    public void insertSafetyCheck(SafetyCheck safetyCheck) {
        repository.insertSafetyCheck(safetyCheck);
    }

    public void insertDefect(Defect defect) {
        repository.insertDefect(defect);
    }

    public void deleteSafetyCheck(SafetyCheck safetyCheck) {
        repository.deleteSafetyCheck(safetyCheck);
    }
}