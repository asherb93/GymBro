package com.example.gymbro.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.gymbro.Data.UserStats;
import com.example.gymbro.R;
import com.example.gymbro.Utils.FirebaseManager;
import com.example.gymbro.Utils.SharedPreferencesManager;
import com.example.gymbro.Utils.SignalManager;
import com.example.gymbro.Utils.SoundPlayer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class userSettingsFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private EditText changeNameEditText;
    private EditText changeWeightEditText;
    private Button saveChangesButton;
    private Button clearAllDataButton;
    private LinearLayout warningLinearLayout;
    private Button okButton;
    private Button cancelButton;
    private EditText deleteEditText;
    private Spinner restTimeSoundSpinner;
    private EditText secondsRestTimeEditText;
    private EditText minutesRestTimeEditText;

    private String userChosenSound;


    private final String NO_SOUND = "None";
    private final String BELL_SOUND = "Default-Bell";
    private final String CLASSIC_SOUND = "Classic Alarm";
    private final String DIGITAL_SOUND = "Digital";

    private final String SPINNER_POSITION_KEY = "spinnerPosition";

    private final String SECONDS_KEY = "seconds";
    private final String MINUTES_KEY = "minutes";
    private final String SOUND_ID_KEY = "restTimeSoundId";
    private int soundId;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference();
    private int spinnerPosition;

    public userSettingsFragment() {
        // Required empty public constructor
    }

    public static userSettingsFragment newInstance(String param1, String param2) {
        userSettingsFragment fragment = new userSettingsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_settings, container, false);
        findViews(view);
        initSpinner();
        initViews();
        return view;
    }


    private void initSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.rest_time_sounds_array,
                android.R.layout.simple_spinner_item
        );

        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        try {
            String userSound;
        } catch (Exception e) {
            SignalManager.getInstance().toast("spinner error");
        }
        restTimeSoundSpinner.setAdapter(adapter);
        restTimeSoundSpinner.setOnItemSelectedListener(this);
    }


    private void initViews() {


        int prefSeconds = Integer.parseInt(SharedPreferencesManager.getInstance().getString(SECONDS_KEY, "00"));
        int prefMinutes = Integer.parseInt(SharedPreferencesManager.getInstance().getString(MINUTES_KEY, "02"));
        if(prefSeconds<10){
            secondsRestTimeEditText.setHint("0"+prefSeconds);
        }
        else {
            secondsRestTimeEditText.setHint(""+prefSeconds);
        }
        if(prefMinutes<10){
            minutesRestTimeEditText.setHint("0"+prefMinutes);
        }
        else {
            minutesRestTimeEditText.setHint(""+prefMinutes);
        }

        saveChangesButton.setOnClickListener(v -> {
            if (!changeNameEditText.getText().toString().isEmpty()) {
                ref.child("UserStats/" + auth.getCurrentUser().getUid()).child("name").setValue(changeNameEditText.getText().toString());
                SignalManager.getInstance().toast("Name changed to " + changeNameEditText.getText().toString());
            }
            if(!userChosenSound.isEmpty()){
                SharedPreferencesManager.getInstance().putInteger(SOUND_ID_KEY,soundId);
                SignalManager.getInstance().toast("sound changed to "+userChosenSound);
            }

            String secondString = secondsRestTimeEditText.getText().toString();
            String minuteString = minutesRestTimeEditText.getText().toString();

            int minutes ;
            int seconds ;
            boolean isMinutesValid;
            boolean isSecondsValid;


            if(!secondString.isEmpty()&&minuteString.isEmpty()){//only update seconds
                seconds = Integer.parseInt(secondsRestTimeEditText.getText().toString());
                isSecondsValid = !(seconds/60>0);//checks if number of entered seconds is bigger than 60
                if(isSecondsValid){
                    SharedPreferencesManager.getInstance().putString(SECONDS_KEY,secondsRestTimeEditText.getText().toString());
                    SignalManager.getInstance().toast("Rest time set to "+prefMinutes+":"+secondsRestTimeEditText.getText().toString());
                }
            }
            else if(!minuteString.isEmpty()&&secondString.isEmpty()){//only update minutes
                minutes = Integer.parseInt(minutesRestTimeEditText.getText().toString());
                 isMinutesValid = !(minutes/60>0);//checks if number of entered minutes is bigger than 60
                if(isMinutesValid) {
                    SharedPreferencesManager.getInstance().putString(MINUTES_KEY, minutesRestTimeEditText.getText().toString());
                    SignalManager.getInstance().toast("Rest time set to " + minutesRestTimeEditText.getText().toString() + ":" + prefSeconds);
                }
                else{
                    SignalManager.getInstance().toast("Invalid minutes input");
                }
            }
            else if(!minuteString.isEmpty()&&!secondString.isEmpty()) {//update both note:second string check is still there for clarification
                seconds = Integer.parseInt(secondsRestTimeEditText.getText().toString());
                isSecondsValid = !(seconds/60>0);//checks if number of entered seconds is bigger than 60
                minutes = Integer.parseInt(minutesRestTimeEditText.getText().toString());
                isMinutesValid = !(minutes/60>0);//checks if number of entered minutes is bigger than 60

                if(isSecondsValid&&isMinutesValid) {
                    SharedPreferencesManager.getInstance().putString(SECONDS_KEY, secondsRestTimeEditText.getText().toString());
                    SharedPreferencesManager.getInstance().putString(MINUTES_KEY, minutesRestTimeEditText.getText().toString());
                    SignalManager.getInstance().toast("Rest time set to "+minutesRestTimeEditText.getText().toString()+":"+secondsRestTimeEditText.getText().toString());
                }
                else{
                    SignalManager.getInstance().toast("Invalid timer input");
                }
            }
            else if(minuteString.isEmpty()&&secondString.isEmpty()){//update nothing
            }
            else{
                SignalManager.getInstance().toast("Invalid timer input");
            }
        });

        clearAllDataButton.setOnClickListener(v -> {
            warningLinearLayout.setVisibility(View.VISIBLE);
        });

        okButton.setOnClickListener(v1 -> {
            if (deleteEditText.getText().toString().equals("delete")) {
                FirebaseManager.getInstance().deleteUserData();

            }
        });

        cancelButton.setOnClickListener(v2 -> {
            warningLinearLayout.setVisibility(View.GONE);
        });

    }

    private void findViews(View view) {
        changeNameEditText = view.findViewById(R.id.change_name_EditText);
        saveChangesButton = view.findViewById(R.id.save_changes_BTN);
        clearAllDataButton = view.findViewById(R.id.Clear_all_data_BTN);
        warningLinearLayout = view.findViewById(R.id.warning_layout);
        deleteEditText = view.findViewById(R.id.deletion_validation_edittext);
        okButton = view.findViewById(R.id.ok_button);
        cancelButton = view.findViewById(R.id.cancel_button);
        restTimeSoundSpinner = view.findViewById(R.id.rest_over_sound_spinner);
        secondsRestTimeEditText = view.findViewById(R.id.user_pref_seconds_edittext);
        minutesRestTimeEditText = view.findViewById(R.id.user_pref_minutes_edittext);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) {
            SoundPlayer soundPlayer = new SoundPlayer(getContext());
            userChosenSound = parent.getItemAtPosition(position).toString();
            switch (userChosenSound) {
                case NO_SOUND:
                    SignalManager.getInstance().toast("R" + R.raw.bell_sound + "Id" + soundId);
                    break;
                case BELL_SOUND:
                    soundPlayer.playSoundOnce(R.raw.bell_sound);
                    soundId = R.raw.bell_sound;
                    SignalManager.getInstance().toast("R" + R.raw.bell_sound + "Id" + soundId);
                    break;
                case CLASSIC_SOUND:
                    soundPlayer.playSoundOnce(R.raw.classic_alarm_sound);
                    soundId = R.raw.classic_alarm_sound;
                    break;
                case DIGITAL_SOUND:
                    soundPlayer.playSoundOnce(R.raw.digital_alarm_sound);
                    soundId = R.raw.digital_alarm_sound;
                    break;
            }
            spinnerPosition = position;
            SharedPreferencesManager.getInstance().putInteger(SPINNER_POSITION_KEY, spinnerPosition);
        }
        else{
            userChosenSound="";
        }

    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}