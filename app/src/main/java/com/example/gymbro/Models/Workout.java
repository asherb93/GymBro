package com.example.gymbro.Models;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;

public class Workout {
    private String workoutName;
    private String workoutDescription;
    private ArrayList<Exercise> exercises;



    private  int workoutid;

    public String getWorkoutDescription() {
        return workoutDescription;
    }

    public void setWorkoutDescription(String workoutDescription) {
        this.workoutDescription = workoutDescription;
    }



    public Workout(String workoutName, String workoutDescription, Date workoutDate, ArrayList<ExerciseSet> exerciseSets) {
        this.workoutName = workoutName;
        this.workoutDescription = workoutDescription;
    }

    public Workout(String workoutName, String workoutDescription) {
        this.workoutName = workoutName;
        this.workoutDescription = workoutDescription;
        this.workoutid++;
        this.exercises = new ArrayList<>();
    }

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
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
        for(int i = 0; i < exercises.size(); i++) {
            stringBuilder.append(exercises.get(i).exerciseName)
                    .append(" x ")
                    .append(exercises.get(i).getExerciseSets().size());
            if (i < exercises.size() - 1) {
                stringBuilder.append("\n");
            } else {
                stringBuilder.append("");
            }
        }
        return stringBuilder.toString();

    }


}
