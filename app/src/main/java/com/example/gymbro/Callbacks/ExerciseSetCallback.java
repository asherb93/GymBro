package com.example.gymbro.Callbacks;

import com.example.gymbro.Models.ExerciseSet;

public interface ExerciseSetCallback {
    /*A callback in a nested recycler view to update the parent adapter about data change inside of a child adapter*/
    void exerciseSetChecked(ExerciseSet exerciseSet, int position);

}
