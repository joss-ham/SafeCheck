package uk.edu.le.part2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import uk.edu.le.part2.data.Defect;
import uk.edu.le.part2.data.SafetyCheck;

public class DetailActivity extends AppCompatActivity {

    private SafetyViewModel viewModel;
    private int checkId;
    private SafetyCheck currentSafetyCheck;
    private TextView checkIdText, dateText, vehicleText, driverText, statusText, defectsListText;
    private EditText defectInput;
    private Button addDefectButton, emailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        try {
            // Initialize UI elements
            checkIdText = findViewById(R.id.checkIdText);
            dateText = findViewById(R.id.dateText);
            vehicleText = findViewById(R.id.vehicleText);
            driverText = findViewById(R.id.driverText);
            statusText = findViewById(R.id.statusText);
            defectsListText = findViewById(R.id.defectsListText);
            defectInput = findViewById(R.id.defectInput);
            addDefectButton = findViewById(R.id.addDefectButton);
            emailButton = findViewById(R.id.emailButton);

            viewModel = new ViewModelProvider(this).get(SafetyViewModel.class);
            checkId = getIntent().getIntExtra("checkId", -1);

            if (checkId == -1) {
                Toast.makeText(this, "Error: No check ID", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            // Load safety check
            viewModel.getCheckById(checkId).observe(this, safetyCheck -> {
                if (safetyCheck != null) {
                    currentSafetyCheck = safetyCheck;
                    displaySafetyCheck(safetyCheck);
                } else {
                    Toast.makeText(DetailActivity.this, "Safety check not found for ID: " + checkId, Toast.LENGTH_SHORT).show();
                }
            });

            // Load defects
            viewModel.getDefectsForCheck(checkId).observe(this, defects -> {
                if (defects != null) {
                    displayDefects(defects);
                }
            });

            // Add defect button
            addDefectButton.setOnClickListener(v -> {
                String description = defectInput.getText().toString().trim();
                if (description.isEmpty()) {
                    defectInput.setError("Description required");
                    Toast.makeText(DetailActivity.this, "Please enter a defect", Toast.LENGTH_SHORT).show();
                    return;
                }
                Defect newDefect = new Defect(0, description, "Low", checkId);
                viewModel.insertDefect(newDefect);
                defectInput.setText("");
                Toast.makeText(DetailActivity.this, "Defect added", Toast.LENGTH_SHORT).show();
            });

            // Email button - UPDATED WITH FULL EMAIL INTENT
            emailButton.setOnClickListener(v -> {
                if (currentSafetyCheck == null) {
                    Toast.makeText(DetailActivity.this, "Loading data...", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Build email content
                StringBuilder emailBody = new StringBuilder();
                emailBody.append("Safety Check Report\n\n");
                emailBody.append("Vehicle: ").append(currentSafetyCheck.getVehicleRegistration()).append("\n");
                emailBody.append("Driver: ").append(currentSafetyCheck.getDriverName()).append("\n");
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                emailBody.append("Date: ").append(dateFormat.format(currentSafetyCheck.getCheckDate())).append("\n");
                emailBody.append("Status: ").append(currentSafetyCheck.getOverallStatus()).append("\n\n");
                emailBody.append("Defects Found:\n");

                // Get current defects
                viewModel.getDefectsForCheck(checkId).observe(DetailActivity.this, defects -> {
                    if (defects == null || defects.isEmpty()) {
                        emailBody.append("No defects reported");
                    } else {
                        for (Defect d : defects) {
                            emailBody.append("- ").append(d.getDescription())
                                    .append(" (Severity: ").append(d.getSeverity()).append(")\n");
                        }
                    }

                    // Create email intent
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    emailIntent.setType("message/rfc822");
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Safety Defect Report: " + currentSafetyCheck.getVehicleRegistration());
                    emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody.toString());

                    try {
                        startActivity(Intent.createChooser(emailIntent, "Send email using..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(DetailActivity.this, "No email app installed", Toast.LENGTH_SHORT).show();
                    }

                    // Remove observer to prevent multiple triggers
                    viewModel.getDefectsForCheck(checkId).removeObservers(DetailActivity.this);
                });
            });

        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    private void displaySafetyCheck(SafetyCheck safetyCheck) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            checkIdText.setText("Check ID: " + safetyCheck.getCheckId());

            if (safetyCheck.getCheckDate() != null) {
                dateText.setText("Date: " + dateFormat.format(safetyCheck.getCheckDate()));
            } else {
                dateText.setText("Date: Unknown");
            }

            vehicleText.setText("Vehicle: " + safetyCheck.getVehicleRegistration());
            driverText.setText("Driver: " + safetyCheck.getDriverName());
            statusText.setText("Status: " + safetyCheck.getOverallStatus());
        } catch (Exception e) {
            Toast.makeText(this, "Error displaying: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void displayDefects(List<Defect> defects) {
        if (defects.isEmpty()) {
            defectsListText.setText("No defects reported");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Defect d : defects) {
            sb.append("• ").append(d.getDescription())
                    .append(" [").append(d.getSeverity()).append("]\n");
        }
        defectsListText.setText(sb.toString());
    }
}