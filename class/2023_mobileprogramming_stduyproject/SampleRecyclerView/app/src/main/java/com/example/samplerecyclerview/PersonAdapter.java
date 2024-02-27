package com.example.samplerecyclerview;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    ArrayList<Person> items = new ArrayList<Person>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.person_item, viewGroup, false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Person item = items.get(position);
        viewHolder.setItem(item);
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    public void addItem(Person item) {
        items.add(item);
    }
    public void setItems(ArrayList<Person> items) {
        this.items = items;
    }
    public Person getItem(int position) {
        return items.get(position);
    }
    public void setItem(int position, Person item) {
        items.set(position, item);
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_phone;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_phone = itemView.findViewById(R.id.tv_phone);
        }
        public void setItem(Person item) {
            tv_name.setText(item.getName());
            tv_phone.setText(item.getMobile());
        }
    }
}
