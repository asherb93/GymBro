package com.example.gymbro.Activities;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.gymbro.Adapters.SummaryActivityAdapters.SummaryExerciseAdapter;
import com.example.gymbro.Data.UserStats;
import com.example.gymbro.Data.Workout;
import com.example.gymbro.R;
import com.example.gymbro.Utils.FirebaseManager;
import com.example.gymbro.Utils.SignalManager;
import com.example.gymbro.Utils.SoundPlayer;
import com.example.gymbro.Utils.TimeFormatter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class workoutSummaryActivity extends AppCompatActivity {

    private SoundPlayer soundPlayer;
    private Workout workout;
    private TextView totalWeightTextView;
    private TextView totalRepsTextView;
    private TextView prTextView;
    private TextView workoutSaveTextView;
    private TextView totalTimeTextView;
    private EditText workoutNameEditText;
    private Button saveWorkoutButton;
    private Button saveAsNewButton;
    private Button dontSaveButton;
    private DatabaseReference ref;
    private FirebaseAuth firebaseAuth;
    private SummaryExerciseAdapter adapter;
    private RecyclerView recyclerView;
    private UserStats userStats;
    private LottieAnimationView lottie_LOTTIE_animation;



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout_summary);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        // To retrieve object in second Activity
        workout = (Workout) getIntent().getSerializableExtra("workout");

        //init user stats
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        ref=mDatabase.getReference();
        ref.child("UserStats/"+firebaseAuth.getCurrentUser().getUid()).get().addOnCompleteListener(task -> {
            userStats = task.getResult().getValue(UserStats.class);
            if(userStats==null){
                userStats = new UserStats();
            }

            userStats.updateStats(workout);
            FirebaseManager.getInstance().uploadUserStats(userStats);
            prTextView.setText("Personal records: "+workout.getNumberOfPrs());
            adapter.notifyDataSetChanged();
        });

        soundPlayer = new SoundPlayer(this);
        soundPlayer.playSoundOnce(R.raw.workout_finish_sound);
        findViews();

        initViews();


         saveWorkoutButton.setOnClickListener(v->{
             saveWorkout();
         });
         saveAsNewButton.setOnClickListener(v->{
             saveAsNewWorkout();
         });

         dontSaveButton.setOnClickListener(v->{
             Intent I = new Intent(this, MainActivity.class);
             startActivity(I);
             finish();
         });
    }

    private void saveAsNewWorkout() {
        if(!workoutNameEditText.getText().equals(workout.getWorkoutName())){
            Workout newWorkout = new Workout(workout);
            newWorkout.setSaved(true);
            newWorkout.setWorkoutName(workoutNameEditText.getText().toString());
            FirebaseManager.getInstance().uploadWorkout(newWorkout);
            Intent I = new Intent(this, MainActivity.class);
            startActivity(I);
            finish();

        }
        else{
            SignalManager.getInstance().toast("Choose a different name then the saved workout");
        }
    }

    private void initViews() {
        if(workout.isSaved()){
            saveAsNewButton.setVisibility(View.VISIBLE);
            workoutSaveTextView.setText("This workout already exists as your saved workout would you like to overwrite it with these changes or save as a new workout?");
        }
        workoutNameEditText.setText(workout.getWorkoutName());
        totalRepsTextView.setText("Total Sets:"+workout.getTotalSets());
        totalWeightTextView.setText("Total Weight:"+workout.getTotalWeight()+"KG");
        totalTimeTextView.setText("Time: "+ TimeFormatter.formatTime(workout.getWorkoutTime()));
        startAnimation(lottie_LOTTIE_animation);

       // prTextView.setText("Personal records: "+workout.getNumberOfPrs());
        adapter = new SummaryExerciseAdapter(workout.getExercises(),this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void saveWorkout() {
        if(!workoutNameEditText.getText().toString().isEmpty()){
            workout.setSaved(true);
            workout.setWorkoutName(workoutNameEditText.getText().toString());
            SignalManager.getInstance().toast("Workout saved");
            FirebaseManager.getInstance().uploadWorkout(workout);
            Intent I = new Intent(this, MainActivity.class);
            startActivity(I);
            finish();
        }
        else {
            SignalManager.getInstance().toast("Please enter a workout name");
        }
    }


    private void findViews() {
        totalWeightTextView=findViewById(R.id.weight_TV);
        totalRepsTextView=findViewById(R.id.reps_TV);
        prTextView =findViewById(R.id.pr_TV);
        workoutNameEditText = findViewById(R.id.workout_name_TV);
        saveWorkoutButton = findViewById(R.id.save_workout_BTN);
        dontSaveButton = findViewById(R.id.dont_save_BTN);
        recyclerView = findViewById(R.id.sum_exercise_RV);
        workoutSaveTextView = findViewById(R.id.save_box_title_TV);
        prTextView = findViewById(R.id.pr_TV);
        totalTimeTextView = findViewById(R.id.total_time_TV);
        lottie_LOTTIE_animation = findViewById(R.id.finish_workout_LOTTIE_animation);
        saveAsNewButton = findViewById(R.id.save_as_new_Button);
    }


    private void startAnimation(LottieAnimationView view) {
        view.resumeAnimation();
    }



}