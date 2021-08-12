package com.example.taskmaster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Task> tasks = AddTaskActivity.getTasks() ;
    private RecyclerView recyclerView;
    ViewAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Task newTask = new Task("Task1","Just a task","new");
        tasks.add(newTask);



        Button allBtn = (Button) findViewById(R.id.allBtn);
        Button addBtn = (Button) findViewById(R.id.addPageButton);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity1();
            }
        });

        allBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });


        findViewById(R.id.settingsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        TextView textView = findViewById(R.id.textView);
        textView.setText(sharedPreferences.getString("username","Create username to customize")+" Tasks");

       tasks = AddTaskActivity.getTasks() ;
        recyclerView = findViewById(R.id.recyclerView);
        setAdapter();
    }

    public void setAdapter(){
        viewAdapter = new ViewAdapter(tasks, new ViewAdapter.OnTaskClickListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(getBaseContext(), TaskDetailsActivity.class);
                intent.putExtra("task_name",tasks.get(position).getTitle());
                startActivity(intent);
            }

            @Override
            public void deleteItem(int position) {
                tasks.remove(position);
                viewAdapter.notifyDataSetChanged();
            }

        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);
    }

    public void openActivity1(){
        Intent intent = new Intent(this, AddTaskActivity.class);
        startActivity(intent);
    }

    public void openActivity2(){
        Intent intent =  new Intent(this, secondActivity.class);
        startActivity(intent);
    }

}