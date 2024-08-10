package com.example.gymbro.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbro.Models.Exercise;
import com.example.gymbro.Models.ExerciseSet;
import com.example.gymbro.R;
import com.example.gymbro.Utils.SignalManager;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.MyViewHolder> {

    ArrayList<Exercise> exerciseArrayList;
    Context context;


    public ExerciseAdapter(ArrayList<Exercise> exerciseArrayList, Context context) {
        this.exerciseArrayList = exerciseArrayList;
        this.context = context;
    }



    @NonNull
    @Override
    public ExerciseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.workout_execise_card_view, parent, false);
        return new ExerciseAdapter.MyViewHolder(view);
    }

    public Exercise getItem(int position){
        return exerciseArrayList.get(position);
    }


    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.MyViewHolder holder, int position) {
        Exercise exercise = getItem(position);




        // Create sub item view adapter
        ExerciseSetAdapter exerciseSetAdapter = new ExerciseSetAdapter(exercise.getExerciseSets(),context);
        holder.exerciseSetsRV.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        holder.exerciseSetsRV.setAdapter(exerciseSetAdapter);



        holder.newSetButton.setOnClickListener(v->{
            exercise.getExerciseSets().add(new ExerciseSet(exercise.getExerciseName(),0,0));
            exerciseSetAdapter.notifyItemInserted(exercise.getExerciseSets().size()-1);
        });



        exerciseSetAdapter.setExerciseSetCallback((exerciseSet, pos) -> {
            exercise.getExerciseSets().get(pos).setReps(exerciseSet.getReps());
            exercise.getExerciseSets().get(pos).setWeight(exerciseSet.getWeight());
            exercise.getExerciseSets().get(pos).setChecked(!exerciseSet.isChecked());
           // exerciseSetAdapter.notifyItemChanged(position);
        });

        holder.exerciseName.setText(exercise.getExerciseName());



    }

    @Override
    public int getItemCount() {
        return exerciseArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView exerciseName;
        RecyclerView exerciseSetsRV;
        Button newSetButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.exercise_name_LBL);
            exerciseSetsRV = itemView.findViewById(R.id.exercise_set_recyclerview);
            newSetButton = itemView.findViewById(R.id.new_set_button);
        }
    }
}
