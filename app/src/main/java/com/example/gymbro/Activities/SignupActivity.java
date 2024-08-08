package com.example.gymbro.Activities;

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

import com.example.gymbro.Models.AppUser;
import com.example.gymbro.R;
import com.example.gymbro.Utils.DataManager;
import com.example.gymbro.Utils.SignalManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private Button signUpBtn;
    private TextView emailTV;
    private TextView passwordTV;
    private TextView passwordVerifyTV;
    private TextView nameTV;
    private TextView weightTV;
    private ImageView goBackToLoginIV;
    private FirebaseDatabase mDatabase;
    private DatabaseReference ref;


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





        //goBackToLoginIV.setOnClickListener(v->{
      //      Intent I = new Intent(this, LoginActivity.class);
      //      startActivity(I);
      //  });

        signUpBtn.setOnClickListener(v->{
            SignUp();
        });
    }

    private void SignUp() {
        String email = emailTV.getText().toString();
        String password = passwordTV.getText().toString();
        String passwordVerify = passwordVerifyTV.getText().toString();
        String name = nameTV.getText().toString();
        String weight = weightTV.getText().toString();
        if(!email.isEmpty()&&!password.isEmpty()&&!passwordVerify.isEmpty()&&!name.isEmpty()&&!weight.isEmpty()){
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
         emailTV  = findViewById(R.id.emailEditText);
         passwordTV = findViewById(R.id.passwordTextView);
         passwordVerifyTV = findViewById(R.id.secondPasswordTextView);
         nameTV = findViewById(R.id.nameEditText);
         weightTV = findViewById(R.id.weightEditText);
         signUpBtn = findViewById(R.id.signUpButton);
        goBackToLoginIV = findViewById(R.id.back_button_ImageView);

    }


    private void createAccount(String email, String password) {

        mAuth = FirebaseAuth.getInstance();
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignupActivity.this, "Authentication success.",
                                    Toast.LENGTH_SHORT).show();
                            DataManager.uploadUser(nameTV.getText().toString(),Integer.parseInt(weightTV.getText().toString()));

                          //  updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                          //  updateUI(null);
                        }
                    }

                });
    }

}