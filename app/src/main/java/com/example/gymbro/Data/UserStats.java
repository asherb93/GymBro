package com.example.gymbro.Data;

import com.example.gymbro.Models.ExerciseSet;
import com.example.gymbro.Models.ExerciseStats;
import com.example.gymbro.Utils.SignalManager;

import java.util.ArrayList;

public class UserStats  {

    // Some getters and setters in this class exist but not used in the app their porpuse is so the database can store them*/
    private String name;
    private int numberOfWorkouts = 0;
    private long totalTime = 0;
    private ArrayList<ExerciseStats> exercisesPrArrayList = new ArrayList<>();
    private ExerciseStats favouriteExerciseStats = new ExerciseStats();
    private int exercisesDone = 0;
    private int totalSets = 0;
    private int totalWeight = 0;

    // Constructors
    public UserStats() {
        // Default constructor
    }

    public UserStats(String name) {
        this.name = name;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public UserStats setName(String name) {
        this.name = name;
        return this;
    }

    public int getNumberOfWorkouts() {
        return numberOfWorkouts;
    }

    public void setNumberOfWorkouts(int numberOfWorkouts) {
        this.numberOfWorkouts = numberOfWorkouts;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public int getTotalSets() {
        return totalSets;
    }

    public UserStats setTotalSets(int totalSets) {
        this.totalSets = totalSets;
        return this;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public int getExercisesDone() {
        return exercisesDone;
    }

    public void setExercisesDone(int exercisesDone) {
        this.exercisesDone = exercisesDone;
    }

    public int getTotalReps() {
        return totalSets;
    }

    public void setTotalReps(int totalReps) {
        this.totalSets = totalReps;
    }

    public ExerciseStats getFavouriteExerciseStats() {
        return favouriteExerciseStats;
    }

    public void setFavouriteExerciseStats(ExerciseStats favouriteExerciseStats) {
        this.favouriteExerciseStats = favouriteExerciseStats;
    }

    public ArrayList<ExerciseStats> getExercisesPrArrayList() {
        return exercisesPrArrayList;
    }

    public void setExercisesPrArrayList(ArrayList<ExerciseStats> exercisesPrArrayList) {
        this.exercisesPrArrayList = exercisesPrArrayList;
    }

    /*given a finished workout the user stats will be updated*/
    public void updateStats(Workout workout) {
        numberOfWorkouts++;
        this.totalSets += workout.getTotalSets();
        this.totalWeight += workout.getTotalWeight();
        this.totalTime += workout.getWorkoutTime();
        updatePr(workout);
        this.exercisesDone = exercisesPrArrayList.size();
        setFavouriteExercise();
    }

    /*a method that determines the User's favourite workout according to it's frequency*/
    private void setFavouriteExercise() {
        if (exercisesPrArrayList.size() > 1) {
            favouriteExerciseStats = exercisesPrArrayList.get(0);
            for (int i = 1; i < exercisesPrArrayList.size(); i++) {
                if (favouriteExerciseStats.getFrequency() < exercisesPrArrayList.get(i).getFrequency()) {
                    favouriteExerciseStats = exercisesPrArrayList.get(i);
                }
            }
        } else if (exercisesPrArrayList.size() == 1) {
            favouriteExerciseStats = exercisesPrArrayList.get(0);
        } else {
            favouriteExerciseStats = new ExerciseStats();
        }
    }

    /*A method that updates user's PR array list according to the given finished workout*/
    private void updatePr(Workout workout) {
        ArrayList<ExerciseSet> getBestSetsArrayList = workout.getBestSets();
        for (ExerciseSet e : getBestSetsArrayList) {
            int bestSetWeight = e.getWeight();
            int bestSetReps = e.getReps();
            ExerciseStats exerciseStats = getExerciseStatsByName(e.getExerciseName());

            if (exerciseStats == null) {
                e.setPersonalRecord(true);
                workout.setPersonalRecords(e);
                exerciseStats = new ExerciseStats();
                exerciseStats.setExerciseName(e.getExerciseName());
                exerciseStats.setMaxWeight(bestSetWeight);
                exerciseStats.setMaxReps(bestSetReps);
                exerciseStats.setFrequency(1);
                workout.setNumberOfPrs(workout.getNumberOfPrs() + 1);

                exercisesPrArrayList.add(exerciseStats);
            } else {
                int currentMaxWeight = exerciseStats.getMaxWeight();
                int currentMaxReps = exerciseStats.getMaxReps();
                if (bestSetWeight > currentMaxWeight || (bestSetWeight == currentMaxWeight && bestSetReps > currentMaxReps)) {
                    workout.setPersonalRecords(e);
                    workout.setNumberOfPrs(workout.getNumberOfPrs() + 1);
                    exerciseStats.setMaxWeight(bestSetWeight);
                    exerciseStats.setMaxReps(bestSetReps);
                    exerciseStats.setFrequency(exerciseStats.getFrequency() + 1);
                } else {
                    exerciseStats.setFrequency(exerciseStats.getFrequency() + 1);
                }
            }
        }
    }

    private ExerciseStats getExerciseStatsByName(String name) {
        for (ExerciseStats e : exercisesPrArrayList) {
            if (e.getExerciseName().equals(name)) {
                return e;
            }
        }
        return null;
    }
}
