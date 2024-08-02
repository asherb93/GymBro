package com.example.gymbro.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
        View view = inflater.inflate(R.layout.exercise_card_view, parent, false);
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
        if(position == getItemCount()-1)
        {
            holder.exerciseCardView.setVisibility(View.INVISIBLE);
            holder.exerciseImageView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return exerciseInfoArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView exerciseNameTV;
        TextView exerciseMuscleGroupTV;
        TextView exerciseDescriptionTV;
        ShapeableImageView exerciseImageView;
        CardView exerciseCardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            exerciseNameTV = itemView.findViewById(R.id.exercise_LBL_name);
            exerciseMuscleGroupTV = itemView.findViewById(R.id.main_muscle_group);
            exerciseDescriptionTV = itemView.findViewById(R.id.exercise_LBL);
            exerciseImageView = itemView.findViewById(R.id.exercise_IMG_poster);
            exerciseCardView =itemView.findViewById(R.id.exercise_cardview);
        }

    }
}
