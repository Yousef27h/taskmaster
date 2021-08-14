package com.example.taskmaster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    List<Task> tasks ;
    private RecyclerView recyclerView;
    ViewAdapter viewAdapter;
    private TaskDatabase taskDatabase;
    private TaskDao taskDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        taskDatabase = Room.databaseBuilder(getApplicationContext(),TaskDatabase.class,"Tasks list").allowMainThreadQueries().build();
        taskDao = taskDatabase.taskDao();

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


    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        TextView textView = findViewById(R.id.textView);
        textView.setText(sharedPreferences.getString("username","Create username to customize")+" Tasks");

       tasks = AddTaskActivity.getTasks() ;
        tasks = taskDao.findAll();

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
                taskDao.deleteTask(tasks.get(position));
                tasks.remove(position);
                viewAdapter.notifyDataSetChanged();
            }

        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(viewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settingsTab:
                        Intent intent = new Intent(getApplicationContext(),SettingActivity.class);
                        startActivity(intent);
                        return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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