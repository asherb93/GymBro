package com.example.gymbro.Models;

public class ExerciseStats {
    private String exerciseName;
    private int maxWeight;
    private int maxReps;
    private int frequency;

    public ExerciseStats() {

    }

    public String getExerciseName() {
        return exerciseName;
    }

    public ExerciseStats setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
        return this;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public ExerciseStats setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
        return this;
    }

    public int getMaxReps() {
        return maxReps;
    }

    public ExerciseStats setMaxReps(int maxReps) {
        this.maxReps = maxReps;
        return this;
    }
}
