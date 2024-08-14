package com.example.gymbro.Models;

import android.net.Uri;

public class ExerciseInfo {

    public static final int MAX_LINES_COLLAPSED = 1;
    public static final int MIN_LINES_COLLAPSED = 1;



    private final int exerciseId;
    public static int idCounter = 0;
    private String exerciseName;

    private String exerciseMuscleGroup;
    private String exerciseDescription;
    private String exerciseImage="";
    private Boolean isCollapsed = true;


    public ExerciseInfo() {
        this.exerciseId = idCounter++;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public int getExerciseId() {
        return exerciseId;
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

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        ExerciseInfo.idCounter = idCounter;
    }

    public Boolean getCollapsed() {
        return isCollapsed;
    }

    public ExerciseInfo setCollapsed(Boolean collapsed) {
        isCollapsed = collapsed;
        return this;
    }

    public boolean isCollapsed() {
        return isCollapsed;
    }
}
