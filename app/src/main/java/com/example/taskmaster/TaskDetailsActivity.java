package com.example.taskmaster;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        Intent intent = getIntent();

        TextView textView = (TextView) findViewById(R.id.textView6);
        textView.setText(intent.getExtras().getString("task_name"));
    }
}