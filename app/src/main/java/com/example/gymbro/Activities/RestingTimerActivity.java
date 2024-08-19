package com.example.gymbro.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gymbro.R;
import com.example.gymbro.Utils.SharedPreferencesManager;
import com.example.gymbro.Utils.SignalManager;
import com.example.gymbro.Utils.SoundPlayer;
import com.example.gymbro.Utils.TimeFormatter;
import com.google.android.material.progressindicator.CircularProgressIndicator;

public class RestingTimerActivity extends AppCompatActivity {

    private static final long PLUS_30_SEC = 30000L;
    private static final long MINUS_30_SEC = -30000L;
    private static final long MAX_REST_TIME = 3570000L ;

    private Button skipRestButton;
    private Button plus30SecButton;
    private Button minus30SecButton;
    private TextView restingTimeTextView;
    private CircularProgressIndicator restingProgressBar;

    private final String SOUND_ID_KEY = "restTimeSoundId";

    private long restTime = 60000L;
    private long totalRestTime = 60000L;


    private static final long DELAY = 1000L;

    final Handler handler = new Handler();


    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this, DELAY);
            updateTimerUI();
        }
    };

    private void updateTimerUI() {
        if(restTime>0) {
            restTime = restTime - DELAY;//countdown timer
            float progress = (((float) restTime / totalRestTime) * 100);
            restingTimeTextView.setText(TimeFormatter.formatTimeNoHours(restTime));
            restingProgressBar.setProgress((int)progress,true);
        }
        else{
            handler.removeCallbacks(runnable);
            int soundId = SharedPreferencesManager.getInstance().getInteger(SOUND_ID_KEY,0);
            SoundPlayer soundPlayer = new SoundPlayer(this);
            soundPlayer.playSoundOnce(soundId);
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_resting_timer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        int minutes = getIntent().getIntExtra("Minutes",0);
        int seconds = getIntent().getIntExtra("Seconds",0);
        restTime = minutes*60000L+seconds*1000L;
        totalRestTime = restTime;
        findViews();
        runnable.run();
        initViews();

    }

    private void initViews() {

        skipRestButton.setOnClickListener(v-> {
            handler.removeCallbacks(runnable);
            finish();
        });

        plus30SecButton.setOnClickListener(v-> {

            if(restTime<MAX_REST_TIME) {
                restTime += PLUS_30_SEC;
                totalRestTime += PLUS_30_SEC;
                SignalManager.getInstance().vibrate(1000L);
            }
        });
        minus30SecButton.setOnClickListener(v-> {
            if(restTime>PLUS_30_SEC) {
                restTime += MINUS_30_SEC;
                totalRestTime+=MINUS_30_SEC;
                SignalManager.getInstance().vibrate(1000L);

            }
        });
    }

    private void findViews() {
        skipRestButton = findViewById(R.id.skip_rest_BTN);
        plus30SecButton = findViewById(R.id.plus_30_sec_BTN);
        minus30SecButton = findViewById(R.id.minus_30_sec_BTN);
        restingTimeTextView = findViewById(R.id.resting_time_TV);
        restingProgressBar = findViewById(R.id.resting_PROGRESS_BAR);
    }

}

