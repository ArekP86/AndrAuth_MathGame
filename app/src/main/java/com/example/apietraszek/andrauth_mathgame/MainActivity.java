package com.example.apietraszek.andrauth_mathgame;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Integer value1;
    private Integer value2;
    private Integer score;
    private TextView Number1;
    private TextView Number2;
    private EditText Attempt;
    private TextView Answer;
    private TextView TimerText;
    private TextView PointsText;
    private boolean gameOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Number1 = findViewById(R.id.number1);
        Number2 = findViewById(R.id.number2);
        Attempt = findViewById(R.id.attemptText);
        Answer = findViewById(R.id.answer);
        TimerText = findViewById(R.id.timerText);
        PointsText = findViewById(R.id.pointsText);

        score = 0;

        setNewNumbers();

        gameOn = true;

        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                TimerText.setText("time remaining: " + millisUntilFinished / 1000);
            }
            public void onFinish() {
                TimerText.setText("done!");
                gameOn = false;
                Answer.setText("Your score is: "+score);
                Attempt.setVisibility(View.GONE);
            }
        }.start();
    }

    public void onSubmitClick (View view){
        if(!gameOn) return;

        if(Attempt.getText().toString().matches("")){
            //Context context = getApplicationContext();
            Toast.makeText(this, "Answer is missing!", Toast.LENGTH_SHORT).show();
            Answer.setText("");
            return;
        }
        int userAnswer = Integer.parseInt(Attempt.getText().toString());
        if(value1+value2 == userAnswer) {
            Answer.setText("Correct!");
            score++;
            PointsText.setText("Points: "+score);
        } else {
            Answer.setText("Wrong, the correct answer was: " + (value1+value2));
        }

        setNewNumbers();
    }

    private void setNewNumbers() {
        Random r = new Random();
        value1 = r.nextInt(99);
        value2 = r.nextInt(100-value1);
        Number1.setText(""+value1);
        Number2.setText(""+value2);
        Attempt.setText("");
    }
}
6