package com.example.gymbro.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbro.Callbacks.ExerciseSetCallback;
import com.example.gymbro.Models.ExerciseSet;
import com.example.gymbro.R;
import com.example.gymbro.Utils.SignalManager;

import org.w3c.dom.Text;

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
        ExerciseSet exerciseSet = getItem(position);
        holder.weightTextView.setText(exerciseSet.getWeight()+"KG");
        holder.repsTextView.setText(""+exerciseSet.getReps());
        holder.setNumberTextView.setText(""+(position+1));


        holder.weightTextView.setOnClickListener(v->{
            SignalManager.getInstance().toast(exerciseSet.getWeight()+"KG");
            holder.weightTextView.setText(exerciseSet.getWeight()+"KG");

        });

        if(exerciseSet.isPersonalRecord()){
            holder.bestSetImageView.setVisibility(View.VISIBLE);
        }



    }

    public ExerciseSet getItem(int position){

        return exerciseSetArrayList.get(position);
    }


    @Override
    public int getItemCount() {
        return exerciseSetArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView setNumberTextView;
        TextView weightTextView;
        TextView repsTextView;
        ImageView bestSetImageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            weightTextView = itemView.findViewById(R.id.sum_set_weight_textview);
            repsTextView = itemView.findViewById(R.id.sum_reps_textview);
            bestSetImageView = itemView.findViewById(R.id.best_set_IV);
            setNumberTextView = itemView.findViewById(R.id.set_number_LBL);
        }
    }
}
