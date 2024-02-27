package com.example.samplethread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int value = 0;
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundThread bt = new BackgroundThread();
                bt.start();
            }
        });

    }

    class BackgroundThread extends Thread {
        public void run() {
            for (int i =0; i<100; i++) {
                try {
                    Thread.sleep(1000);
                } catch ( InterruptedException e) {
                    throw new RuntimeException(e);
                }
                value += 1;
                Log.d("Thread", "value: " + value);
                textView.setText("value 값 : " + value);
            }
        }
    }
}