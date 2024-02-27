package com.example.csvtest2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CsvAdapter extends RecyclerView.Adapter<CsvAdapter.ViewHolder> {

    private List<List<String>> data;

    public CsvAdapter(List<List<String>> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Calculate average temperature for all rows
        double sum = 0.0;
        int totalCount = 0;

        for (List<String> rowData : data) {
            for (String value : rowData) {
                try {
                    double temperature = Double.parseDouble(value);
                    sum += temperature;
                    totalCount++;
                } catch (NumberFormatException e) {
                    // Handle the case where the value is not a valid double
                }
            }
        }

        // Calculate overall average and set it to the TextView
        double overallAverage = (totalCount > 0) ? sum / totalCount : 0.0;
        holder.textView.setText("Overall Average Temperature: " + overallAverage);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }

}


