package com.example.sampleevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    View view;
    View view2;
    TextView textView;
    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.view);
        view2 = findViewById(R.id.view2);
        textView = findViewById(R.id.textView);
        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                float curX = motionEvent.getX();
                float curY = motionEvent.getY();

                if (action == MotionEvent.ACTION_DOWN) {
                    println("손가락 눌림 : " + curX + "," + curY);
                } else if (action == MotionEvent.Action_Move) {
                    println("손가락 움직임: " + curX + "," + curY);
                } else if (action == MotionEvent.ACTION_UP) {
                    println("손가락 땜 : " + curX + "," + curY);
                }
            }
        });

        view2.setOnTouchListener(new View.onTouchListener() {
            public bollean on_Touch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
                }
        });
        gestureDetector = new GestureDetector(this, new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(@NonNull MotionEvent e){
                println("on Down 호출됨."); return true;
            }
            @Override
            public boolean onShowPress(@NonNull MotionEvent e){
                println("onShowpress 호출됨."); return true;
            }
            @Override
            public boolean onSingleTapUp(@NonNull MotionEvent e){
                println("onSingleTapUp 호출됨."); return true;
            }
            @Override
            public boolean onScroll(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY){
                println("onScroll 호출됨." + distanceX + "," + distanceY); return true;
            }

            @Override
            public boolean onLongPress(@NonNull MotionEvent e){
                println("onLongPress 호출됨."); return true;
            }

            public boolean onScroll(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY){
                println("onFling 호출됨." + velocityX + "," + velocityY); return true;
            }

        });

        public void println (String Data) {
            if(textView.getLineCount() > 10) {
                textView.setText(null);
            }
            textView.append(data+"\n");
        }
    }