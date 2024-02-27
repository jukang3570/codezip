package com.example.sampledealyed;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    Button button;
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        handler = new Handler();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    public void request() {
        String title = "원격 요청";
        String msg = "데이터를 요청하시겠습니까?";
        String buttonYes = " 예";
        String buttonNo = "아니오";
        AlertDialog dialog = makeRequestDialog(title, msg, buttonYes, buttonNo);
        dialog.show();
        textView.setText("대화상자 표시중...");
    }

    private AlertDialog makeRequestDialog(String title, String msg, String buttonYes, String buttonNo) {
        AlertDialog.Builder requestDialog = new AlertDialog.Builder(this);

        requestDialog.setTitle(title);
        requestDialog.setMessage(msg);
        requestDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                textView.setText("5초 후에 결과 표시됨. ");
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("요청 완료됨" );
                    }
                }, delayMillis: 5000);
            }
        });

        requestDialog.setNegativeButton(buttonNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return requestDialog.create();
    }
}