package com.example.gymbro.Utils;


import android.provider.ContactsContract;

import androidx.annotation.NonNull;

import com.example.gymbro.App;
import com.example.gymbro.Models.AppUser;
import com.example.gymbro.Models.Exercise;
import com.example.gymbro.Models.ExerciseInfo;
import com.example.gymbro.Models.ExerciseSet;
import com.example.gymbro.Models.Workout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DataManager {


    public static ArrayList<Workout> workoutArrayList=new ArrayList<>();
    public static ArrayList<Exercise> exercisesArrayList = new ArrayList<>();
    public static AppUser appUser;
    private static FirebaseDatabase mDatabase;
    private static  DatabaseReference ref;
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();;
    public static ArrayList<String> getExercisesName() {
        ArrayList<ExerciseInfo> exerciseInfoArrayList = getExercisesInfo();
        ArrayList<String> exerciseNamesArrayList = new ArrayList<>();
        for(int i=0;i<exerciseInfoArrayList.size();i++)
        {
            exerciseNamesArrayList.add(exerciseInfoArrayList.get(i).getExerciseName());
        }
        return exerciseNamesArrayList;
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


    public static void addObjectToFireBase(Object o,String path){
        mDatabase = FirebaseDatabase.getInstance();
        ref = mDatabase.getReference();
        ref.child(path).setValue(o).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                SignalManager.getInstance().toast(o.getClass().getSimpleName()+" added to data base");
            }
            }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public static void uploadUser(String name, int weight){
        mDatabase = FirebaseDatabase.getInstance();
        ref=mDatabase.getReference();
        AppUser user = new AppUser(name,weight);
        ref.child("Users/"+mAuth.getCurrentUser().getUid()+"/").setValue(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        SignalManager.getInstance().toast("Account created successfuly");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        SignalManager.getInstance().toast("Account creation failed");
                    }
                });
    }

    public static void uploadWorkout(Workout workout){
        mDatabase = FirebaseDatabase.getInstance();
        ref=mDatabase.getReference();
        ref.child("Workouts/"+mAuth.getCurrentUser().getUid()+"/userWorkouts/"+workout.getWorkoutId()).setValue(workout)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        SignalManager.getInstance().toast("Workout uploaded successfully");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        SignalManager.getInstance().toast("Account creation failed");
                    }
                });
    }




}
