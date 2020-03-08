package com.example.arithmaticapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    double answer;
    double doubleanswer;
    int correct;
    int incorrect;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Spinner operatorspinner = findViewById(R.id.spinnerid);
        String[] operatorList =  new String[]{ "Addition", "Subtraction", "Multiplication", "Division"};
        ArrayAdapter<String> adapterOperators = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, operatorList);
        operatorspinner.setAdapter(adapterOperators);

        Spinner digitSpinner = findViewById(R.id.spinnerid2);
        String[] digitlist = new String[]{ "Single Digits", "Double Digits", "Triple Digits"};
        ArrayAdapter<String> adapterDidgets = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, digitlist);
        digitSpinner.setAdapter(adapterDidgets);
        player = MediaPlayer.create(this, R.raw.fuck);

    }

    public void genQuestion(View v)
    {
        int n1;
        int n2;
        int digit = 0;

        Spinner digitspinner = findViewById(R.id.spinnerid2);
        String selectedDigit = digitspinner.getSelectedItem().toString();
        switch(selectedDigit){
            case "Single Digits":
                digit = 9;
                break;

            case "Double Digits":
                digit = 99;
                break;

            case "Triple Digits":
                digit = 999;
                break;
        }


        Spinner operatorspinner = findViewById(R.id.spinnerid);
       String SelectedOperator = operatorspinner.getSelectedItem().toString();
        n1 = 1 + (int)(Math.random()*digit);
        n2 = 1 + (int) (Math.random()*digit);

        TextView ques = (TextView)(findViewById(R.id.questiontxt));
        ques.setText((n1 + "+" + n2));
        answer = n1 + n2;
        ((EditText)(findViewById(R.id.answertxt))).setText("");

        switch(SelectedOperator){
            case "Addition":
                ques.setText(n1 + "+" + n2);
                answer = n1 + n2;
                System.out.println("I AM THE ANSWER, COME LOOK AT ME " + answer);
                break;
            case "Subtraction":
                ques.setText(n1 + "-" + n2);
                answer = n1 - n2;
                System.out.println("I AM THE ANSWER, COME LOOK AT ME " + answer);
                break;
            case "Multiplication":
                ques.setText(n1 + "x" + n2);
                answer = n1 * n2;
                System.out.println("I AM THE ANSWER, COME LOOK AT ME " + answer);
                break;
            case "Division":
                ques.setText(n1 + "÷" + n2);
                double d1 = n1;
                double d2 = n2;
                doubleanswer = d1 / d2;
                answer = Math.round(doubleanswer * 100d) /100d;
                System.out.println("I AM THE ANSWER, COME LOOK AT ME " + answer);

                break;
        }

    }

    public void checkAnswer(View v)
                {
                    EditText s = (EditText)(findViewById(R.id.answertxt));
                    if (s.getText().toString().isEmpty()){ return;}
                    if (s.getText().toString().contains(" ")){ return;}
                    if(answer == Double.parseDouble(s.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_LONG).show();
                        correct++;
                        ((TextView) (findViewById(R.id.corrects))).setText("Corrects " + correct);
                        ((TextView) (findViewById(R.id.Points))).setText("Points " + (3 * correct));
                        s.setText(" ");
                        genQuestion(v);



        }
        else
            {
            Toast.makeText(getApplicationContext(), "Incorrect ", Toast.LENGTH_LONG).show();
            incorrect++;
            ((TextView)(findViewById(R.id.corrects))).setText("Incorrect " + incorrect);
            s.setText(" ");
            genQuestion(v);
                player.start();
        }
    }
}
