package com.example.intentresult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_FRIEND = 101;
    public static final String KEY_SIMPLE_MSG = "msg";

    TextView textView;
    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView2);
        editText = findViewById(R.id.editTextText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FriendActivity.class);
                String msg = editText.getText().toString();
                intent.putExtra(KEY_SIMPLE_MSG, msg);
                startActivityForResult(intent, REQUEST_CODE_FRIEND);
            }
        });
    }

        protected void onActivityResult(int requestCode,int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == REQUEST_CODE_FRIEND) {
                if(resultCode == RESULT_OK) {
                    String msg = data.getStringExtra(KEY_SIMPLE_MSG);
                    textView.setText(msg);
                }
            }
        }
}