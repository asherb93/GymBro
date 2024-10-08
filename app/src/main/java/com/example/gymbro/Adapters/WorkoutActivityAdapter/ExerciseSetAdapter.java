package com.example.gymbro.Adapters.WorkoutActivityAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymbro.Callbacks.ExerciseSetCallback;
import com.example.gymbro.Models.ExerciseSet;
import com.example.gymbro.R;
import com.example.gymbro.Utils.SignalManager;

import java.util.ArrayList;

public class ExerciseSetAdapter extends RecyclerView.Adapter<ExerciseSetAdapter.MyViewHolder>{

    ArrayList<ExerciseSet> exerciseSetArrayList ;
    Context context;
    private ExerciseSetCallback exerciseSetCallback;


    public ExerciseSetAdapter(ArrayList<ExerciseSet> exerciseSetArrayList,Context context) {
        this.exerciseSetArrayList = exerciseSetArrayList;
        this.context = context;
    }

    public void setExerciseSetCallback(ExerciseSetCallback exerciseSetCallback) {
        this.exerciseSetCallback = exerciseSetCallback;
    }


    @NonNull
    @Override
    public ExerciseSetAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_exercise_set, parent, false);
        return new ExerciseSetAdapter.MyViewHolder(view);
    }

    public ExerciseSet getItem(int position){
        return exerciseSetArrayList.get(position);
    }


    @Override
    public void onBindViewHolder(@NonNull ExerciseSetAdapter.MyViewHolder holder, int position) {
        ExerciseSet exerciseSet = getItem(position);
        holder.setNumberLBL.setText(""+(position+1)+".");
        holder.weightEditText.setHint(""+exerciseSet.getWeight()+"KG");
        holder.repsEditText.setHint(""+exerciseSet.getReps());

        holder.exerciseSetCardView.setOnClickListener(v->{
            SignalManager.getInstance().toast("position: "+position+" weight: "+exerciseSet.getWeight()+" reps: "+exerciseSet.getReps()+"");
        });



        holder.removeSetImageView.setOnClickListener(v->{
            Log.d("DELETE ESA","size"+exerciseSetArrayList.size()+ "Position: "+position);

            if(exerciseSetCallback!=null){
                exerciseSetCallback.exerciseSetRemoved(position);
            }
        });


        holder.setCheckBox.setChecked(exerciseSet.isChecked());

        holder.setCheckBox.setOnClickListener(v->{

            String repsString = holder.repsEditText.getText().toString();
            String weightString = holder.weightEditText.getText().toString();
            int reps;
            int weight;

            reps = repsString.isEmpty() ? exerciseSet.getReps() : Integer.parseInt(repsString);
            weight = weightString.isEmpty() ? exerciseSet.getWeight() : Integer.parseInt(weightString);


            if(exerciseSetCallback!=null) {
                if(reps==0||weight==0){
                    SignalManager.getInstance().toast("Weight or Reps must be greater than 0");
                    SignalManager.getInstance().vibrate(1000L);
                    holder.setCheckBox.setChecked(false);
                }
                else{
                    exerciseSet.setReps(reps);
                    exerciseSet.setWeight(weight);
                    exerciseSetCallback.exerciseSetChecked(exerciseSet, position);
                }
            }


            if(!exerciseSet.isChecked()){
                holder.repsEditText.setEnabled(true);
                holder.weightEditText.setEnabled(true);
            }
            else{
                holder.repsEditText.setEnabled(false);
                holder.weightEditText.setEnabled(false);
            }

        });


    }

    @Override
    public int getItemCount() {
        return exerciseSetArrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox setCheckBox;
        TextView setNumberLBL;
        EditText weightEditText;
        EditText repsEditText;
        Button addSetButton;
        CardView exerciseSetCardView;
        ImageView removeSetImageView;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            weightEditText = itemView.findViewById(R.id.weightEditText);
            repsEditText = itemView.findViewById(R.id.reps_edit_text);
            setCheckBox = itemView.findViewById(R.id.set_checkbox);
            setNumberLBL = itemView.findViewById(R.id.set_number_LBL);
            addSetButton = itemView.findViewById(R.id.new_set_button);
            exerciseSetCardView = itemView.findViewById(R.id.exercise_set_card_view);
            removeSetImageView = itemView.findViewById(R.id.remove_set_IV);

        }
    }
}
