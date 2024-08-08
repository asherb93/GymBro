package com.example.gymbro.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbro.Models.Exercise;
import com.example.gymbro.Models.ExerciseSet;
import com.example.gymbro.R;

import java.util.ArrayList;

public class SummaryExerciseAdapter extends RecyclerView.Adapter<SummaryExerciseAdapter.MyViewHolder> {

    ArrayList<Exercise> exerciseArrayList;
    Context context;


    public SummaryExerciseAdapter(ArrayList<Exercise> exerciseArrayList, Context context) {
        this.exerciseArrayList = exerciseArrayList;
        this.context = context;
    }



    @NonNull
    @Override
    public SummaryExerciseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.summary_exercise_item, parent, false);
        return new SummaryExerciseAdapter.MyViewHolder(view);
    }

    public Exercise getItem(int position){
        return exerciseArrayList.get(position);
    }


    @Override
    public void onBindViewHolder(@NonNull SummaryExerciseAdapter.MyViewHolder holder, int position) {
        Exercise exercise = getItem(position);
        holder.exerciseName.setText(exercise.getExerciseName());

        // Create sub item view adapter
        SummaryExerciseSetsAdapter adapter = new SummaryExerciseSetsAdapter(exercise.getExerciseSets(),context);
        holder.exerciseSetsRV.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        holder.exerciseSetsRV.setAdapter(adapter);



    }

    @Override
    public int getItemCount() {
        return exerciseArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView exerciseName;
        RecyclerView exerciseSetsRV;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.exercise_name_textview);
            exerciseSetsRV = itemView.findViewById(R.id.sum_exercise_sets_rv);
        }
    }
}
