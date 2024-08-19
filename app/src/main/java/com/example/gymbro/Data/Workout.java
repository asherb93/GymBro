package com.example.gymbro.Data;

import androidx.annotation.NonNull;

import com.example.gymbro.Models.Exercise;
import com.example.gymbro.Models.ExerciseSet;

import java.io.Serializable;
import java.util.ArrayList;

public class Workout implements Serializable {

    // Fields
    private String workoutName;
    private ArrayList<Exercise> exercises = new ArrayList<>();
    private final int workoutId = (int) (Math.random() * 100000);
    private boolean isSaved = false;
    private long workoutTime = 0;
    private ArrayList<ExerciseSet> bestSets = new ArrayList<>();
    private int numberOfPrs = 0;

    // Constructors
    public Workout() {

    }

    public Workout(Workout workout){
        this.exercises = workout.getExercises();
        this.isSaved = workout.isSaved();
    }

    // Getters and Setters
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

    public int getWorkoutId() {
        return workoutId;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }

    public long getWorkoutTime() {
        return workoutTime;
    }

    public void setWorkoutTime(long workoutTime) {
        this.workoutTime = workoutTime;
    }

    public ArrayList<ExerciseSet> getBestSets() {
        for (Exercise e : exercises) {
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

    // Methods
    public boolean checkIfAllExercisesHaveSets() {
        for (Exercise exercise : exercises) {
            if (exercise.getExerciseSets() == null || exercise.getExerciseSets().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkIfExerciseExists(String item) {
        for (Exercise exercise : exercises) {
            if (exercise.getExerciseName().equals(item)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfAllSetsAreChecked() {
        for (Exercise exercise : exercises) {
            if (!exercise.checkIfAllSetsAreChecked()) {
                return false;
            }
        }
        return true;
    }

    public void uncheckAllSets() {
        for (Exercise exercise : exercises) {
            exercise.uncheckAllSets();
        }
    }

    public int getTotalSets() {
        int totalSets = 0;
        for (Exercise exercise : exercises) {
            totalSets += exercise.getTotalSets();
        }
        return totalSets;
    }

    public int getTotalWeight() {
        int totalWeight = 0;
        for (Exercise exercise : exercises) {
            totalWeight += exercise.getTotalWeight();
        }
        return totalWeight;
    }

    public void clearPrsets() {
        for (Exercise exercise : exercises) {
            exercise.clearPrsets();
        }
    }

    public void clearBestSets() {
        bestSets = new ArrayList<>();
    }

    public void setPersonalRecords(ExerciseSet es) {
        for (Exercise exercise : exercises) {
            exercise.setPersonalRecords(es);
        }
    }

    public String getWorkoutSummary() {
        return toString().replaceAll("]", "");
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (exercises != null) {
            for (int i = 0; i < exercises.size(); i++) {
                if (exercises.get(i).getExerciseSets() != null) {
                    stringBuilder.append(exercises.get(i).exerciseName)
                            .append(" x ")
                            .append(exercises.get(i).getExerciseSets().size());
                }
                if (i < exercises.size() - 1) {
                    stringBuilder.append("\n");
                }
            }
        }
        return stringBuilder.toString();
    }
}
