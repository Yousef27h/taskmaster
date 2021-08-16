package com.example.taskmaster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDetailsActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        Intent intent = getIntent();

        TextView textTitle = (TextView) findViewById(R.id.textTitle);
        TextView textState = (TextView) findViewById(R.id.textState);
        TextView textDescription = (TextView) findViewById(R.id.textDescription);

        textTitle.setText(intent.getExtras().getString("task_name"));
        textState.setText("State: " + intent.getExtras().getString("task_state"));
        textDescription.setText("Description: " + intent.getExtras().getString("task_description"));
    }
}