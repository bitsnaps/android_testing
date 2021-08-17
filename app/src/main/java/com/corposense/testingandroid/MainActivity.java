package com.corposense.testingandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnCalc;
    private TextView tvResult;
    private EditText edWeight, edHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCalc = findViewById(R.id.btnCalc);
        tvResult = findViewById(R.id.tvResult);
        edWeight = findViewById(R.id.edWeight);
        edHeight = findViewById(R.id.edHeight);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String weight = edWeight.getEditableText().toString().trim();
                String height = edHeight.getEditableText().toString().trim();

                if (weight.isEmpty() || height.isEmpty()){
                    tvResult.setText( "Please enter correct values..." );
                    return;
                }

                Toast.makeText(getApplicationContext(), "Calculating BMI...", Toast.LENGTH_SHORT).show();
                BodyMassIndex bmi = new BodyMassIndex(Double.valueOf(weight), Double.valueOf(height) );
                tvResult.setText( "Your BMI = " + bmi.calculate().toString()+ " kg/m2" );
            }
        });

    }


    public static class BodyMassIndex {

        private double weight;
        private double height;

        public BodyMassIndex(double weight, double height) {
            this.weight = weight;
            this.height = height;
        }

        public Double calculate(){
            return this.weight / (this.height * this.height);
        }
    }
}
