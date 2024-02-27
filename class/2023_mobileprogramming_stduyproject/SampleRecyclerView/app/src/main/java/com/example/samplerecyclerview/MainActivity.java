package com.example.samplerecyclerview;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.rv_contacts);
        // LinearLayoutManager layoutManager = new LinearLayoutManager
                // (this, LinearLayoutManager.VERTICAL, false);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        PersonAdapter adapter = new PersonAdapter();
        adapter.addItem(new Person("사장님", "010-1004-1004"));
        adapter.addItem(new Person("부장님", "010-2004-2004"));
        adapter.addItem(new Person("과장님", "010-3004-3004"));
        recyclerView.setAdapter(adapter);
    }
}