package com.example.gymbro.Models;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class Exercise implements Serializable {
    public String exerciseName;
    private ArrayList<ExerciseSet> exerciseSets;

    public ArrayList<ExerciseSet> getExerciseSets() {
        return exerciseSets;
    }

    public Exercise(){

    }

    public Exercise(String exerciseName) {
        this.exerciseName = exerciseName;
        this.exerciseSets= new ArrayList<>();
        this.exerciseSets.add(new ExerciseSet(this.getExerciseName(),0,0));
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setExerciseSets(ArrayList<ExerciseSet> exerciseSets) {
        this.exerciseSets = exerciseSets;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(exerciseSets.toString());
        stringBuilder.deleteCharAt(0);
        return  exerciseName+ " x " + exerciseSets.size() + "\n";
    }


    public boolean checkIfAllSetsAreChecked() {
        for (int i = 0; i < exerciseSets.size(); i++) {
            if (!exerciseSets.get(i).isChecked()) {
                return false;
            }
        }
        return true;
    }

    public void uncheckAllSets() {
        for (int i = 0; i < exerciseSets.size(); i++) {
            exerciseSets.get(i).setChecked(false);
        }
    }

    public int getTotalReps() {
        int totalReps = 0;
        for (int i = 0; i < exerciseSets.size(); i++) {
            totalReps += exerciseSets.get(i).getReps();
        }
        return totalReps;
    }

    public int getTotalWeight() {
        int totalWeight = 0;
        for (int i = 0; i < exerciseSets.size(); i++) {
            totalWeight += exerciseSets.get(i).getWeight();
        }
        return totalWeight;
    }

    public ExerciseSet getBestSet() {

        ExerciseSet bestSet = exerciseSets.get(0);
        for(int i=1;i<exerciseSets.size();i++){

            ExerciseSet eSet = exerciseSets.get(i);

            if((eSet.getWeight()==bestSet.getWeight()&&eSet.getReps()>bestSet.getReps())||eSet.getWeight()>bestSet.getWeight()){
                bestSet = eSet;
            }

        }
        return bestSet;

    }

    public int getTotalSets() {
        return exerciseSets.size();
    }

    public void setPersonalRecords(ExerciseSet es) {
        for (ExerciseSet e : exerciseSets) {
            if (e.getExerciseName().equals(es.getExerciseName())&&e.getWeight()==es.getWeight()&&e.getReps()==es.getReps()) {
                e.setPersonalRecord(true);
            }
        }
    }

    public void clearPrsets() {
        for (ExerciseSet e : exerciseSets) {
            e.setPersonalRecord(false);
        }
    }
}
