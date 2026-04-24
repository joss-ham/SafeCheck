package uk.edu.le.part2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uk.edu.le.part2.data.SafetyCheck;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{
    //TODO make holder class
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
}
