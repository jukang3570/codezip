package com.example.samplethreadanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button button;
    ArrayList<Drawable> drawbleList;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);
        drawbleList = new ArrayList<Drawable>();
        handler = new Handler();

        Resources res = getResources();
        drawbleList.add(res.getDrawable(R.drawable.face1));
        drawbleList.add(res.getDrawable(R.drawable.face2));
        drawbleList.add(res.getDrawable(R.drawable.face3));
        drawbleList.add(res.getDrawable(R.drawable.face4));
        drawbleList.add(res.getDrawable(R.drawable.face5));
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AnimThread thread = new AnimThread();
                thread.start();
            }
        });
    }

    class AnimThread extends Thread {
        public void run() {
            int idx = 0;
            for(int i=0;i<100;i++) {
                Drawable drawable = drawbleList.get(idx);
                idx += 1;
                if(idx >4) idx =0;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageDrawable(drawable);
                    }
                });
                try {
                    Thread.sleep( 1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}