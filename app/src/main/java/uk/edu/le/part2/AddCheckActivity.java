package uk.edu.le.part2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Date;

import uk.edu.le.part2.data.SafetyCheck;
import uk.edu.le.part2.data.SafetyRepository;

public class AddCheckActivity extends AppCompatActivity {

    private EditText editDriverName, editVehicleReg;
    private SafetyRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_check_activity);

        repository = new SafetyRepository(getApplication());

        editDriverName = findViewById(R.id.editDriverName);
        editVehicleReg = findViewById(R.id.editVehicleReg);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(v -> saveCheck());
    }

    private void saveCheck() {
        String driverName = editDriverName.getText().toString().trim();
        String vehicleReg = editVehicleReg.getText().toString().trim();

        // Input validation — satisfies the brief's validation requirement
        if (vehicleReg.isEmpty()) {
            Toast.makeText(this, "Please enter vehicle details", Toast.LENGTH_SHORT).show();
            return;
        }

        if (driverName.isEmpty()) {
            Toast.makeText(this, "Please enter driver name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Auto generate date
        Date date = new Date();

        SafetyCheck newCheck = new SafetyCheck(date,vehicleReg,driverName,"pass");

        // Writes on background thread via ExecutorService inside Repository
        repository.insertSafetyCheck(newCheck);

        // Go back to MainActivity, LiveData will update the list automatically
        finish();
    }
}