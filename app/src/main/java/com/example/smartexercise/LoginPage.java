package com.example.smartexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button signinButton;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.password);
        signinButton = findViewById(R.id.signinbtn);

        dbHandler = new DBHandler(this);

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

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

                if (dbHandler.checkUser(email, password)) {
                    Toast.makeText(LoginPage.this, "Signin successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginPage.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Close signin activity
                } else {
                    Toast.makeText(LoginPage.this, "Invalid credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}