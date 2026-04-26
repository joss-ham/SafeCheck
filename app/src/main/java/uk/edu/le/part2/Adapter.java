package uk.edu.le.part2;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import uk.edu.le.part2.data.SafetyCheck;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import uk.edu.le.part2.data.SafetyCheck;

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
        SafteyCheck safteyCheck = checkList.get(position);

        holder.checkId.setText(SafetyCheck.getCheckId());
        //add all columns later
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView id, date, vehicleRegistration, driverName, overallStatus; //pull info from defect later
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
                checkId = itemView.findViewById(R.id.checkId);
                //add all columns later

        }
    }
}


