package com.example.gymbro.Models;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ExerciseSet {
    int reps;
    int weight;

    public int getReps() {
        return reps;
    }

    public ExerciseSet(int reps, int weight) {
        this.reps = reps;
        this.weight = weight;
    }

    public ExerciseSet setReps(int reps) {
        this.reps = reps;
        return this;
    }



    public int getWeight() {
        return weight;
    }

    public ExerciseSet setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        return "reps: " + reps +
                " weight: " + weight+"\n" ;

    }
}
