package com.example.taskmanagement.activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.taskmanagement.R;
import com.example.taskmanagement.activity.adapter.TaskByDateAdapter;
import com.example.taskmanagement.activity.model.Task;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TasksByDate extends AppCompatActivity {
    @BindView(R.id.rcTaskFilter)
    RecyclerView rcTaskFilter;

    TaskByDateAdapter taskByDateAdapter;
    List<Task> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_by_date);
        if (getIntent() != null) {
            tasks = (List<Task>) getIntent().getSerializableExtra("task_list");
            System.out.println(tasks.size());
        }
        setUpAdapter();
    }

    public void setUpAdapter(){
        taskByDateAdapter= new TaskByDateAdapter(this,tasks);
        rcTaskFilter.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rcTaskFilter.setAdapter(taskByDateAdapter);
    }
}