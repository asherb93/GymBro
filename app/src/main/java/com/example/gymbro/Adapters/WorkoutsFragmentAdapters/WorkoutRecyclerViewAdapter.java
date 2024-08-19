package com.example.gymbro.Adapters.WorkoutsFragmentAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbro.Callbacks.DeleteWorkoutCallback;
import com.example.gymbro.Callbacks.StartSavedWorkoutCallback;
import com.example.gymbro.Data.Workout;
import com.example.gymbro.R;
import com.example.gymbro.Utils.SignalManager;

import java.util.ArrayList;

public class WorkoutRecyclerViewAdapter extends RecyclerView.Adapter<WorkoutRecyclerViewAdapter.MyViewHolder> {

     private ArrayList<Workout> workoutsArray;
     private Context context;
     private DeleteWorkoutCallback deleteWorkoutCallback;

    StartSavedWorkoutCallback startSavedWorkoutCallback;

    public void setStartSavedWorkoutCallback(StartSavedWorkoutCallback startSavedWorkoutCallback) {
        this.startSavedWorkoutCallback = startSavedWorkoutCallback;
    }


    public void setDeleteWorkoutCallback(DeleteWorkoutCallback deleteWorkoutCallback) {
         this.deleteWorkoutCallback = deleteWorkoutCallback;
     }

    // data is passed into the constructor
    public WorkoutRecyclerViewAdapter(Context context, ArrayList<Workout> workoutsArray) {
        this.context = context;
        this.workoutsArray = workoutsArray;
    }


    @NonNull
    @Override
    public WorkoutRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_workout, parent, false);
        return new WorkoutRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutRecyclerViewAdapter.MyViewHolder holder, int position) {
        Workout workout = getItem(position);
        holder.workoutTitle.setText(workoutsArray.get(position).getWorkoutName());
        holder.exerciseSummary.setText(workoutsArray.get(position).getWorkoutSummary());

        holder.workoutCardView.setOnClickListener(v->{
            if(startSavedWorkoutCallback!=null)
                startSavedWorkoutCallback.startSavedWorkout(position);
        });

        holder.trash_icon.setOnClickListener(v -> {

            holder.askDeleteLayout.setVisibility(View.VISIBLE);
            holder.noDeleteButton.setOnClickListener(v1 -> {
              holder.askDeleteLayout.setVisibility(View.GONE);
            });
            holder.yesDeleteButton.setOnClickListener(v2 -> {

                if(deleteWorkoutCallback!=null)
                    deleteWorkoutCallback.deleteWorkoutFromDB(workout.getWorkoutId(),position);


                holder.askDeleteLayout.setVisibility(View.GONE);
                SignalManager.getInstance().toast(position+" deleted");
                SignalManager.getInstance().vibrate(1000);
            });

        });
    }

    public Workout getItem(int position){
        return workoutsArray.get(position);
    }

    @Override
    public int getItemCount() {
        return workoutsArray.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ViewPropertyAnimator viewPropertyAnimator;
        CardView workoutCardView;
        TextView workoutTitle ;
        TextView workoutDescription ;
        TextView exerciseSummary;
        ImageView trash_icon;
        ConstraintLayout askDeleteLayout;
        Button noDeleteButton;
        Button yesDeleteButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            viewPropertyAnimator = itemView.animate();
            workoutCardView = itemView.findViewById(R.id.workout_card_view);
            workoutTitle = itemView.findViewById(R.id.workout_title);
            workoutDescription = itemView.findViewById(R.id.workout_description);
            exerciseSummary = itemView.findViewById(R.id.exercise_summary_textview);
            trash_icon = itemView.findViewById(R.id.trash_imageview);
            askDeleteLayout = itemView.findViewById(R.id.ask_before_delete_layout);
            noDeleteButton = itemView.findViewById(R.id.no_delete_button);
            yesDeleteButton = itemView.findViewById(R.id.yes_delete_button);
        }

    }
}
