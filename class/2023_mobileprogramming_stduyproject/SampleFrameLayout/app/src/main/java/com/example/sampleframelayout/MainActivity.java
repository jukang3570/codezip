package com.example.sampleframelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    ImageView imageView2;
    ImageView  imageView3;

    int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
    }

    public void onButtonClicked(View view){
        changeImage();
    }
    private void changeImage() {
        if(imageIndex == 0) {
            imageView.setVisibility(View.VISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            imageView3.setVisibility(View.INVISIBLE);
            imageIndex++;
        }
        else if(imageIndex == 1) {
            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.VISIBLE);
            imageView3.setVisibility(View.INVISIBLE);
            imageIndex++;
        }
        else if(imageIndex == 2) {
            imageView.setVisibility(View.INVISIBLE);
            imageView2.setVisibility(View.INVISIBLE);
            imageView3.setVisibility(View.VISIBLE);
            imageIndex = 0;
        }
    }
}