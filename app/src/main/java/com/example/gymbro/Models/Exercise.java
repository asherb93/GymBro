package com.example.gymbro.Models;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class Exercise implements Serializable {
    String exerciseName;
    ArrayList<ExerciseSet> exerciseSets;

    public ArrayList<ExerciseSet> getExerciseSets() {
        return exerciseSets;
    }

    public Exercise(){

    }


    public Exercise(String exerciseName,ArrayList<ExerciseSet> exerciseSets) {
        this.exerciseName = exerciseName;
        this.exerciseSets = exerciseSets;
    }

    public Exercise(String exerciseName) {
        this.exerciseName = exerciseName;
        this.exerciseSets= new ArrayList<>();
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



}
