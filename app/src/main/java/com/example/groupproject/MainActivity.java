package com.example.groupproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText loginUsername, loginPassword;
    private Button buttonLogin;
    private RadioGroup loginRbGroup;
    private RadioButton rbAdmin, rbTeacher, rbStudent;
    private String selectedRb = null;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.loginPassword);

        rbAdmin = findViewById(R.id.rbAdmin);
        rbTeacher = findViewById(R.id.rbTeacher);
        rbStudent = findViewById(R.id.rbStudent);

        buttonLogin = findViewById(R.id.buttonLogin);
        loginRbGroup = findViewById(R.id.loginRbGroup);

        db = new DatabaseHelper(this);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                Boolean checkUserPass = false;
                String user = loginUsername.getText().toString();
                String pass = loginPassword.getText().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (user.equals("admin") && pass.equals("admin")) {
                        intent = new Intent(getApplicationContext(), Welcome_Admin.class);
                        startActivity(intent);
                    } else {
                        checkUserPass = db.checkUsernamePassword(user, pass);
                    }

                    if (checkUserPass == true) {
                        Toast.makeText(MainActivity.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                        switch (selectedRb) {
                            case "admin":
                                intent = new Intent(getApplicationContext(), Welcome_Admin.class);
                                startActivity(intent);
                                break;
                            case "teacher":
                                intent = new Intent(getApplicationContext(), MainActivity2.class);
                                startActivity(intent);
                                break;
                            case "student":
                                intent = new Intent(getApplicationContext(), WelcomePage.class);
                                startActivity(intent);
                                break;
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Username or Password Incorrect. Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }

                if (selectedRb != null) {

                } else {
                    Toast.makeText(MainActivity.this, "Please select a user type above", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void onRadioButtonClicked(View v) {
        // Is the button now checked?
        boolean checked = ((RadioButton) v).isChecked();

        // Check which radio button was clicked
        switch (v.getId()) {
            case R.id.rbAdmin:
                if (checked) selectedRb = "admin";
                break;
            case R.id.rbTeacher:
                if (checked) selectedRb = "teacher";
                break;
            case R.id.rbStudent:
                if (checked) selectedRb = "student";
                break;
        }
    }}