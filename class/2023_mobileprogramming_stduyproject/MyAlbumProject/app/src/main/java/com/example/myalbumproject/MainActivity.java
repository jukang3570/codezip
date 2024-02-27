package com.example.myalbumproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button b1, b2, b3, b4, b5, b6;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);

        image = findViewById(R.id.imageView);

        b2.setAlpha(0.3f);
        b3.setAlpha(0.3f);
        b4.setAlpha(0.3f);
        b5.setAlpha(0.3f);
        b6.setAlpha(0.3f);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.photo1);
                b1.setAlpha(1.0f);
                b2.setAlpha(0.3f);
                b3.setAlpha(0.3f);
                b4.setAlpha(0.3f);
                b5.setAlpha(0.3f);
                b6.setAlpha(0.3f);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.photo2);
                b1.setAlpha(0.3f);
                b2.setAlpha(1.0f);
                b3.setAlpha(0.3f);
                b4.setAlpha(0.3f);
                b5.setAlpha(0.3f);
                b6.setAlpha(0.3f);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.photo3);
                b1.setAlpha(0.3f);
                b2.setAlpha(0.3f);
                b3.setAlpha(1.0f);
                b4.setAlpha(0.3f);
                b5.setAlpha(0.3f);
                b6.setAlpha(0.3f);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.photo4);
                b1.setAlpha(0.3f);
                b2.setAlpha(0.3f);
                b3.setAlpha(0.3f);
                b4.setAlpha(1.0f);
                b5.setAlpha(0.3f);
                b6.setAlpha(0.3f);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.photo5);
                b1.setAlpha(0.3f);
                b2.setAlpha(0.3f);
                b3.setAlpha(0.3f);
                b4.setAlpha(0.3f);
                b5.setAlpha(1.0f);
                b6.setAlpha(0.3f);
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image.setImageResource(R.drawable.photo6);
                b1.setAlpha(0.3f);
                b2.setAlpha(0.3f);
                b3.setAlpha(0.3f);
                b4.setAlpha(0.3f);
                b5.setAlpha(0.3f);
                b6.setAlpha(1.0f);
            }
        });
    }
}