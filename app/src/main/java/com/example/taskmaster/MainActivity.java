package com.example.taskmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public void openActivity1(){
        Intent intent = new Intent(this, firstActivity.class);
        startActivity(intent);
    }
    public void openActivity2(){
        Intent intent =  new Intent(this, secondActivity.class);
        startActivity(intent);
    }



}