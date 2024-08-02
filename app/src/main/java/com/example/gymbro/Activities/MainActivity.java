package com.example.gymbro.Activities;

import static com.google.android.material.internal.ViewUtils.hideKeyboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.gymbro.Fragments.exercisesFragment;
import com.example.gymbro.Fragments.workoutsFragment;
import com.example.gymbro.Models.Exercise;
import com.example.gymbro.Models.Workout;
import com.example.gymbro.R;
import com.example.gymbro.Utils.ImageLoader;
import com.example.gymbro.Utils.SignalManager;
import com.example.gymbro.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.internal.ViewUtils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final int MENU_ITEM_WORKOUT = R.id.workouts_menu_item;
    private static final int MENU_ITEM_EXERCISES = R.id.exercises_menu_item;
    private static final int MENU_ITEM_STATS = R.id.stats_menu_item;
    private BottomNavigationView bottom_bar_menu;
    private FloatingActionButton newWorkoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        ImageLoader.initImageLoader(this);


        setContentView(R.layout.activity_main);
        findViews();
        initViews();
        switchFragment();
        newWorkoutButton.setOnClickListener(v->{
            Intent i = new Intent(getApplicationContext(),WorkoutActivity.class);
            startActivity(i);
        });

    }




    private void initViews() {
        setFragment(new workoutsFragment());
        bottom_bar_menu.getMenu().findItem(MENU_ITEM_WORKOUT).setChecked(true);

    }

    private void findViews() {
        bottom_bar_menu = findViewById(R.id.bottomNavigationView);
        newWorkoutButton = findViewById(R.id.start_workout_fab);
    }

    private void switchFragment() {

        bottom_bar_menu.setOnItemSelectedListener(item -> {
            if (item.getItemId() == MENU_ITEM_WORKOUT) {
                setFragment(new workoutsFragment());
            }
            else if (item.getItemId() == MENU_ITEM_EXERCISES) {
                setFragment(new exercisesFragment());
            }
            else if (item.getItemId() == MENU_ITEM_STATS) {
              //  setFragment(new statsFragment());
            }
            else{
                //do nothing
            }

            return true;
        });

    }

    private void setFragment(Fragment fragment) {
       getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView, fragment).commit();
    }




}