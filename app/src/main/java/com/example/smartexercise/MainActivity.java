package com.example.smartexercise;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private EditText editTextWeight, editTextHeight;
    private TextView textViewResult;
    Button btnCalBMI, btnUnderweight, btnHealthy, btnOverweight, btnObese;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextWeight = findViewById(R.id.editTextWeight);
        editTextHeight = findViewById(R.id.editTextHeight);
        btnCalBMI = findViewById(R.id.btnCalBMI);
        textViewResult = findViewById(R.id.textViewResult);

        btnUnderweight = findViewById(R.id.btnunderweight);
        btnHealthy = findViewById(R.id.btnhealthy);
        btnOverweight = findViewById(R.id.btnoverweight);
        btnObese = findViewById(R.id.btnobese);

        btnCalBMI.setOnClickListener(v -> calculateBMI());
        Button btnlogout = findViewById(R.id.btnlogout);

        btnUnderweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, underweight.class);
                startActivity(intent);
            }
        });
        btnHealthy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, healthy.class);
                startActivity(intent);
            }
        });
        btnOverweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, overweight.class);
                startActivity(intent);
            }
        });
        btnObese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, obese.class);
                startActivity(intent);
            }
        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, welcome_page.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void calculateBMI() {
        // Get weight and height from EditText fields
        String weightStr = editTextWeight.getText().toString();
        String heightStr = editTextHeight.getText().toString();

        if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
            // Convert weight and height to float values
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr) / 100; // Convert cm to meters

            // Calculate BMI
            float bmi = weight / (height * height);

            // Format BMI to one decimal place
            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            String formattedBMI = decimalFormat.format(bmi);

            // Determine BMI category and display the result
            String bmiCategory;
            if (bmi < 18.5) {
                bmiCategory = "Underweight";
            } else if (bmi >= 18.5 && bmi < 25.0) {
                bmiCategory = "Healthy";
            } else if (bmi >= 25.0 && bmi < 30.0) {
                bmiCategory = "Overweight";
            } else {
                bmiCategory = "Obesity";
            }

            // Display the result
            textViewResult.setText("BMI: " + formattedBMI + " (" + bmiCategory + ")");
        } else {
            textViewResult.setText("Please enter weight and height.");
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

    }
}