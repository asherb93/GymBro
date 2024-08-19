package com.example.gymbro.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.gymbro.Data.UserStats;
import com.example.gymbro.R;
import com.example.gymbro.Utils.DataManager;
import com.example.gymbro.Utils.FirebaseManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button signUpButton;
    private TextView emailTextView;
    private TextView passwordTextView;
    private TextView passwordVerifyTextView;
    private TextView nameTV;
    private ImageView goBackToLoginIV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.activity_signup), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViews();





        goBackToLoginIV.setOnClickListener(v->{
           Intent I = new Intent(this, LoginActivity.class);
            startActivity(I);
        });

        signUpButton.setOnClickListener(v->{
            SignUp();
        });
    }

    private void SignUp() {
        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String passwordVerify = passwordVerifyTextView.getText().toString();
        String name = nameTV.getText().toString();
        if(!email.isEmpty()&&!password.isEmpty()&&!passwordVerify.isEmpty()&&!name.isEmpty()){
            if(password.equals(passwordVerify)){
                createAccount(email,password);
            }else{
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void findViews() {
         emailTextView = findViewById(R.id.email_ET);
         passwordTextView = findViewById(R.id.password_ET);
         passwordVerifyTextView = findViewById(R.id.secondPassword_ET);
         nameTV = findViewById(R.id.name_ET);
         signUpButton = findViewById(R.id.signUpButton);
        goBackToLoginIV = findViewById(R.id.back_button_IV);

    }


    private void createAccount(String email, String password) {

        mAuth = FirebaseAuth.getInstance();
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //upload initial userStats
                            UserStats userStats = new UserStats();
                            userStats.setName(nameTV.getText().toString());
                            FirebaseManager.getInstance().uploadUserStats(userStats);
                            //template workouts for new user
                            FirebaseManager.getInstance().uploadWorkouts(DataManager.getTemplateWorkouts());
                            //upload default user prefrences
                            Intent I = new Intent(SignupActivity.this, MainActivity.class);
                            startActivity(I);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }

}