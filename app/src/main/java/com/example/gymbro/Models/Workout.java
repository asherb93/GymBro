package com.example.gymbro.Models;

import androidx.annotation.NonNull;

import com.example.gymbro.Utils.TimeFormatter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Workout implements Serializable {
    private String workoutName;
    private ArrayList<Exercise> exercises=new ArrayList<>();
    private final int workoutId = (int) (Math.random() * 100000);
    private boolean isSaved = false;
    private long workoutTime=0;
    private ArrayList<ExerciseSet> bestSets = new ArrayList<>();
    private int  numberOfPrs = 0;

    public long getWorkoutTime() {
        return workoutTime;
    }

    public void setWorkoutTime(long workoutTime) {
        this.workoutTime = workoutTime;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public Workout() {
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public Workout setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
        return this;
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public Workout setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
        return this;
    }

    public ArrayList<ExerciseSet> getBestSets(){
        for(Exercise e: exercises){
            bestSets.add(e.getBestSet());
        }
        return bestSets;
    }

    public void setBestSets(ArrayList<ExerciseSet> bestSets) {
        this.bestSets = bestSets;
    }

    public int getNumberOfPrs() {
        return numberOfPrs;
    }

    public void setNumberOfPrs(int numberOfPrs) {
        this.numberOfPrs = numberOfPrs;
    }



    public boolean checkIfAllExercisesHaveSets(){
        for (int i = 0; i < exercises.size(); i++) {
            if(exercises.get(i).getExerciseSets()==null||exercises.get(i).getExerciseSets().isEmpty()){
                return false;
            }
        }
        return true;
    }


    public boolean checkIfExerciseExists(String item) {
        for (int i = 0; i < exercises.size(); i++) {
            if (exercises.get(i).getExerciseName().equals(item)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfAllSetsAreChecked() {
        for (int i = 0; i < exercises.size(); i++) {
            if (!exercises.get(i).checkIfAllSetsAreChecked()) {
                return false;
            }
        }
        return true;
    }

    public void uncheckAllSets() {
        for (int i = 0; i < exercises.size(); i++) {
            exercises.get(i).uncheckAllSets();
        }
    }

    public int getTotalSets() {
        int totalSets = 0;
        for (int i = 0; i < exercises.size(); i++) {
            totalSets += exercises.get(i).getTotalSets();
        }
        return totalSets;
    }

    public int getTotalWeight() {
        int totalWeight = 0;
        for (int i = 0; i < exercises.size(); i++) {
            totalWeight += exercises.get(i).getTotalWeight();
        }
        return totalWeight;
    }

    public void clearPrsets() {
        for(Exercise e:exercises){
            e.clearPrsets();
        }
    }

    public void clearBestSets() {
        bestSets = new ArrayList<>();
    }

    public void setPersonalRecords(ExerciseSet es) {
        for(Exercise e:exercises){
            e.setPersonalRecords(es);
        }
    }


    public String getWorkoutSummary()
    {
        String str =toString().replaceAll("]","");
        return str;
    }




    @NonNull
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if(exercises!=null) {
            for (int i = 0; i < exercises.size(); i++) {
                if(exercises.get(i).getExerciseSets()!=null) {
                    stringBuilder.append(exercises.get(i).exerciseName)
                            .append(" x ")
                            .append(exercises.get(i).getExerciseSets().size());
                }
                if (i < exercises.size() - 1) {
                    stringBuilder.append("\n");
                } else {
                    stringBuilder.append("");
                }
            }
        }
        return stringBuilder.toString();
    }

}
