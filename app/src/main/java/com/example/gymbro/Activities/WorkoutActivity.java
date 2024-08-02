package com.example.gymbro.Activities;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Adapter;
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
import com.example.gymbro.Models.Exercise;
import com.example.gymbro.Models.Workout;
import com.example.gymbro.R;
import com.example.gymbro.Utils.DataManager;
import com.example.gymbro.Utils.SignalManager;
import com.example.gymbro.Utils.TimeFormatter;

public class WorkoutActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView main_LBL_time;
    ExerciseAdapter adapter;
    private static final long DELAY = 1000L;

    final Handler handler = new Handler();

    Button newExerciseButton;
    AutoCompleteTextView exerciseAutoCompleteTextView;

    Workout workout = new Workout("New workout","");




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
        startTime = System.currentTimeMillis();
        handler.postDelayed(runnable, 0);
        findViews();
        initViews();
        newExerciseButton.setOnClickListener(v-> {
            exerciseAutoCompleteTextView.setVisibility(EditText.VISIBLE);
            adapter.notifyDataSetChanged();
        });
        initAutoComplete();


    }

    private void initAutoComplete() {

        // Create the object of ArrayAdapter with String
        // which hold the data as the list item.
        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(
                this,
                android.R.layout.select_dialog_item,
                DataManager.getExercisesName());

        // Give the suggestion after 1 words.
        exerciseAutoCompleteTextView.setThreshold(1);

        // Set the adapter for data as a list
        exerciseAutoCompleteTextView.setAdapter(adapter);
        exerciseAutoCompleteTextView.setTextColor(Color.BLACK);

        exerciseAutoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            SignalManager.getInstance().toast("Selected: " + adapter.getItem(position));
            workout.getExercises().add(new Exercise(""+adapter.getItem(position)));

        });
    }


    private void updateTimerUI() {
        long currentTime = System.currentTimeMillis();
        main_LBL_time.setText(TimeFormatter.formatTime(currentTime - startTime));

    }

    private void initViews() {

        adapter = new ExerciseAdapter(workout.getExercises(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void findViews() {
        recyclerView = findViewById(R.id.workouts_recyclerview);
        main_LBL_time = findViewById(R.id.timer_LBL);
        newExerciseButton = findViewById(R.id.new_exercise_button);
        exerciseAutoCompleteTextView = findViewById(R.id.exercise_auto_complete_text_view);


    }
}