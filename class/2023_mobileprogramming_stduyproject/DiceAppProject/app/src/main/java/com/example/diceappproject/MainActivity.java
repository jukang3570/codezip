package com.example.diceappproject;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
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
    ImageView diceImage3;
    ImageView diceImage4;
    ImageView diceImage5;
    ImageView diceImage6;

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        choiceDice = findViewById(R.id.choiceDice);
        dice_one = findViewById(R.id.dice_one);
        throwDice = findViewById(R.id.throwDice);
        diceImage = findViewById(R.id.diceImage);
        diceImage2 = findViewById(R.id.diceImage2);
        diceImage3 = findViewById(R.id.diceImage3);
        diceImage4 = findViewById(R.id.diceImage4);
        diceImage5 = findViewById(R.id.diceImage5);
        diceImage6 = findViewById(R.id.diceImage6);


        choiceDice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.dice_one) {
                    whichRadioButton = 1;
                    diceImage2.setVisibility(View.INVISIBLE);
                    diceImage3.setVisibility(View.INVISIBLE);
                    diceImage4.setVisibility(View.INVISIBLE);
                    diceImage5.setVisibility(View.INVISIBLE);
                    diceImage6.setVisibility(View.INVISIBLE);
                } else if (checkedId == R.id.dice_two) {
                    whichRadioButton = 2;
                    diceImage2.setVisibility(View.VISIBLE);
                    diceImage3.setVisibility(View.INVISIBLE);
                    diceImage4.setVisibility(View.INVISIBLE);
                    diceImage5.setVisibility(View.INVISIBLE);
                    diceImage6.setVisibility(View.INVISIBLE);
                } else if (checkedId == R.id.dice_three) {
                    whichRadioButton = 3;
                    diceImage2.setVisibility(View.VISIBLE);
                    diceImage3.setVisibility(View.VISIBLE);
                    diceImage4.setVisibility(View.INVISIBLE);
                    diceImage5.setVisibility(View.INVISIBLE);
                    diceImage6.setVisibility(View.INVISIBLE);
                } else if (checkedId == R.id.dice_four) {
                    whichRadioButton = 4;
                    diceImage2.setVisibility(View.VISIBLE);
                    diceImage3.setVisibility(View.VISIBLE);
                    diceImage4.setVisibility(View.VISIBLE);
                    diceImage5.setVisibility(View.INVISIBLE);
                    diceImage6.setVisibility(View.INVISIBLE);
                } else if (checkedId == R.id.dice_five) {
                    whichRadioButton = 5;
                    diceImage2.setVisibility(View.VISIBLE);
                    diceImage3.setVisibility(View.VISIBLE);
                    diceImage4.setVisibility(View.VISIBLE);
                    diceImage5.setVisibility(View.VISIBLE);
                    diceImage6.setVisibility(View.INVISIBLE);
                } else if (checkedId == R.id.dice_six) {
                    whichRadioButton = 6;
                    diceImage2.setVisibility(View.VISIBLE);
                    diceImage3.setVisibility(View.VISIBLE);
                    diceImage4.setVisibility(View.VISIBLE);
                    diceImage5.setVisibility(View.VISIBLE);
                    diceImage6.setVisibility(View.VISIBLE);
                }
            }
        });

        throwDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numberOfDice = 1;
                int numberOfDice2 = 1;
                int numberOfDice3 = 1;
                int numberOfDice4 = 1;
                int numberOfDice5 = 1;
                int numberOfDice6 = 1;

                if (whichRadioButton == 1)
                    numberOfDice = random.nextInt(6) + 1;
                else if (whichRadioButton == 2) {
                    numberOfDice = random.nextInt(6) + 1;
                    numberOfDice2 = random.nextInt(6) + 1;
                } else if (whichRadioButton == 3) {
                    numberOfDice = random.nextInt(6) + 1;
                    numberOfDice2 = random.nextInt(6) + 1;
                    numberOfDice3 = random.nextInt(6) + 1;
                } else if (whichRadioButton == 4) {
                    numberOfDice = random.nextInt(6) + 1;
                    numberOfDice2 = random.nextInt(6) + 1;
                    numberOfDice3 = random.nextInt(6) + 1;
                    numberOfDice4 = random.nextInt(6) + 1;
                } else if (whichRadioButton == 5) {
                    numberOfDice = random.nextInt(6) + 1;
                    numberOfDice2 = random.nextInt(6) + 1;
                    numberOfDice3 = random.nextInt(6) + 1;
                    numberOfDice4 = random.nextInt(6) + 1;
                    numberOfDice5 = random.nextInt(6) + 1;
                } else if (whichRadioButton == 6) {
                    numberOfDice = random.nextInt(6) + 1;
                    numberOfDice2 = random.nextInt(6) + 1;
                    numberOfDice3 = random.nextInt(6) + 1;
                    numberOfDice4 = random.nextInt(6) + 1;
                    numberOfDice5 = random.nextInt(6) + 1;
                    numberOfDice6 = random.nextInt(6) + 1;
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
                if (whichRadioButton == 3) {
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
                    switch (numberOfDice3) {
                        case 1:
                            diceImage3.setImageResource(R.drawable.dice1);
                            break;
                        case 2:
                            diceImage3.setImageResource(R.drawable.dice2);
                            break;
                        case 3:
                            diceImage3.setImageResource(R.drawable.dice3);
                            break;
                        case 4:
                            diceImage3.setImageResource(R.drawable.dice4);
                            break;
                        case 5:
                            diceImage3.setImageResource(R.drawable.dice5);
                            break;
                        case 6:
                            diceImage3.setImageResource(R.drawable.dice6);
                            break;
                    }
                }
                if (whichRadioButton == 4) {
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
                    switch (numberOfDice3) {
                        case 1:
                            diceImage3.setImageResource(R.drawable.dice1);
                            break;
                        case 2:
                            diceImage3.setImageResource(R.drawable.dice2);
                            break;
                        case 3:
                            diceImage3.setImageResource(R.drawable.dice3);
                            break;
                        case 4:
                            diceImage3.setImageResource(R.drawable.dice4);
                            break;
                        case 5:
                            diceImage3.setImageResource(R.drawable.dice5);
                            break;
                        case 6:
                            diceImage3.setImageResource(R.drawable.dice6);
                            break;
                    }
                    switch (numberOfDice4) {
                        case 1:
                            diceImage4.setImageResource(R.drawable.dice1);
                            break;
                        case 2:
                            diceImage4.setImageResource(R.drawable.dice2);
                            break;
                        case 3:
                            diceImage4.setImageResource(R.drawable.dice3);
                            break;
                        case 4:
                            diceImage4.setImageResource(R.drawable.dice4);
                            break;
                        case 5:
                            diceImage4.setImageResource(R.drawable.dice5);
                            break;
                        case 6:
                            diceImage4.setImageResource(R.drawable.dice6);
                            break;
                    }
                }
                if (whichRadioButton == 5) {
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
                    switch (numberOfDice3) {
                        case 1:
                            diceImage3.setImageResource(R.drawable.dice1);
                            break;
                        case 2:
                            diceImage3.setImageResource(R.drawable.dice2);
                            break;
                        case 3:
                            diceImage3.setImageResource(R.drawable.dice3);
                            break;
                        case 4:
                            diceImage3.setImageResource(R.drawable.dice4);
                            break;
                        case 5:
                            diceImage3.setImageResource(R.drawable.dice5);
                            break;
                        case 6:
                            diceImage3.setImageResource(R.drawable.dice6);
                            break;
                    }
                    switch (numberOfDice4) {
                        case 1:
                            diceImage4.setImageResource(R.drawable.dice1);
                            break;
                        case 2:
                            diceImage4.setImageResource(R.drawable.dice2);
                            break;
                        case 3:
                            diceImage4.setImageResource(R.drawable.dice3);
                            break;
                        case 4:
                            diceImage4.setImageResource(R.drawable.dice4);
                            break;
                        case 5:
                            diceImage4.setImageResource(R.drawable.dice5);
                            break;
                        case 6:
                            diceImage4.setImageResource(R.drawable.dice6);
                            break;
                    }
                    switch (numberOfDice5) {
                        case 1:
                            diceImage5.setImageResource(R.drawable.dice1);
                            break;
                        case 2:
                            diceImage5.setImageResource(R.drawable.dice2);
                            break;
                        case 3:
                            diceImage5.setImageResource(R.drawable.dice3);
                            break;
                        case 4:
                            diceImage5.setImageResource(R.drawable.dice4);
                            break;
                        case 5:
                            diceImage5.setImageResource(R.drawable.dice5);
                            break;
                        case 6:
                            diceImage5.setImageResource(R.drawable.dice6);
                            break;
                    }

                }
                if (whichRadioButton == 6) {
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
                    switch (numberOfDice3) {
                        case 1:
                            diceImage3.setImageResource(R.drawable.dice1);
                            break;
                        case 2:
                            diceImage3.setImageResource(R.drawable.dice2);
                            break;
                        case 3:
                            diceImage3.setImageResource(R.drawable.dice3);
                            break;
                        case 4:
                            diceImage3.setImageResource(R.drawable.dice4);
                            break;
                        case 5:
                            diceImage3.setImageResource(R.drawable.dice5);
                            break;
                        case 6:
                            diceImage3.setImageResource(R.drawable.dice6);
                            break;
                    }
                    switch (numberOfDice4) {
                        case 1:
                            diceImage4.setImageResource(R.drawable.dice1);
                            break;
                        case 2:
                            diceImage4.setImageResource(R.drawable.dice2);
                            break;
                        case 3:
                            diceImage4.setImageResource(R.drawable.dice3);
                            break;
                        case 4:
                            diceImage4.setImageResource(R.drawable.dice4);
                            break;
                        case 5:
                            diceImage4.setImageResource(R.drawable.dice5);
                            break;
                        case 6:
                            diceImage4.setImageResource(R.drawable.dice6);
                            break;
                    }
                    switch (numberOfDice5) {
                        case 1:
                            diceImage5.setImageResource(R.drawable.dice1);
                            break;
                        case 2:
                            diceImage5.setImageResource(R.drawable.dice2);
                            break;
                        case 3:
                            diceImage5.setImageResource(R.drawable.dice3);
                            break;
                        case 4:
                            diceImage5.setImageResource(R.drawable.dice4);
                            break;
                        case 5:
                            diceImage5.setImageResource(R.drawable.dice5);
                            break;
                        case 6:
                            diceImage5.setImageResource(R.drawable.dice6);
                            break;
                    }
                    switch (numberOfDice6) {
                        case 1:
                            diceImage6.setImageResource(R.drawable.dice1);
                            break;
                        case 2:
                            diceImage6.setImageResource(R.drawable.dice2);
                            break;
                        case 3:
                            diceImage6.setImageResource(R.drawable.dice3);
                            break;
                        case 4:
                            diceImage6.setImageResource(R.drawable.dice4);
                            break;
                        case 5:
                            diceImage6.setImageResource(R.drawable.dice5);
                            break;
                        case 6:
                            diceImage6.setImageResource(R.drawable.dice6);
                            break;
                    }

                }
            }   //end of onClick
        });
    }
}