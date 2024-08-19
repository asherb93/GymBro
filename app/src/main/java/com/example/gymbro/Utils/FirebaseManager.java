package com.example.gymbro.Utils;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.gymbro.Data.UserStats;
import com.example.gymbro.Data.Workout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseManager {

    //This class only works when changing data but getting data is not supported because Firebase is asynchronous//

    private static volatile FirebaseManager instance = null;
    private static Context context;

    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
    private static DatabaseReference ref = mDatabase.getReference();



    private FirebaseManager(Context context) {
        this.context = context;
    }

    public static FirebaseManager getInstance() {
        return instance;
    }

    public static FirebaseManager initFirebaseManager(Context context) {
        if (instance == null) {
            synchronized (FirebaseManager.class) {
                if (instance == null)
                    instance = new FirebaseManager(context);
            }
        }
        return getInstance();
    }

    public void deleteWorkout(int workoutId) {
        ref.child("Workouts/" + mAuth.getCurrentUser().getUid() + "/userWorkouts/" + workoutId).removeValue();
    }

    public void deleteAllWorkouts() {
        try {
            ref.child("Workouts/" + mAuth.getCurrentUser().getUid() + "/userWorkouts").removeValue();
        }
        catch (Exception e){
            SignalManager.getInstance().toast("error"+e.getMessage());
        }
    }


    public void uploadWorkout(Workout workout) {
        ref.child("Workouts/" + mAuth.getCurrentUser().getUid() + "/userWorkouts/" + workout.getWorkoutId()).setValue(workout)
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


    public void uploadUserStats(UserStats userStats) {
        ref.child("UserStats/" + mAuth.getCurrentUser().getUid()).setValue(userStats);
    }

    public void uploadWorkouts(ArrayList<Workout> templateWorkouts) {
        for (Workout t : templateWorkouts) {
            ref.child("Workouts/" + mAuth.getCurrentUser().getUid() + "/userWorkouts/" + t.getWorkoutId()).setValue(t).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    SignalManager.getInstance().toast("Workouts uploaded successfully");
                }
            });
        }
    }


    public void deleteUserData() {
        try{
            ref.child("UserStats/" + mAuth.getCurrentUser().getUid()).get().addOnCompleteListener(task -> {
                UserStats user = task.getResult().getValue(UserStats.class);
                UserStats userStats = new UserStats();
                userStats.setName(user.getName());
                FirebaseManager.getInstance().uploadUserStats(userStats);
            });
            FirebaseManager.getInstance().deleteAllWorkouts();
        }
        catch (Exception e){
            SignalManager.getInstance().toast("error"+e.getMessage());
        }
    }
}
