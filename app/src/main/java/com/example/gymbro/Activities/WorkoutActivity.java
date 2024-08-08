package com.example.gymbro.Activities;

import android.content.Intent;
import android.graphics.Color;
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

import com.example.gymbro.Adapters.ExerciseAdapter;
import com.example.gymbro.Adapters.SummaryExerciseAdapter;
import com.example.gymbro.Models.Exercise;
import com.example.gymbro.Models.Workout;
import com.example.gymbro.R;
import com.example.gymbro.Utils.DataManager;
import com.example.gymbro.Utils.SignalManager;
import com.example.gymbro.Utils.TimeFormatter;

public class WorkoutActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView main_LBL_time;
    ExerciseAdapter exerciseAdapter;
    private static final long DELAY = 1000L;

    final Handler handler = new Handler();

    Button newExerciseButton;
    Button startButton;
    Button finishButton;
    AutoCompleteTextView exerciseAutoCompleteTextView;
    TextView emptyWorkoutTV;
    Workout workout = new Workout();





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
        findViews();
        initViews();

        startButton.setOnClickListener(v->{
            if(!workout.getExercises().isEmpty()) {
                startButton.setVisibility(View.GONE);
                startTime = System.currentTimeMillis();
                handler.postDelayed(runnable, 0);
                finishButton.setVisibility(View.VISIBLE);
                SignalManager.getInstance().toast("Workout started");
            }
            else{
                SignalManager.getInstance().toast("add at least one exercise to start workout");
            }
        });

        finishButton.setOnClickListener(v->{

            handler.removeCallbacks(runnable);


            Intent i = new Intent(this, workoutSummaryActivity.class);
            i.putExtra("workout", workout);
            startActivity(i);
            finish();

        });



        newExerciseButton.setOnClickListener(v-> {
            exerciseAutoCompleteTextView.setVisibility(EditText.VISIBLE);

        });
        initAutoComplete();

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
        exerciseAutoCompleteTextView.setThreshold(1);

        // Set the adapter for data as a list
        exerciseAutoCompleteTextView.setAdapter(autoCompleteAdapter);
        exerciseAutoCompleteTextView.setTextColor(Color.BLACK);

        exerciseAutoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            workout.getExercises().add(new Exercise(autoCompleteAdapter.getItem(position)));
            exerciseAutoCompleteTextView.setVisibility(View.GONE);
            exerciseAdapter.notifyItemInserted(workout.getExercises().size()-1);

            if(!workout.getExercises().isEmpty()){
                emptyWorkoutTV.setVisibility(View.GONE);
            }

        });
    }


    private void updateTimerUI() {
        long currentTime = System.currentTimeMillis();
        main_LBL_time.setText(TimeFormatter.formatTime(currentTime - startTime));

    }

    private void initViews() {

        exerciseAdapter = new ExerciseAdapter(workout.getExercises(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(exerciseAdapter);
    }

    private void findViews() {
        recyclerView = findViewById(R.id.workouts_recyclerview);
        main_LBL_time = findViewById(R.id.timer_LBL);
        newExerciseButton = findViewById(R.id.new_exercise_button);
        exerciseAutoCompleteTextView = findViewById(R.id.exercise_auto_complete_text_view);
        startButton = findViewById(R.id.start_workout_button);
        emptyWorkoutTV = findViewById(R.id.empty_workout_TV);
        finishButton = findViewById(R.id.finish_workout_button);



    }
}