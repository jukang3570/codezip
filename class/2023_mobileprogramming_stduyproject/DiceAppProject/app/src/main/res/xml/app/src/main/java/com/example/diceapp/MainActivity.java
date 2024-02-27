package com.example.diceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private RadioGroup choiceDice;
    RadioButton dice_one;
    Button throwDice;
    int whichRadioButton = 1;

    ImageView diceImage;
    ImageView diceImage2;

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        choiceDice =  findViewById(R.id.choiceDice);
        dice_one =  findViewById(R.id.dice_one);
        throwDice = findViewById(R.id.throwDice);
        diceImage = findViewById(R.id.diceImage);
        diceImage2 = findViewById(R.id.diceImage2);

        choiceDice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.dice_one) {
                    whichRadioButton = 1;
                    diceImage2.setVisibility(View.INVISIBLE);
                }
                else if (checkedId == R.id.dice_two) {
                    whichRadioButton = 2;
                    diceImage2.setVisibility(View.VISIBLE);
                }
            }
        });

        throwDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numberOfDice = 1;
                int numberOfDice2 = 1;

                if (whichRadioButton == 1)
                    numberOfDice = random.nextInt(6) + 1;
                else {
                    numberOfDice = random.nextInt(6) + 1;
                    numberOfDice2 = random.nextInt(6) + 1;
                }

                if (whichRadioButton == 1) {
                    switch (numberOfDice) {
                        case 1:
                            diceImage.setImageResource(R.drawable.dice1);
                            break;
                        case 2:
                            diceImage.setImageResource(R.drawable.dice2);
                            break;
                        case 3:
                            diceImage.setImageResource(R.drawable.dice3);
                            break;
                        case 4:
                            diceImage.setImageResource(R.drawable.dice4);
                            break;
                        case 5:
                            diceImage.setImageResource(R.drawable.dice5);
                            break;
                        case 6:
                            diceImage.setImageResource(R.drawable.dice6);
                            break;
                    }
                }

                if (whichRadioButton == 2) {
                    switch (numberOfDice) {
                        case 1:
                            diceImage.setImageResource(R.drawable.dice1);
                            break;
                        case 2:
                            diceImage.setImageResource(R.drawable.dice2);
                            break;
                        case 3:
                            diceImage.setImageResource(R.drawable.dice3);
                            break;
                        case 4:
                            diceImage.setImageResource(R.drawable.dice4);
                            break;
                        case 5:
                            diceImage.setImageResource(R.drawable.dice5);
                            break;
                        case 6:
                            diceImage.setImageResource(R.drawable.dice6);
                            break;
                    }

                    switch (numberOfDice2) {
                        case 1:
                            diceImage2.setImageResource(R.drawable.dice1);
                            break;
                        case 2:
                            diceImage2.setImageResource(R.drawable.dice2);
                            break;
                        case 3:
                            diceImage2.setImageResource(R.drawable.dice3);
                            break;
                        case 4:
                            diceImage2.setImageResource(R.drawable.dice4);
                            break;
                        case 5:
                            diceImage2.setImageResource(R.drawable.dice5);
                            break;
                        case 6:
                            diceImage2.setImageResource(R.drawable.dice6);
                            break;
                    }
                }
            }   //end of onClick
        });
    }
}