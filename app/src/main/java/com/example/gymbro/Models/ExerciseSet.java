package com.example.gymbro.Models;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class ExerciseSet implements Serializable {
    private String ExerciseName;
    private int reps;
    private int weight;
    private boolean isChecked = false;
    private boolean isPersonalRecord = false;

    public boolean isPersonalRecord() {
        return isPersonalRecord;
    }

    public void setPersonalRecord(boolean personalRecord) {
        this.isPersonalRecord = personalRecord;
    }

    public int getReps() {
        return reps;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public String getExerciseName() {
        return ExerciseName;
    }

    public void setExerciseName(String exerciseName) {
        ExerciseName = exerciseName;
    }

    public ExerciseSet(int reps, int weight) {
        this.reps = reps;
        this.weight = weight;
    }

    public ExerciseSet(String name,int reps, int weight) {
        this.ExerciseName = name;
        this.reps = reps;
        this.weight = weight;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public ExerciseSet() {

    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return "reps: " + reps +
                " weight: " + weight+"\n" ;

    }
}
