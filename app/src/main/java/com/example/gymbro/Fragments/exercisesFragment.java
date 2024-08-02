package com.example.gymbro.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gymbro.Adapters.ExerciseInfoRecyclerViewAdapter;
import com.example.gymbro.Models.ExerciseInfo;
import com.example.gymbro.R;
import com.example.gymbro.Utils.DataManager;

import java.util.ArrayList;

public class exercisesFragment extends Fragment {

    RecyclerView exerciseInfoRecyclerView;
    View view;

    public exercisesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    private void findViews() {
        exerciseInfoRecyclerView = view.findViewById(R.id.exercises_RecyclerView);
    }

    private void initViews() {
        ArrayList<ExerciseInfo> ArrayList = DataManager.getExercisesInfo();

        ExerciseInfoRecyclerViewAdapter exerciseAdapter = new ExerciseInfoRecyclerViewAdapter(ArrayList,getContext());
        exerciseInfoRecyclerView.setAdapter(exerciseAdapter);
        exerciseInfoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exercises, container, false);
        findViews();
        initViews();

        // Inflate the layout for this fragment
        return view;

    }
}