package com.example.gymbro.Fragments;

import android.content.Intent;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymbro.Activities.WorkoutActivity;
import com.example.gymbro.Adapters.WorkoutsFragmentAdapters.WorkoutRecyclerViewAdapter;
import com.example.gymbro.Callbacks.DeleteWorkoutCallback;
import com.example.gymbro.Callbacks.StartSavedWorkoutCallback;
import com.example.gymbro.Data.Workout;
import com.example.gymbro.R;
import com.example.gymbro.Utils.FirebaseManager;
import com.example.gymbro.Utils.SignalManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class workoutsFragment extends Fragment {

    RecyclerView recyclerView;
    View view;
    ArrayList<Workout> workoutArrayList =new ArrayList<>() ;
    private FirebaseDatabase mDatabase;
    private DatabaseReference ref;
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_workouts, container, false);

        recyclerView = view.findViewById(R.id.workouts_RV);

        WorkoutRecyclerViewAdapter adapter = new WorkoutRecyclerViewAdapter(getContext(),workoutArrayList);
        getWorkouts(adapter);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);


        adapter.setStartSavedWorkoutCallback(new StartSavedWorkoutCallback() {
            @Override
            public void startSavedWorkout(int position) {
                Workout workout = workoutArrayList.get(position);
                Intent i = new Intent(getContext(), WorkoutActivity.class);
                i.putExtra("workout", workout);
                startActivity(i);
            }
        });



        adapter.setDeleteWorkoutCallback(new DeleteWorkoutCallback() {
            @Override
            public void deleteWorkoutFromDB(int workoutId, int position) {
                Workout workout = workoutArrayList.get(position);
                workoutArrayList.remove(position);
                adapter.notifyItemRangeRemoved(0,workoutArrayList.size());
                FirebaseManager.getInstance().deleteWorkout(workout.getWorkoutId());
                workoutArrayList=new ArrayList<>();

           }
        });
        return view;

    }



    private void getWorkouts(WorkoutRecyclerViewAdapter adapter) {
        //get userid
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String userId = user.getUid();
        mDatabase = FirebaseDatabase.getInstance();


        ref= mDatabase.getReference().child("Workouts/"+userId+"/userWorkouts");


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(workoutArrayList!=null){
                    workoutArrayList.clear();
                    adapter.notifyDataSetChanged();
                }
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Workout workout= ds.getValue(Workout.class);
                    if (workout != null) {
                        workoutArrayList.add(workout);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                SignalManager.getInstance().toast("Failed to load workouts");
            }
        });


    }
}