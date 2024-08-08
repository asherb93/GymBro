package com.example.gymbro.Models;

public class ExercisePR {
    private String exerciseName;
    private int maxWeight;
    private int maxReps;

    public ExercisePR() {

    }

    public String getExerciseName() {
        return exerciseName;
    }

    public ExercisePR setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
        return this;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public ExercisePR setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
        return this;
    }

    public int getMaxReps() {
        return maxReps;
    }

    public ExercisePR setMaxReps(int maxReps) {
        this.maxReps = maxReps;
        return this;
    }
}
