package com.example.gymbro.Utils;


import com.example.gymbro.Models.Exercise;
import com.example.gymbro.Models.ExerciseInfo;
import com.example.gymbro.Models.ExerciseSet;
import com.example.gymbro.Models.Workout;

import java.util.ArrayList;

public class DataManager {

    public static ArrayList<String> getExercisesName() {
        ArrayList<String> exercisesName = new ArrayList<>();
        exercisesName.add("Bench Press");
        exercisesName.add("Pullups");
        exercisesName.add("Barbell Squats");
        exercisesName.add("Barbell Military Press");
        exercisesName.add("Barbell Bicep Curls");
        return exercisesName;
    }
    public static ArrayList<ExerciseInfo> getExercisesInfo() {
        ArrayList<ExerciseInfo> exerciseInfoArrayList = new ArrayList<>();
        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Bench Press")
                .setExerciseMuscleGroup("Chest")
                .setExerciseDescription("1. Setup:\n- Lie on a flat bench with your feet flat on the floor.\n- Grip the barbell slightly wider than shoulder-width apart.\n\n2. Execution:\n- Lift the barbell off the rack and hold it above your chest with arms fully extended.\n- Lower the barbell slowly until it touches your chest, keeping your elbows at around a 45-degree angle to your body.\n- Press the barbell back up to the starting position, exhaling as you push.")
                .setExerciseImage("https://i.pinimg.com/originals/08/60/37/08603700cb6365ab40466f4dd9d49e23.gif")
        );

        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Pullups")
                .setExerciseMuscleGroup("Back")
                .setExerciseDescription("1. Setup: Hang from a pull-up bar with an overhand grip, hands slightly wider than shoulder-width apart.\n2. Execution: Pull your body up until your chin is above the bar.\nLower yourself back down until your arms are fully extended.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2021/02/Pull-up.gif")

        );

        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Barbell Squats")
                .setExerciseMuscleGroup("Legs")
                .setExerciseDescription("1. Setup: Stand with feet shoulder-width apart and the barbell resting on your upper back.\n2. Execution: Lower your body by bending your knees and hips, keeping your back straight.\nDescend until your thighs are at least parallel to the floor.\nPush through your heels to return to the starting position.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2021/02/BARBELL-SQUAT.gif")
        );

        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Barbell Military Press")
                .setExerciseMuscleGroup("Shoulders")
                .setExerciseDescription("1. Setup: Stand with feet shoulder-width apart and hold the barbell at shoulder height with an overhand grip.\n2. Execution: Press the barbell overhead until your arms are fully extended.\nLower the barbell back to shoulder height.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2021/07/Barbell-Standing-Military-Press.gif")
        );

        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("Barbell Bicep Curls")
                .setExerciseMuscleGroup("Biceps")
                .setExerciseDescription("1. Setup: Stand with feet shoulder-width apart and hold a barbell or dumbbells at your sides with palms facing forward.\n2. Execution: Keeping your upper arms stationary, curl the weights forward while contracting your biceps. Continue until your biceps are fully contracted and the weights are at shoulder level. Lower the weights back to the starting position.")
                .setExerciseImage("https://fitnessprogramer.com/wp-content/uploads/2021/02/Barbell-Curl.gif")
        );
        exerciseInfoArrayList.add(new ExerciseInfo()
                .setExerciseName("")
                .setExerciseMuscleGroup("")
                .setExerciseDescription(" ")
                .setExerciseImage("")
        );
        return exerciseInfoArrayList;
    }

    public static ArrayList<Exercise> getWorkOutExercises(){
        ArrayList<ExerciseSet> exerciseSets1 = new ArrayList<>();
        ArrayList<ExerciseSet> exerciseSets2 = new ArrayList<>();

        ExerciseSet exerciseSet1 = new ExerciseSet(10,100);
        ExerciseSet exerciseSet2 = new ExerciseSet(10,100);

        exerciseSets1.add(exerciseSet1);
        exerciseSets1.add(exerciseSet1);
        exerciseSets1.add(exerciseSet1);

        exerciseSets2.add(exerciseSet2);
        exerciseSets2.add(exerciseSet2);
        exerciseSets2.add(exerciseSet2);


        ArrayList<Exercise> exerciseArrayList= new ArrayList<>();

        exerciseArrayList.add(new Exercise()
                .setExerciseName("Bench press")
                .setExerciseSets(exerciseSets1)
        );

        exerciseArrayList.add(new Exercise()
                .setExerciseName("Pull ups")
                .setExerciseSets(exerciseSets2)
        );

        return exerciseArrayList;
    }
}
