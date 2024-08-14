package com.example.gymbro.Data;

import com.example.gymbro.Models.ExerciseSet;
import com.example.gymbro.Models.ExerciseStats;
import com.example.gymbro.Models.Workout;
import com.example.gymbro.Utils.SignalManager;

import java.util.ArrayList;

public class UserStats {
    private static int numberOfWorkouts=0;
    private long totalTime=0;
    private ArrayList<ExerciseStats> exercisesPrArrayList = new ArrayList<>();
    private ExerciseStats favouriteExerciseStats = new ExerciseStats();
    private int exercisesDone=0;
    private int totalSets=0;
    private int totalWeight=0;




    public void updateStats(Workout workout){
        numberOfWorkouts++;
        this.totalSets+=workout.getTotalSets();//done
        this.totalWeight+=workout.getTotalWeight();
        this.totalTime+=workout.getWorkoutTime();
        updatePr(workout);
        this.exercisesDone = exercisesPrArrayList.size();//done
        setFavouriteExercise();
    }

    public ExerciseStats getFavouriteExerciseStats() {
        return favouriteExerciseStats;
    }

    public void setFavouriteExerciseStats(ExerciseStats favouriteExerciseStats) {
        this.favouriteExerciseStats = favouriteExerciseStats;
    }

    private void setFavouriteExercise() {
        if(exercisesPrArrayList.size()>1){
            favouriteExerciseStats = exercisesPrArrayList.get(0);
            for(int i=1;i<exercisesPrArrayList.size();i++){
             if(favouriteExerciseStats.getFrequency()<exercisesPrArrayList.get(i).getFrequency()){
                 favouriteExerciseStats = exercisesPrArrayList.get(i);
             }
            }
        }
        else if(exercisesPrArrayList.size()==1){
            favouriteExerciseStats = exercisesPrArrayList.get(0);
        }
        else{
            favouriteExerciseStats = new ExerciseStats();
        }
    }


    private void updatePr(Workout workout) {
        ArrayList<ExerciseSet> getBestSetsArrayList = workout.getBestSets();
        for(ExerciseSet e: getBestSetsArrayList){
            int bestSetWeight = e.getWeight();
            int bestSetReps = e.getReps();
            ExerciseStats exerciseStats = getExerciseStatsByName(e.getExerciseName());
            if(exerciseStats==null){
                e.setPersonalRecord(true);
                workout.setPersonalRecords(e);
                SignalManager.getInstance().toast(e.getExerciseName()+"Exercise not found in pr array");
                exerciseStats = new ExerciseStats();
                exerciseStats.setExerciseName(e.getExerciseName());
                exerciseStats.setMaxWeight(bestSetWeight);
                exerciseStats.setMaxReps(bestSetReps);
                exerciseStats.setFrequency(1);
                workout.setNumberOfPrs(workout.getNumberOfPrs()+1);

                exercisesPrArrayList.add(exerciseStats);
            }
            else{
                int currentMaxWeight = exerciseStats.getMaxWeight();
                int currentMaxReps = exerciseStats.getMaxReps();
                if(bestSetWeight>currentMaxWeight||(bestSetWeight==currentMaxWeight&&bestSetReps>currentMaxReps)) {
                    workout.setPersonalRecords(e);
                    workout.setNumberOfPrs(workout.getNumberOfPrs()+1);
                    exerciseStats.setMaxWeight(bestSetWeight);
                    exerciseStats.setMaxReps(bestSetReps);
                    exerciseStats.setFrequency(exerciseStats.getFrequency()+1);
                    SignalManager.getInstance().toast("New personal record"+"Weight: "+bestSetWeight+"Reps: "+bestSetReps+"Exercise: "+e.getExerciseName());
                }
                else{
                    exerciseStats.setFrequency(exerciseStats.getFrequency()+1);
                    SignalManager.getInstance().toast("No new personal records");
                }
            }
        }

    }

    private ExerciseStats getExerciseStatsByName(String name){
        for(ExerciseStats e: exercisesPrArrayList){
            if(e.getExerciseName().equals(name)){
                return e;
            }
        }
        return null;
    }


    public UserStats() {
        //default constructor
    }

    public int getNumberOfWorkouts() {
        return numberOfWorkouts;
    }


    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public static void setNumberOfWorkouts(int numberOfWorkouts) {
        UserStats.numberOfWorkouts = numberOfWorkouts;
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

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }


    public ArrayList<ExerciseStats> getExercisesPrArrayList() {
        return exercisesPrArrayList;
    }

    public void setExercisesPrArrayList(ArrayList<ExerciseStats> exercisesPrArrayList) {
        this.exercisesPrArrayList = exercisesPrArrayList;
    }



}
