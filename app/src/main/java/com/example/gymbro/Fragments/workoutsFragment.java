package com.example.gymbro.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymbro.Adapters.WorkoutRecyclerViewAdapter;
import com.example.gymbro.Models.Exercise;
import com.example.gymbro.Models.ExerciseSet;
import com.example.gymbro.Models.Workout;
import com.example.gymbro.R;

import java.util.ArrayList;

public class workoutsFragment extends Fragment {

    ArrayList<Exercise> exercises=new ArrayList<>();
    ArrayList<ExerciseSet> benchPressSets;
    Workout workout;
    RecyclerView recyclerView;
    View view;
    ArrayList<Workout> workoutArrayList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void createData() {

        Exercise benchPress = new Exercise("Bench Press");
        ExerciseSet benchSet = new ExerciseSet(10,100);
        benchPress.getExerciseSets().add(benchSet);
        benchPress.getExerciseSets().add(benchSet);
        benchPress.getExerciseSets().add(benchSet);
        benchPress.getExerciseSets().add(benchSet);
        benchPress.getExerciseSets().add(benchSet);
        benchPress.getExerciseSets().add(benchSet);
        benchPress.getExerciseSets().add(benchSet);
        benchPress.getExerciseSets().add(benchSet);
        benchPress.getExerciseSets().add(benchSet);
        benchPress.getExerciseSets().add(benchSet);
        benchPress.getExerciseSets().add(benchSet);
        benchPress.getExerciseSets().add(benchSet);
        benchPress.getExerciseSets().add(benchSet);
        benchPress.getExerciseSets().add(benchSet);
        benchPress.getExerciseSets().add(benchSet);

        Exercise PullUps = new Exercise("Pull ups");
        ExerciseSet pullUpSet = new ExerciseSet(10,100);
        PullUps.getExerciseSets().add(pullUpSet);
        PullUps.getExerciseSets().add(pullUpSet);
        PullUps.getExerciseSets().add(pullUpSet);
        PullUps.getExerciseSets().add(pullUpSet);
        PullUps.getExerciseSets().add(pullUpSet);
        PullUps.getExerciseSets().add(pullUpSet);
        PullUps.getExerciseSets().add(pullUpSet);
        PullUps.getExerciseSets().add(pullUpSet);
        PullUps.getExerciseSets().add(pullUpSet);
        PullUps.getExerciseSets().add(pullUpSet);
        PullUps.getExerciseSets().add(pullUpSet);
        PullUps.getExerciseSets().add(pullUpSet);
        PullUps.getExerciseSets().add(pullUpSet);
        PullUps.getExerciseSets().add(pullUpSet);

        exercises.add(benchPress);
        exercises.add(PullUps);

        Workout workout1 = new Workout("Strength","Strength workout");
        workout1.setExercises(exercises);

        workoutArrayList.add(workout1);
        workoutArrayList.add(workout1);
        workoutArrayList.add(workout1);
        workoutArrayList.add(workout1);
        //finish the adapter
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_workouts, container, false);
        createData();

        recyclerView = view.findViewById(R.id.workouts_recyclerview);
        WorkoutRecyclerViewAdapter adapter = new WorkoutRecyclerViewAdapter(getContext(),workoutArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}