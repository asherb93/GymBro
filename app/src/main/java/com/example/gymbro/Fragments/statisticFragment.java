package com.example.gymbro.Fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.example.gymbro.Adapters.PersonalRecordsAdapter;
import com.example.gymbro.Data.UserStats;
import com.example.gymbro.Models.ExerciseStats;
import com.example.gymbro.R;
import com.example.gymbro.Utils.DataManager;
import com.example.gymbro.Utils.TimeFormatter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileReader;
import java.util.ArrayList;


public class statisticFragment extends Fragment {

    private TextView totalWorkoutsTextView;
    private TextView totalTimeTextView;
    private TextView favouriteExerciseTextView;
    private TextView exerciseFrequencyTextView;
    private TextView exercisesDoneTextView;
    private TextView helloUserTextView;

    private RecyclerView recyclerView;
    private PersonalRecordsAdapter adapter;

    private ArrayList<ExerciseStats> personalRecords;

    private UserStats userStats;

    private View view;



    public statisticFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initViews() {
        totalWorkoutsTextView.setText(""+userStats.getNumberOfWorkouts());
        totalTimeTextView.setText(""+ TimeFormatter.formatTime(userStats.getTotalTime()));
        favouriteExerciseTextView.setText(userStats.getFavouriteExerciseStats().getExerciseName());
        exerciseFrequencyTextView.setText("Performed: "+userStats.getFavouriteExerciseStats().getFrequency());
        exercisesDoneTextView.setText(""+userStats.getExercisesDone());

        adapter = new PersonalRecordsAdapter(personalRecords,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void findViews() {
         totalWorkoutsTextView = view.findViewById(R.id.total_workouts_textview);
         totalTimeTextView = view.findViewById(R.id.totalTime_textview);
         favouriteExerciseTextView = view.findViewById(R.id.favourite_exercise_textview);
         exerciseFrequencyTextView = view.findViewById(R.id.exercise_frequency_LBK);
         exercisesDoneTextView = view.findViewById(R.id.exercise_done_textview);
         helloUserTextView = view.findViewById(R.id.hello_user_textview);
         recyclerView = view.findViewById(R.id.personal_records_recyclerview);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_statistic, container, false);
        findViews();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference();
        ref.child("UserStats/"+firebaseAuth.getCurrentUser().getUid()).get().addOnCompleteListener(task -> {
            userStats = task.getResult().getValue(UserStats.class);
            if(userStats==null){
                userStats = new UserStats();
            }
            personalRecords = userStats.getExercisesPrArrayList();
            for(ExerciseStats e:personalRecords){
                e.setImage(DataManager.getImageByExerciseName(e.getExerciseName()));
            }
            initViews();


        });



        return view;
    }
}