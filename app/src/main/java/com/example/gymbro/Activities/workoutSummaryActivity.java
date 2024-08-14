package com.example.gymbro.Activities;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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

import com.example.gymbro.Adapters.SummaryExerciseAdapter;
import com.example.gymbro.Data.UserStats;
import com.example.gymbro.Models.AppUser;
import com.example.gymbro.Models.Workout;
import com.example.gymbro.R;
import com.example.gymbro.Utils.DataManager;
import com.example.gymbro.Utils.SignalManager;
import com.example.gymbro.Utils.SoundPlayer;
import com.example.gymbro.Utils.TimeFormatter;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
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
    private AppUser appUser;
    private EditText workoutNameEditText;
    private Button saveWorkoutBtn;
    private Button dontSaveBtn;
    private DatabaseReference ref;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private SummaryExerciseAdapter adapter;
    private RecyclerView recyclerView;
    private UserStats userStats;


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
            DataManager.uploadUserStats(userStats);
            prTextView.setText("Personal records: "+workout.getNumberOfPrs());

            adapter.notifyDataSetChanged();

        });

        soundPlayer = new SoundPlayer(this);
        soundPlayer.playSoundOnce(R.raw.finish_workout_sound);
        findViews();
        initViews();


         saveWorkoutBtn.setOnClickListener(v->{
             saveWorkout();
         });

         dontSaveBtn.setOnClickListener(v->{
             Intent I = new Intent(this, MainActivity.class);
             startActivity(I);
             finish();
         });


    }

    private void initViews() {
        if(workout.isSaved()){
            workoutSaveTextView.setText("This workout already exists,would you like to overwrite it with these changes?");
        }
        totalRepsTextView.setText("Total Sets:"+workout.getTotalSets());
        totalWeightTextView.setText("Total Weight:"+workout.getTotalWeight()+"KG");
        totalTimeTextView.setText("Time: "+ TimeFormatter.formatTime(workout.getWorkoutTime()));

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
            firebaseAuth=FirebaseAuth.getInstance();
            SignalManager.getInstance().toast("Workout saved");
            ref= FirebaseDatabase.getInstance().getReference();
            DataManager.uploadWorkout(workout);
            Intent I = new Intent(this, MainActivity.class);
            startActivity(I);
            finish();


        }
        else {
            SignalManager.getInstance().toast("Please enter a workout name");
        }
    }


    private void findViews() {
        totalWeightTextView=findViewById(R.id.weight_textview);
        totalRepsTextView=findViewById(R.id.reps_textview);
        prTextView =findViewById(R.id.pr_textview);
        workoutNameEditText = findViewById(R.id.workout_name_textview);
        saveWorkoutBtn = findViewById(R.id.save_workout_btn);
        dontSaveBtn = findViewById(R.id.dont_save_btn);
        recyclerView = findViewById(R.id.sum_exercise_rv);
        workoutSaveTextView = findViewById(R.id.save_box_title);
        prTextView = findViewById(R.id.pr_textview);
        totalTimeTextView = findViewById(R.id.total_time_textview);
    }


}