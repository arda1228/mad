package com.example.holidaze;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.holidaze.DBHelper;

public class RegistrationActivity extends AppCompatActivity {
    EditText username,password,repassword;
    Button signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        signup = findViewById(R.id.register);
        signin = findViewById(R.id.signin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                    Toast.makeText(RegistrationActivity.this, "all fields must be filled", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(RegistrationActivity.this, "registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegistrationActivity.this, "registration failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(RegistrationActivity.this, "user already exists", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegistrationActivity.this, "passwords don't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                startActivity(intent);
//            }
//        });


    }
}