package com.example.gymbro.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.gymbro.R;
import com.example.gymbro.Utils.SharedPreferencesManager;
import com.example.gymbro.Utils.SignalManager;

public class LoginActivity extends AppCompatActivity {
    CheckBox rememberMeCB;
    TextView emailTV;
    TextView passwordTV;
    String EMAIL_KEY = "email";
    String PASSWORD_KEY = "password";
    Button loginBtn;
    Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);


        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViews();
        rememberUserCheckBox();
        loginUser();
    }

    private void loginUser() {

        loginBtn.setOnClickListener(v->{

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();


        });



    }

    private void rememberUserCheckBox() {
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        //save user email and password
        rememberMeCB.setOnClickListener(v->{
            this.loginBtn.setAnimation(fadeOutAnimation);

            String emailStr = emailTV.getText().toString();
            String passwordStr = passwordTV.getText().toString();
            SharedPreferencesManager.getInstance().putString(EMAIL_KEY, emailStr);
            SharedPreferencesManager.getInstance().putString(PASSWORD_KEY, passwordStr);
            SignalManager.getInstance().toast("User email and password saved");
        });

        //load user email and password
        String emailStr = SharedPreferencesManager.getInstance().getString(EMAIL_KEY, "");
        String passwordStr = SharedPreferencesManager.getInstance().getString(PASSWORD_KEY, "");
        emailTV.setText(emailStr);
        passwordTV.setText(passwordStr);

    }





    private void findViews() {
        rememberMeCB = findViewById(R.id.remember_me_checkbox);
        emailTV = findViewById(R.id.editTextTextEmailAddress);
        passwordTV = findViewById(R.id.editTextTextPassword);
        loginBtn=findViewById(R.id.login_button);
        signupBtn=findViewById(R.id.signup_button);

    }
}