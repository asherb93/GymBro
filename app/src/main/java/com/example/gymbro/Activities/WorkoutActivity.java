package com.example.gymbro.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Adapter;
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
import com.example.gymbro.R;
import com.example.gymbro.Utils.DataManager;
import com.example.gymbro.Utils.TimeFormatter;

public class WorkoutActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView main_LBL_time;
    ExerciseAdapter adapter;
    private static final long DELAY = 1000L;

    final Handler handler = new Handler();

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

    }

    private void updateTimerUI() {
        long currentTime = System.currentTimeMillis();
        main_LBL_time.setText(TimeFormatter.formatTime(currentTime - startTime));

    }

    private void initViews() {
        adapter = new ExerciseAdapter(DataManager.getWorkOutExercises(), this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void findViews() {
        recyclerView = findViewById(R.id.workouts_recyclerview);
        main_LBL_time = findViewById(R.id.timer_LBL);
    }
}