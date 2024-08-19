package com.example.gymbro.Adapters.WorkoutActivityAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbro.Callbacks.StartRestCallback;
import com.example.gymbro.Models.Exercise;
import com.example.gymbro.Models.ExerciseSet;
import com.example.gymbro.R;

import java.util.ArrayList;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.MyViewHolder> {

    ArrayList<Exercise> exerciseArrayList;
    Context context;
    private StartRestCallback startRestCallback;
    private ExerciseSetAdapter exerciseSetAdapter;

    public void setStartRestCallback(StartRestCallback startRestCallback) {
        this.startRestCallback = startRestCallback;
    }

    public ExerciseAdapter(ArrayList<Exercise> exerciseArrayList, Context context) {
        this.exerciseArrayList = exerciseArrayList;
        this.context = context;
    }



    @NonNull
    @Override
    public ExerciseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_exercise, parent, false);
        return new ExerciseAdapter.MyViewHolder(view);
    }

    public Exercise getItem(int position){
        return exerciseArrayList.get(position);
    }


    @Override
    public void onBindViewHolder(@NonNull ExerciseAdapter.MyViewHolder holder, int position) {
        Exercise exercise = getItem(position);

        // Create sub item view adapter
        exerciseSetAdapter = new ExerciseSetAdapter(exercise.getExerciseSets(),context);
        holder.exerciseSetsRV.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        holder.exerciseSetsRV.setAdapter(exerciseSetAdapter);





        holder.newSetButton.setOnClickListener(v->{
                if(exerciseArrayList.get(position).getExerciseSets().size()>0){
                    ExerciseSet lastExerciseSet = exerciseArrayList.get(position).getExerciseSets().get(exerciseArrayList.get(position).getExerciseSets().size()-1);
                    exercise.getExerciseSets().add(new ExerciseSet(exercise.getExerciseName(), lastExerciseSet.getReps(), lastExerciseSet.getWeight()));
                }
                Log.d("TAG", "Position "+position+" Name: "+exercise.getExerciseName());
            int newPosition = exercise.getExerciseSets().size() - 1;
            exerciseSetAdapter.notifyItemInserted(newPosition);
            notifyItemRangeChanged(position, exerciseArrayList.size());

        });

        exerciseSetAdapter.setExerciseSetCallback((exerciseSet, pos) -> {
            if(!exercise.getExerciseSets().get(pos).isChecked()) {
                exercise.getExerciseSets().get(pos).setReps(exerciseSet.getReps());
                exercise.getExerciseSets().get(pos).setWeight(exerciseSet.getWeight());
            }
            exercise.getExerciseSets().get(pos).setChecked(!exerciseSet.isChecked());
            exerciseSetAdapter.notifyDataSetChanged();
             if(startRestCallback!=null&&exercise.getExerciseSets().get(pos).isChecked()){
                startRestCallback.startTimer();
            }
        });

        holder.exerciseName.setOnClickListener(v->{
            Log.d("TAG", "Position "+position+" Name: "+exercise.getExerciseName());
                });

        holder.removeButton.setOnClickListener(v->{
            holder.areYouSureTextView.setText("Remove "+exercise.getExerciseName()+" from workout?");
            holder.areYouSureLayout.setVisibility(View.VISIBLE);

        });

        holder.noImageView.setOnClickListener(v1->{
            holder.areYouSureLayout.setVisibility(View.GONE);
        });

        holder.yesImageView.setOnClickListener(v1->{
            int deletedPosition = exerciseArrayList.indexOf(exercise);
            exerciseArrayList.remove(deletedPosition);
            this.notifyItemRemoved(deletedPosition);
            this.notifyItemRangeChanged(deletedPosition, exerciseArrayList.size());
            holder.areYouSureLayout.setVisibility(View.GONE);
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
        ImageView removeButton;
        ConstraintLayout areYouSureLayout;
        ImageView yesImageView;
        ImageView noImageView;
        TextView areYouSureTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseName = itemView.findViewById(R.id.exercise_name_LBL);
            exerciseSetsRV = itemView.findViewById(R.id.exercise_set_recyclerview);
            newSetButton = itemView.findViewById(R.id.new_set_button);
            removeButton = itemView.findViewById(R.id.remove_exercise_imageview);
            areYouSureLayout = itemView.findViewById(R.id.are_you_sure_layout);
            yesImageView = itemView.findViewById(R.id.yes_imageview);
            noImageView = itemView.findViewById(R.id.no_imageview);
            areYouSureTextView = itemView.findViewById(R.id.are_you_sure_textview);

        }
    }
}
