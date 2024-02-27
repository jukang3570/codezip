package com.example.samplescrollview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    int check = 0;
    ScrollView scrollView;
    ImageView imageView;
    BitmapDrawable bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.ScrollView);
        imageView = findViewById(R.id.imageView);
        scrollView.setHorizontalScrollBarEnabled(true);

        Resources res = getResources();
        bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image01);
        int bitmapWidth = bitmap.getIntrinsicWidth();
        int bitmapHeight = bitmap.getIntrinsicHeight();

        imageView.setImageDrawable(bitmap);
        imageView.getLayoutParams().width = bitmapWidth;
        imageView.getLayoutParams().height = bitmapHeight;
    }

    public void onButtonClicked(View v) {
        changeImage();
    }
    private void changeImage() {
        if(check == 0) {
            Resources res = getResources();
            bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image02);
            int bitmapWidth = bitmap.getIntrinsicWidth();
            int bitmapHeight = bitmap.getIntrinsicHeight();

            imageView.setImageDrawable(bitmap);
            imageView.getLayoutParams().width = bitmapWidth;
            imageView.getLayoutParams().height = bitmapHeight;
            check++;
        }
        else if(check == 1) {
            Resources res = getResources();
            bitmap = (BitmapDrawable) res.getDrawable(R.drawable.image01);
            int bitmapWidth = bitmap.getIntrinsicWidth();
            int bitmapHeight = bitmap.getIntrinsicHeight();

            imageView.setImageDrawable(bitmap);
            imageView.getLayoutParams().width = bitmapWidth;
            imageView.getLayoutParams().height = bitmapHeight;
            check--;
        }
    }
}