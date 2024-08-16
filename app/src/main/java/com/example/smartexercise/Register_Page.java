package com.example.smartexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register_Page extends AppCompatActivity {

    private EditText fullNameEditText, ageEditText, emailEditText, passwordEditText, confirmPasswordEditText;
    private Button signupButton;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        fullNameEditText = findViewById(R.id.fullname);
        ageEditText = findViewById(R.id.age);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirm_password);
        signupButton = findViewById(R.id.Signupbtn);

        dbHandler = new DBHandler(this);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = fullNameEditText.getText().toString();
                int age = Integer.parseInt(ageEditText.getText().toString());
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                // Validate full name
                if (fullName.isEmpty()) {
                    fullNameEditText.setError("Full name is required");
                    return;
                }

                // Validate email
                if (email.isEmpty()) {
                    emailEditText.setError("Email is required");
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    emailEditText.setError("Invalid email format");
                    return;
                }

                // Validate password
                if (password.isEmpty()) {
                    passwordEditText.setError("Password is required");
                    return;
                }

                if (password.length() < 6) {
                    passwordEditText.setError("Password must be at least 6 characters long");
                    return;
                }

                // Validate confirm password
                if (!confirmPassword.equals(password)) {
                    confirmPasswordEditText.setError("Passwords do not match");
                    return;
                }

                if (dbHandler.addUser(fullName, age, email, password, confirmPassword)) {
                    Toast.makeText(Register_Page.this, "Signup successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register_Page.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Close signup activity
                } else {
                    Toast.makeText(Register_Page.this, "Signup failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}