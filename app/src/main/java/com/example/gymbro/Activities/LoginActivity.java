package com.example.gymbro.Activities;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.gymbro.R;
import com.example.gymbro.Utils.SharedPreferencesManager;
import com.example.gymbro.Utils.SignalManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.internal.zzac;

public class LoginActivity extends AppCompatActivity {
    CheckBox rememberMeCB;
    TextView emailTV;
    TextView passwordTV;
    String EMAIL_KEY = "email";
    String PASSWORD_KEY = "password";
    Button loginBtn;
    Button signupBtn;

    private FirebaseAuth mAuth;


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



        mAuth = FirebaseAuth.getInstance();
        findViews();
        //load user email and password
        String emailStr = SharedPreferencesManager.getInstance().getString(EMAIL_KEY, "");
        String passwordStr = SharedPreferencesManager.getInstance().getString(PASSWORD_KEY, "");
        emailTV.setText(emailStr);
        passwordTV.setText(passwordStr);

        loginUser();
        signUpUser();


    }

    private void signUpUser() {
        signupBtn.setOnClickListener(v->{
            Intent i = new Intent(getApplicationContext(),SignupActivity.class);
            startActivity(i);
        });
    }

    private void loginUser() {
        loginBtn.setOnClickListener(v->{
            String email = emailTV.getText().toString();
            String password = passwordTV.getText().toString();

            if(!email.isEmpty()&& !password.isEmpty()){{
                signIn(emailTV.getText().toString(),passwordTV.getText().toString());
            }}
            else{
                SignalManager.getInstance().toast("Please fill all fields");
            }
        });


    }

    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            rememberUserCheckBox();
                            FirebaseUser user = mAuth.getCurrentUser();
                            transectToMainActivity();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Failed to login.\nplease check password and email.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    private void transectToMainActivity() {
                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(i);
                    }
                });
    }


    private void rememberUserCheckBox() {
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        //save user email and password
        if(rememberMeCB.isChecked()) {
            this.loginBtn.setAnimation(fadeOutAnimation);
            String emailStr = emailTV.getText().toString();
            String passwordStr = passwordTV.getText().toString();
            SharedPreferencesManager.getInstance().putString(EMAIL_KEY, emailStr);
            SharedPreferencesManager.getInstance().putString(PASSWORD_KEY, passwordStr);
            SignalManager.getInstance().toast("User email and password saved");
        }

    }





    private void findViews() {
        rememberMeCB = findViewById(R.id.remember_me_checkbox);
        emailTV = findViewById(R.id.editTextTextEmailAddress);
        passwordTV = findViewById(R.id.editTextTextPassword);
        loginBtn=findViewById(R.id.login_button);
        signupBtn=findViewById(R.id.signup_button);

    }
}