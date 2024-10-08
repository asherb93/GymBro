package com.example.gymbro.Activities;

import static com.google.android.material.internal.ViewUtils.hideKeyboard;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.gymbro.Fragments.exercisesFragment;
import com.example.gymbro.Fragments.statisticFragment;
import com.example.gymbro.Fragments.userSettingsFragment;
import com.example.gymbro.Fragments.workoutsFragment;
import com.example.gymbro.R;
import com.example.gymbro.Utils.ImageLoader;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private static final int MENU_ITEM_WORKOUT = R.id.workouts_menu_item;
    private static final int MENU_ITEM_EXERCISES = R.id.exercises_menu_item;
    private static final int MENU_ITEM_STATS = R.id.stats_menu_item;
    private static final int MENU_ITEM_SETTINGS = R.id.settings_menu_item;
    private BottomNavigationView bottomBarMenu;
    private FloatingActionButton newWorkoutFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        ImageLoader.initImageLoader(this);

        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());;
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, 10);
            return insets;
        });

        findViews();
        initViews();
        switchFragment();
        newWorkoutFab.setOnClickListener(v->{
            Intent i = new Intent(getApplicationContext(),WorkoutActivity.class);
            startActivity(i);
        });

    }


    private void initViews() {
        setFragment(new workoutsFragment());
        bottomBarMenu.getMenu().findItem(MENU_ITEM_WORKOUT).setChecked(true);

    }

    private void findViews() {
        bottomBarMenu = findViewById(R.id.bottom_nav_bar);
        newWorkoutFab = findViewById(R.id.start_workout_fab);
    }

    private void switchFragment() {

        bottomBarMenu.setOnItemSelectedListener(item -> {
            if (item.getItemId() == MENU_ITEM_WORKOUT) {
                setFragment(new workoutsFragment());

            }
            else if (item.getItemId() == MENU_ITEM_EXERCISES) {
                setFragment(new exercisesFragment());
            }
            else if (item.getItemId() == MENU_ITEM_STATS) {
                setFragment(new statisticFragment());
            }
            else if (item.getItemId() == MENU_ITEM_SETTINGS) {
                setFragment(new userSettingsFragment());
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