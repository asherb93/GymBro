package com.example.gymbro.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbro.Callbacks.ExerciseSetCallback;
import com.example.gymbro.Models.ExerciseSet;
import com.example.gymbro.R;

import java.util.ArrayList;

public class SummaryExerciseSetsAdapter extends RecyclerView.Adapter<SummaryExerciseSetsAdapter.MyViewHolder>{

    ArrayList<ExerciseSet> exerciseSetArrayList ;
    Context context;


    public SummaryExerciseSetsAdapter(ArrayList<ExerciseSet> exerciseSetArrayList,Context context) {
        this.exerciseSetArrayList = exerciseSetArrayList;
        this.context = context;
    }



    @NonNull
    @Override
    public SummaryExerciseSetsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.summary_set_item, parent, false);
        return new SummaryExerciseSetsAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


    }

    public ExerciseSet getItem(int position){
        return exerciseSetArrayList.get(position);
    }


    @Override
    public int getItemCount() {
        return exerciseSetArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
