package com.example.csvtest2;

import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // AsyncTask를 사용하여 백그라운드에서 CSV 파일을 읽고 RecyclerView에 표시
        new ReadCSVTask().execute();
    }

    private class ReadCSVTask extends AsyncTask<Void, Void, List<List<String>>> {

        @Override
        protected List<List<String>> doInBackground(Void... voids) {
            // CSV 파일을 읽어서 'Jongno-gu'만을 리스트로 반환
            try (InputStream inputStream = getAssets().open("train.csv")) {
                return CsvParser.parseCsv(inputStream, "Jung-gu");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<List<String>> csvContent) {
            // 백그라운드 작업이 완료되면 RecyclerView에 어댑터 설정
            if (csvContent != null) {
                CsvAdapter csvAdapter = new CsvAdapter(csvContent);
                recyclerView.setAdapter(csvAdapter);
            }
        }
    }
}