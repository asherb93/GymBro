package com.example.gymbro.Models;

import android.net.Uri;

public class ExerciseInfo {

    private String exerciseName;
    private String exerciseMuscleGroup;
    private String exerciseDescription;
    private String exerciseImage="";
    private Boolean isCollapsed = true;


    public ExerciseInfo() {

    }



    public String getExerciseName() {
        return exerciseName;
    }

    public ExerciseInfo setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
        return this;
    }

    public String getExerciseMuscleGroup() {
        return exerciseMuscleGroup;
    }

    public ExerciseInfo setExerciseMuscleGroup(String exerciseMuscleGroup) {
        this.exerciseMuscleGroup = exerciseMuscleGroup;
        return this;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public ExerciseInfo setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
        return this;
    }

    public String getExerciseImage() {
        return exerciseImage;
    }

    public ExerciseInfo setExerciseImage(String exerciseImage) {
        this.exerciseImage = exerciseImage;
        return this;
    }
}
