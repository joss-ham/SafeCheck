package uk.edu.le.part2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import uk.edu.le.part2.data.SafetyCheck;

public class SafetyCheckAdapter extends RecyclerView.Adapter<SafetyCheckAdapter.ViewHolder> {

    private List<SafetyCheck> checks = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(SafetyCheck safetyCheck);
    }

    public SafetyCheckAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void updateChecks(List<SafetyCheck> newChecks) {
        this.checks = newChecks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SafetyCheck check = checks.get(position);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        holder.dateText.setText(dateFormat.format(check.getCheckDate()));
        holder.vehicleText.setText(check.getVehicleRegistration());
        holder.driverText.setText(check.getDriverName());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(check));
    }

    @Override
    public int getItemCount() {
        return checks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateText;
        TextView vehicleText;
        TextView driverText;

        ViewHolder(View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.dateText);
            vehicleText = itemView.findViewById(R.id.vehicleText);
            driverText = itemView.findViewById(R.id.driverText);
        }
    }
}