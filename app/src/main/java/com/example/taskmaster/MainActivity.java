package com.example.taskmaster;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button allBtn = (Button) findViewById(R.id.allBtn);
        Button addBtn = (Button) findViewById(R.id.addPageButton);

        Button taskBtn1 = (Button) findViewById(R.id.taskDetails1);
        String taskText1 = taskBtn1.getText().toString();

        Button taskBtn2 = (Button) findViewById(R.id.taskDetails2);
        String taskText2 = taskBtn2.getText().toString();

        Button taskBtn3 = (Button) findViewById(R.id.taskDetails3);
        String taskText3 = taskBtn3.getText().toString();

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
        Intent intent = new Intent(getApplicationContext(), TaskDetailsActivity.class);
        taskBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("taskText",taskText1);
                startActivity(intent);
            }
        });

        taskBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("taskText", taskText2);
                startActivity(intent);
            }
        });

        taskBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("taskText", taskText3);
                startActivity(intent);
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
    }

    public void openActivity1(){
        Intent intent = new Intent(this, firstActivity.class);
        startActivity(intent);
    }
    public void openActivity2(){
        Intent intent =  new Intent(this, secondActivity.class);
        startActivity(intent);
    }

}