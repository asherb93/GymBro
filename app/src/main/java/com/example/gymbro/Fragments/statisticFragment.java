package com.example.gymbro.Fragments;

import android.animation.Animator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.gymbro.Adapters.StatsFragmentAdapter.PersonalRecordsAdapter;
import com.example.gymbro.Data.UserStats;
import com.example.gymbro.Models.ExerciseStats;
import com.example.gymbro.R;
import com.example.gymbro.Utils.DataManager;
import com.example.gymbro.Utils.TimeFormatter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class statisticFragment extends Fragment  {

    private TextView totalWorkoutsTextView;
    private TextView totalTimeTextView;
    private TextView favouriteExerciseTextView;
    private TextView exerciseFrequencyTextView;
    private TextView exercisesDoneTextView;
    private TextView helloUserTextView;
    private TextView numberOfPrsTextView;
    private TextView noWorkoutsTextView;
    private CardView stats_card_view;
    private LottieAnimationView lottie_LOTTIE_animation;
    private ConstraintLayout startConstraintLayout;




    private String userName;

    private RecyclerView recyclerView;
    private PersonalRecordsAdapter adapter;

    private ArrayList<ExerciseStats> personalRecords = new ArrayList<>();

    private UserStats userStats;

    private View view;



    public statisticFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void initViews(){
        if(userStats.getNumberOfWorkouts()>0) {
            noWorkoutsTextView.setVisibility(View.GONE);
            startConstraintLayout.setVisibility(View.VISIBLE);
        }
        stats_card_view.setVisibility(View.VISIBLE);
        helloUserTextView.setText("Hello "+userStats.getName()+" Here are your stats");
        totalWorkoutsTextView.setText(""+userStats.getNumberOfWorkouts());
        totalTimeTextView.setText(""+ TimeFormatter.formatTime(userStats.getTotalTime()));
        if(userStats.getFavouriteExerciseStats()==null||userStats.getFavouriteExerciseStats().getExerciseName()==null){
            favouriteExerciseTextView.setText("No favourite yet");
            exerciseFrequencyTextView.setText("");

        }
        else {
            favouriteExerciseTextView.setText(userStats.getFavouriteExerciseStats().getExerciseName());
            exerciseFrequencyTextView.setText("Performed: "+userStats.getFavouriteExerciseStats().getFrequency());

        }
        exercisesDoneTextView.setText(""+userStats.getExercisesDone());
        numberOfPrsTextView.setText("You have "+userStats.getExercisesPrArrayList().size()+" Personal records");
        adapter = new PersonalRecordsAdapter(personalRecords,getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }



    private void findViews() {
         totalWorkoutsTextView = view.findViewById(R.id.total_workouts_number_LBL);
         totalTimeTextView = view.findViewById(R.id.totalTime_LBL);
         favouriteExerciseTextView = view.findViewById(R.id.favourite_exercise_LBL);
         exerciseFrequencyTextView = view.findViewById(R.id.exercise_frequency_LBL);
         exercisesDoneTextView = view.findViewById(R.id.exercises_discovered_LBL);
         helloUserTextView = view.findViewById(R.id.hello_user_LBL);
         recyclerView = view.findViewById(R.id.personal_records_recyclerview);
        numberOfPrsTextView = view.findViewById(R.id.number_of_personal_records_LBL);
        stats_card_view = view.findViewById(R.id.fragment_stat_card_view);
        lottie_LOTTIE_animation = view.findViewById(R.id.stats_loading_LOTTIE_animation);
        noWorkoutsTextView = view.findViewById(R.id.no_stats_LBL);
        startConstraintLayout = view.findViewById(R.id.stats_layout);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_statistic, container, false);
        findViews();
        startAnimation(lottie_LOTTIE_animation);
        return view;
    }


    private void startAnimation(LottieAnimationView view) {
        view.playAnimation();
        view.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animation) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference ref = firebaseDatabase.getReference();
                ref.child("UserStats/"+firebaseAuth.getCurrentUser().getUid()).get().addOnCompleteListener(task -> {
                    userStats = task.getResult().getValue(UserStats.class);
                    assert userStats != null;
                    personalRecords = userStats.getExercisesPrArrayList();
                    for(ExerciseStats e:personalRecords){
                        e.setImage(DataManager.getImageByExerciseName(e.getExerciseName()));
                    }
                    initViews();
                    ref.keepSynced(true);
                });


            }

            @Override
            public void onAnimationEnd(@NonNull Animator animation) {

            }

            @Override
            public void onAnimationCancel(@NonNull Animator animation) {

                //pass
            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {
                //pass
            }
        });
    }

}