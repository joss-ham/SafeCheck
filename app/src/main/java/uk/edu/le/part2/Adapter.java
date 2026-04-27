package uk.edu.le.part2;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import uk.edu.le.part2.data.SafetyCheck;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    private List<SafetyCheck> checkList;

    public Adapter(List<SafetyCheck> checkList) {
        this.checkList = checkList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount(){
        return checkList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.MyViewHolder holder, int position) {
        SafetyCheck safetyCheck = checkList.get(position);

        holder.checkId.setText(safetyCheck.getCheckId());
        holder.checkDate.setText((CharSequence) safetyCheck.getCheckDate());
        holder.vehicleRegistration.setText(safetyCheck.getVehicleRegistration());
        holder.driverName.setText(safetyCheck.getDriverName());
        holder.overallStatus.setText(safetyCheck.getOverallStatus());

        holder.itemView.setOnClickListener(v -> listener.onCheckClick(safetyCheck));
    }
    public void updateList(List<SafetyCheck> newList) {
        this.checkList = newList;
        notifyDataSetChanged();
    }

    public interface OnCheckClickListener {
        void onCheckClick(SafetyCheck safetyCheck);
    }

    private OnCheckClickListener listener;

    public Adapter(List<SafetyCheck> checkList, OnCheckClickListener listener) {
        this.checkList = checkList;
        this.listener = listener;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView checkId, checkDate, vehicleRegistration, driverName, overallStatus; //pull info from defect later
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
                checkId = itemView.findViewById(R.id.checkId);
                checkDate = itemView.findViewById(R.id.checkDate);
                vehicleRegistration = itemView.findViewById(R.id.vehicleRegistration);
                driverName = itemView.findViewById(R.id.driverName);
                overallStatus = itemView.findViewById(R.id.overallStatus);

        }
    }
}


