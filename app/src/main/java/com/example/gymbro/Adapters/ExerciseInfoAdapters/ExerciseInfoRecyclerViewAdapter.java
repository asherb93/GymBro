package com.example.gymbro.Adapters.ExerciseInfoAdapters;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbro.Models.ExerciseInfo;
import com.example.gymbro.R;
import com.example.gymbro.Utils.ImageLoader;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class ExerciseInfoRecyclerViewAdapter extends RecyclerView.Adapter<ExerciseInfoRecyclerViewAdapter.MyViewHolder> {


    ArrayList<ExerciseInfo> exerciseInfoArrayList ;
    Context context;

    public ExerciseInfoRecyclerViewAdapter(ArrayList<ExerciseInfo> exerciseInfoArrayList, Context context) {
        this.exerciseInfoArrayList = exerciseInfoArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ExerciseInfoRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_exercise_info, parent, false);
        return new ExerciseInfoRecyclerViewAdapter.MyViewHolder(view);
    }

    public ExerciseInfo getItem(int position){
        return exerciseInfoArrayList.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseInfoRecyclerViewAdapter.MyViewHolder holder, int position) {
        //To do last
        ExerciseInfo exerciseInfo = getItem(position);
        ImageLoader.getInstance().load(exerciseInfo.getExerciseImage(), holder.exerciseImageView);
        holder.exerciseNameTV.setText(exerciseInfo.getExerciseName());
        holder.exerciseMuscleGroupTV.setText(exerciseInfo.getExerciseMuscleGroup());
        holder.exerciseDescriptionTV.setText(exerciseInfo.getExerciseDescription());

        holder.exerciseNameTV.setOnClickListener(v -> {
            ArrayList<ObjectAnimator> animations = new ArrayList<>();
            if (exerciseInfo.isCollapsed()) {
                animations.add(ObjectAnimator
                        .ofInt(holder.exerciseDescriptionTV, "maxLines", holder.exerciseDescriptionTV.getLineCount())
                        .setDuration((Math.max(holder.exerciseDescriptionTV.getLineCount() - ExerciseInfo.MAX_LINES_COLLAPSED, 0)) * 50L));
            } else {
               animations.add(ObjectAnimator
                        .ofInt(holder.exerciseDescriptionTV, "maxLines", ExerciseInfo.MAX_LINES_COLLAPSED)
                        .setDuration((Math.max(holder.exerciseDescriptionTV.getLineCount() - ExerciseInfo.MAX_LINES_COLLAPSED, 0)) * 50L));
            }
            animations.forEach(ObjectAnimator::start);

            exerciseInfo.setCollapsed(!exerciseInfo.isCollapsed());
        });

    }

    @Override
    public int getItemCount() {
        return exerciseInfoArrayList.size();
    }

    public void setExerciseInfoArrayList(ArrayList<ExerciseInfo> exerciseInfoArrayList) {
        this.exerciseInfoArrayList.clear();
        this.exerciseInfoArrayList.addAll(exerciseInfoArrayList);
        notifyDataSetChanged();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView exerciseNameTV;
        TextView exerciseMuscleGroupTV;
        TextView exerciseDescriptionTV;
        ShapeableImageView exerciseImageView;
        CardView exerciseCardView;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseNameTV = itemView.findViewById(R.id.exercise_LBL_name);
            exerciseMuscleGroupTV = itemView.findViewById(R.id.main_muscle_group);
            exerciseDescriptionTV = itemView.findViewById(R.id.exercise_LBL);
            exerciseImageView = itemView.findViewById(R.id.exercise_IMG_poster);
            exerciseCardView =itemView.findViewById(R.id.exercise_cardview);
            constraintLayout = itemView.findViewById(R.id.main_constraint_layout);
        }

    }
}
