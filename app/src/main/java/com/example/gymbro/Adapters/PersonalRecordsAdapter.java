package com.example.gymbro.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbro.Models.Exercise;
import com.example.gymbro.Models.ExerciseStats;
import com.example.gymbro.R;
import com.example.gymbro.Utils.ImageLoader;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class PersonalRecordsAdapter extends RecyclerView.Adapter<PersonalRecordsAdapter.MyViewHolder> {
    private ArrayList<ExerciseStats> personalRecords;
    private Context context;

    public PersonalRecordsAdapter(ArrayList<ExerciseStats> personalRecords, Context context) {
        this.personalRecords = personalRecords;
        this.context = context;
    }


    @NonNull
    @Override
    public PersonalRecordsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.exercise_pr_item, parent, false);
        return new PersonalRecordsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalRecordsAdapter.MyViewHolder holder, int position) {
        ExerciseStats exerciseStats = getItem(position);
        holder.exerciseName.setText(exerciseStats.getExerciseName());
        holder.exerciseWeight.setText(exerciseStats.getMaxWeight()+"KG");
        holder.exerciseReps.setText(exerciseStats.getMaxReps()+"Reps");
        ImageLoader.getInstance().load(exerciseStats.getImage(),holder.exerciseIV);


    }

    public ExerciseStats getItem(int position){
        return personalRecords.get(position);
    }

    @Override
    public int getItemCount() {
        return personalRecords.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView exerciseName;
        TextView exerciseWeight;
        TextView exerciseReps;
        ImageView exerciseIV;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.pr_exercise_name_LBL);
            exerciseWeight = itemView.findViewById(R.id.pr_exercise_weight_LBL);
            exerciseReps = itemView.findViewById(R.id.pr_exercise_reps_LBL);
            exerciseIV = itemView.findViewById(R.id.pr_exercise_imageview);
        }
    }


}
