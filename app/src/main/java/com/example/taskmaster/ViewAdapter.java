package com.example.taskmaster;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {

    List<Task> tasks;
    OnTaskClickListener listener;
    public static final String TAG = "Task Adapter";



    public interface OnTaskClickListener{
        void onItemClicked(int position);
        void deleteItem(int position);
    }

    public ViewAdapter(List<Task> tasks, OnTaskClickListener listener) {
        this.tasks = tasks;
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTask;
        private TextView bodyTask;
        private TextView stateTask;
        private Button deleteBtn;

        public ViewHolder(@NonNull View itemView, OnTaskClickListener listener) {
            super(itemView);
            titleTask = itemView.findViewById(R.id.titleTask);
            stateTask = itemView.findViewById(R.id.stateTask);
            bodyTask = itemView.findViewById(R.id.bodyTask);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { listener.onItemClicked(getAdapterPosition()); }
            });
            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.deleteItem(getAdapterPosition());
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent,false);
        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title  = tasks.get(position).getTitle();
        String body = tasks.get(position).getBody();
        String state = tasks.get(position).getState();


        holder.titleTask.setText(title);
        holder.bodyTask.setText(body);
        holder.stateTask.setText(state);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

}
