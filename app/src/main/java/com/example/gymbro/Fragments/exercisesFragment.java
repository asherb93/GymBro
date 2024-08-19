package com.example.gymbro.Fragments;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gymbro.Adapters.ExerciseInfoAdapters.ExerciseInfoRecyclerViewAdapter;
import com.example.gymbro.Models.ExerciseInfo;
import com.example.gymbro.R;
import com.example.gymbro.Utils.DataManager;
import com.example.gymbro.Utils.SignalManager;

import java.util.ArrayList;

public class exercisesFragment extends Fragment {

    private RecyclerView exerciseInfoRecyclerView;
    private View view;

    private ArrayList<ExerciseInfo> exerciseInfoArrayList = new ArrayList<>();

    private ExerciseInfoRecyclerViewAdapter exerciseAdapter;

    private final String SHOULDERS = "Shoulders";
    private final String CHEST = "Chest";
    private final String BACK = "Back";
    private final String LEGS = "Legs";
    private final String BICEPS = "Biceps";
    private final String TRICEPS = "Triceps";
    private final String ABS = "Abs";

    private AppCompatButton chestBtn;
    private AppCompatButton backBtn;
    private AppCompatButton shoulderBtn;
    private  AppCompatButton legsBtn;
    private AppCompatButton bicepsBtn;
    private AppCompatButton tricepsBtn;
    private AppCompatButton absBtn;
    private AppCompatButton allBtn;

    public exercisesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    private void findViews() {
        exerciseInfoRecyclerView = view.findViewById(R.id.exercises_RecyclerView);
        chestBtn = view.findViewById(R.id.chest_button);
        backBtn = view.findViewById(R.id.back_button);
        shoulderBtn = view.findViewById(R.id.shoulder_button);
        legsBtn = view.findViewById(R.id.legs_button);
        bicepsBtn = view.findViewById(R.id.biceps_button);
        tricepsBtn = view.findViewById(R.id.triceps_button);
        absBtn = view.findViewById(R.id.abs_button);
        allBtn = view.findViewById(R.id.all_button);

    }

    private void initViews() {
        ArrayList<ExerciseInfo> exerciseInfoArrayList = DataManager.getExercisesInfo();
        exerciseAdapter = new ExerciseInfoRecyclerViewAdapter(exerciseInfoArrayList,getContext());
        exerciseInfoRecyclerView.setAdapter(exerciseAdapter);
        exerciseInfoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void chooseMuscleGroup() {
        chestBtn.setOnClickListener(v -> {
            exerciseInfoArrayList = DataManager.getExercisesInfoByMuscleGroup(CHEST);
            exerciseAdapter.setExerciseInfoArrayList(exerciseInfoArrayList);

        });
        backBtn.setOnClickListener(v -> {
            exerciseInfoArrayList = DataManager.getExercisesInfoByMuscleGroup(BACK);
            exerciseAdapter.setExerciseInfoArrayList(exerciseInfoArrayList);

        });
        shoulderBtn.setOnClickListener(v -> {
            exerciseInfoArrayList = DataManager.getExercisesInfoByMuscleGroup(SHOULDERS);
            exerciseAdapter.setExerciseInfoArrayList(exerciseInfoArrayList);

        });

        legsBtn.setOnClickListener(v -> {
            exerciseInfoArrayList = DataManager.getExercisesInfoByMuscleGroup(LEGS);
            exerciseAdapter.setExerciseInfoArrayList(exerciseInfoArrayList);

        });
        bicepsBtn.setOnClickListener(v -> {
            exerciseInfoArrayList = DataManager.getExercisesInfoByMuscleGroup(BICEPS);
            exerciseAdapter.setExerciseInfoArrayList(exerciseInfoArrayList);

        });
        tricepsBtn.setOnClickListener(v -> {
            exerciseInfoArrayList = DataManager.getExercisesInfoByMuscleGroup(TRICEPS);
            exerciseAdapter.setExerciseInfoArrayList(exerciseInfoArrayList);

        });
        absBtn.setOnClickListener(v -> {
            exerciseInfoArrayList = DataManager.getExercisesInfoByMuscleGroup(ABS);
            exerciseAdapter.setExerciseInfoArrayList(exerciseInfoArrayList);

        });
        allBtn.setOnClickListener(v -> {
            exerciseInfoArrayList = DataManager.getExercisesInfo();
            exerciseAdapter.setExerciseInfoArrayList(exerciseInfoArrayList);

        });



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exercises, container, false);
        findViews();
        initViews();
        chooseMuscleGroup();
        return view;

    }

}