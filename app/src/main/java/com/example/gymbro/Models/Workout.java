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
