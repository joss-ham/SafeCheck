package uk.edu.le.part2;

import android.content.Intent;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private SafetyViewModel viewModel;
    private RecyclerView recyclerView;
    private SafetyCheckAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Set up RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up adapter
        adapter = new SafetyCheckAdapter(safetyCheck -> {
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("checkId", safetyCheck.getCheckId());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        // Set up ViewModel to observe database changes
        viewModel = new ViewModelProvider(this).get(SafetyViewModel.class);
        viewModel.getAllChecks().observe(this, checks -> {
            adapter.updateChecks(checks);
        });

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}