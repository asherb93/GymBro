package com.example.gymbro.Callbacks;

public interface DeleteWorkoutCallback {
    /*callback used to update the main activity about a workout deletion so it can update the database*/
    void deleteWorkoutFromDB(int workoutId,int position);
}
