package com.example.intentresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FriendActivity extends AppCompatActivity {
    public static final String KEY_SIMPLE_MSG = "msg";

    TextView textView;
    EditText editText;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        textView = findViewById(R.id.textView5);
        editText = findViewById(R.id.editTextText2);
        button = findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent();
                String msg = editText.getText().toString();
                intent.putExtra(KEY_SIMPLE_MSG, msg);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        Intent intent = getIntent();
        String msg = intent.getStringExtra(KEY_SIMPLE_MSG);
        textView.setText(msg);
    }
}