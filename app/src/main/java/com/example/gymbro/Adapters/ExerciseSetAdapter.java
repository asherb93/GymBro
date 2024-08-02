package com.example.gymbro.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbro.Models.ExerciseInfo;
import com.example.gymbro.Models.ExerciseSet;
import com.example.gymbro.R;

import java.util.ArrayList;

public class ExerciseSetAdapter extends RecyclerView.Adapter<ExerciseSetAdapter.MyViewHolder>{

    ArrayList<ExerciseSet> exerciseSetArrayList ;
    Context context;

    public ExerciseSetAdapter(ArrayList<ExerciseSet> exerciseSetArrayList,Context context) {
        this.exerciseSetArrayList = exerciseSetArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ExerciseSetAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.exercise_set_item, parent, false);
        return new ExerciseSetAdapter.MyViewHolder(view);
    }

    public ExerciseSet getItem(int position){
        return exerciseSetArrayList.get(position);
    }


    @Override
    public void onBindViewHolder(@NonNull ExerciseSetAdapter.MyViewHolder holder, int position) {
        ExerciseSet exerciseSet = getItem(position);
    }

    @Override
    public int getItemCount() {
        return exerciseSetArrayList.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView setNumberTV;
        TextView setWeightTV;
        TextView setRepsTV;
        CheckBox setCheckBox;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            setNumberTV = itemView.findViewById(R.id.set_number_LBL);
            setWeightTV = itemView.findViewById(R.id.weightEditText);
            setRepsTV = itemView.findViewById(R.id.reps_edit_text);
            setCheckBox = itemView.findViewById(R.id.set_checkbox);
        }
    }
}
