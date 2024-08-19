package com.example.gymbro.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

import com.example.gymbro.Adapters.WorkoutActivityAdapter.ExerciseAdapter;
import com.example.gymbro.Callbacks.StartRestCallback;
import com.example.gymbro.Models.Exercise;
import com.example.gymbro.Data.Workout;
import com.example.gymbro.R;
import com.example.gymbro.Utils.DataManager;
import com.example.gymbro.Utils.SharedPreferencesManager;
import com.example.gymbro.Utils.SignalManager;
import com.example.gymbro.Utils.SoundPlayer;
import com.example.gymbro.Utils.TimeFormatter;

public class WorkoutActivity extends AppCompatActivity {


    private static final long DELAY = 1000L;
    final Handler handler = new Handler();

    private long workoutTime=0;

    private Button newExerciseButton;
    private Button startButton;
    private Button finishButton;
    private AutoCompleteTextView exerciseAutoCompleteTextView;
    private Workout workout ;
    private EditText restMinutesEditText;
    private EditText restSecondsEditText;
    private RecyclerView recyclerView;
    private TextView workoutTimeTextView;
    private ExerciseAdapter exerciseAdapter;

    private final String SECONDS_KEY = "seconds";
    private final String MINUTES_KEY = "minutes";




    private long startTime ;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, DELAY);
            updateTimerUI();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_workout);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        workout = (Workout) getIntent().getSerializableExtra("workout");
        if(workout==null){
            workout=new Workout();
        }
        else{
            initSavedWorkout();
        }

        findViews();

        initViews();

        initAutoComplete();
    }

    private void initSavedWorkout() {
        workout.setNumberOfPrs(0);
        workout.clearPrsets();
        workout.clearBestSets();
        workout.uncheckAllSets();
    }

    private void initAutoComplete() {

        // Create the object of ArrayAdapter with String
        // which hold the data as the list item.
        ArrayAdapter<String> autoCompleteAdapter
                = new ArrayAdapter<String>(
                this,
                android.R.layout.select_dialog_item,
                DataManager.getExercisesName());

        // Give the suggestion after 1 words.
        exerciseAutoCompleteTextView.setThreshold(0);

        // Set the adapter for data as a list
        exerciseAutoCompleteTextView.setAdapter(autoCompleteAdapter);
        exerciseAutoCompleteTextView.setTextColor(Color.BLACK);

        exerciseAutoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            if(!workout.checkIfExerciseExists(autoCompleteAdapter.getItem(position))) {
                workout.getExercises().add(new Exercise(autoCompleteAdapter.getItem(position)));
                exerciseAutoCompleteTextView.setVisibility(View.GONE);
                exerciseAdapter.notifyItemInserted(workout.getExercises().size() - 1);
            }
            else{
                SignalManager.getInstance().toast("Exercise already exists");
            }
        });
    }


    private void updateTimerUI() {
        long currentTime = System.currentTimeMillis();
        workoutTime=currentTime-startTime;
        workoutTimeTextView.setText(TimeFormatter.formatTime(workoutTime));
    }



    private void findViews() {
        recyclerView = findViewById(R.id.workouts_RV);
        workoutTimeTextView = findViewById(R.id.timer_TV);
        newExerciseButton = findViewById(R.id.new_exercise_BTN);
        exerciseAutoCompleteTextView = findViewById(R.id.exercise_auto_complete_TV);
        startButton = findViewById(R.id.start_workout_BTN);
        finishButton = findViewById(R.id.finish_workout_BTN);
        restMinutesEditText = findViewById(R.id.resting_time_minute_ET);
        restSecondsEditText = findViewById(R.id.resting_time_seconds_ET);
    }


    private void initViews() {
        String restSeconds = SharedPreferencesManager.getInstance().getString(SECONDS_KEY, "00");
        String restMinutes = SharedPreferencesManager.getInstance().getString(MINUTES_KEY, "02");
        restMinutesEditText.setText(restMinutes);
        restSecondsEditText.setText(restSeconds);

        exerciseAdapter = new ExerciseAdapter(workout.getExercises(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(exerciseAdapter);


        startButton.setOnClickListener(v->{
            if(!workout.getExercises().isEmpty()) {
                startButton.setVisibility(View.GONE);
                startTime = System.currentTimeMillis();
                handler.postDelayed(runnable, 0);
                finishButton.setVisibility(View.VISIBLE);
                SoundPlayer soundPlayer = new SoundPlayer(this);
                soundPlayer.playSoundOnce(R.raw.bell_sound);
            }
            else{
                SignalManager.getInstance().toast("add at least one exercise to start workout");
            }
        });

        finishButton.setOnClickListener(v->{
            boolean isAllSetsChecked = workout.checkIfAllSetsAreChecked();
            boolean isAllExercisesHaveSets = workout.checkIfAllExercisesHaveSets();
            if(isAllSetsChecked&&isAllExercisesHaveSets) {
                handler.removeCallbacks(runnable);
                workout.setWorkoutTime(workoutTime);
                Intent i = new Intent(this, workoutSummaryActivity.class);
                i.putExtra("workout", workout);
                startActivity(i);
                finish();
            }
            else {
                if(!isAllSetsChecked){
                    SignalManager.getInstance().toast("check all sets");
                }
                if(!isAllExercisesHaveSets){
                    SignalManager.getInstance().toast("add at least one set to each exercise");
                }
                SignalManager.getInstance().vibrate(1000);
            }

        });

        newExerciseButton.setOnClickListener(v-> {
            exerciseAutoCompleteTextView.setVisibility(EditText.VISIBLE);

        });

        exerciseAdapter.setStartRestCallback(new StartRestCallback() {
            @Override
            public void startTimer() {
                int minutes = Integer.parseInt(restMinutesEditText.getText().toString());
                int seconds = Integer.parseInt(restSecondsEditText.getText().toString());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    if(minutes>0||seconds>0&&handler.hasCallbacks(runnable)) {
                        Intent i = new Intent(WorkoutActivity.this, RestingTimerActivity.class);
                        i.putExtra("Minutes", minutes);
                        i.putExtra("Seconds", seconds);
                        startActivity(i);
                    }
                }

            }
        });

    }

}