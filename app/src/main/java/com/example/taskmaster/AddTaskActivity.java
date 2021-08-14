package com.example.taskmaster;


import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class AddTaskActivity extends AppCompatActivity {

    private Spinner spinner;
    private final String[] states={"new","assigned","in progress","complete"};
    static List<Task> tasks = new ArrayList<>();
    private TaskDatabase taskDatabase;
    private TaskDao taskDao;
    public static List<Task> getTasks() {
        return tasks;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        // set drop down list to choose a state
        spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,
                states);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        // initialize database
        taskDatabase = Room.databaseBuilder(getApplicationContext(),TaskDatabase.class,"Tasks list").allowMainThreadQueries().build();
        taskDao = taskDatabase.taskDao();

        TextView tasksCount = findViewById(R.id.tasksCount);
        tasksCount.setText("Total tasks: "+taskDao.findAll().size());

        // add event listener to submit button that creates new Task object and shows a toast
        findViewById(R.id.addTaskButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText taskTitle = findViewById(R.id.TaskTitleInput);
                EditText taskBody = findViewById(R.id.TaskBodyInput);

                Task task = new Task(taskTitle.getText().toString(),taskBody.getText().toString(),spinner.getSelectedItem().toString());
//                tasks.add(task);

                taskDao.insertTask(task);

                TextView tasksCount = findViewById(R.id.tasksCount);
                tasksCount.setText("Total tasks: "+taskDao.findAll().size());

                Toast toast = Toast.makeText(getApplicationContext(),"Submitted!", Toast.LENGTH_SHORT);
                toast.show();
            }

        });
    }
}