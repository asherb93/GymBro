package com.example.gymbro.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbro.Models.Workout;
import com.example.gymbro.R;
import com.example.gymbro.Utils.SignalManager;

import java.util.ArrayList;

public class WorkoutRecyclerViewAdapter extends RecyclerView.Adapter<WorkoutRecyclerViewAdapter.MyViewHolder> {

     ArrayList<Workout> workoutsArray;
     Context context;

    // data is passed into the constructor
    public WorkoutRecyclerViewAdapter(Context context, ArrayList<Workout> workoutsArray) {
        this.context = context;
        this.workoutsArray = workoutsArray;
    }


    @NonNull
    @Override
    public WorkoutRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.workout_title_card_view, parent, false);
        return new WorkoutRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutRecyclerViewAdapter.MyViewHolder holder, int position) {
        Workout workout = getItem(position);
        Animation fadeOutAnimation = android.view.animation.AnimationUtils.loadAnimation(context, R.anim.fade_out);
        holder.workoutTitle.setText(workoutsArray.get(position).getWorkoutName());
        holder.workoutDescription.setText(workoutsArray.get(position).getWorkoutDescription());
        holder.exerciseSummary.setText(workoutsArray.get(position).getWorkoutSummary());

        holder.trash_icon.setOnClickListener(v -> {
            holder.askDeleteCardView.setVisibility(View.VISIBLE);

            holder.noDeleteButton.setOnClickListener(v1 -> {
              holder.askDeleteCardView.setVisibility(View.GONE);
            });

            holder.yesDeleteButton.setOnClickListener(v2 -> {

                holder.workoutCardView.setAnimation(fadeOutAnimation);
               // workoutsArray.remove(position);
                holder.askDeleteCardView.setVisibility(View.GONE);
                SignalManager.getInstance().toast(workout.getWorkoutName()+" deleted");
                SignalManager.getInstance().vibrate(1000);
                notifyDataSetChanged();
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
        CardView askDeleteCardView;
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
            askDeleteCardView = itemView.findViewById(R.id.ask_before_delete_cardview);
            noDeleteButton = itemView.findViewById(R.id.no_delete_button);
            yesDeleteButton = itemView.findViewById(R.id.yes_delete_button);
        }

    }
}
