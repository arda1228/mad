package com.example.authapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//  declaring variables
    private TextView register;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//      Handle registration by sending to RegisterUser.
//      OnClickListener for register button

        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);
//      Handle signin. OnClick listener initiated to handle request
        signIn = (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(this);
//      Capture email and password to initiate signin process
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
//      Initiate progressBar for user communication
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
//      getInstance from Firebase for auth
        mAuth = FirebaseAuth.getInstance();
    }

    // responding to click event
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
//                Send user to RegisterUser activity for new registration request
                startActivity(new Intent(this, RegisterUser.class));
                break;

            case R.id.signIn:
//                Handle userLogin as per the userLogin() function
                userLogin();
                break;
        }
    }

    private void userLogin() { // checks for valid inouts, then checks if user is in database before allowing access
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) { // ensures email is provided
            editTextEmail.setError("email required");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) { // ensures that email is in database
            editTextEmail.setError("valid email required");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) { // ensures password is provided
            editTextPassword.setError("password required");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) { // ensures password is at least 6 characters long
            editTextPassword.setError("password of at least 6 characters required");
            editTextPassword.requestFocus();
            return;
        }
//      Set progressBar to visible while user is waiting for Firebase auth process
        progressBar.setVisibility(View.VISIBLE);

        // checking if user is valid
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if (user.isEmailVerified()) {
                    // redirect to main user screen and provide main menu
                    startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        Toast.makeText(MainActivity.this, "login successful", Toast.LENGTH_LONG).show();
                    } else {
                        // user exists but email not verified. remind user to check email and verify account
                        user.sendEmailVerification();
                        Toast.makeText(MainActivity.this, "check your email, verify account", Toast.LENGTH_LONG).show();
                    }
                } else { // user/password combination not found
                    Toast.makeText(MainActivity.this, "failed to log in, check inputs", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}