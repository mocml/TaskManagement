package com.example.taskmanagement.activity.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taskmanagement.R;
import com.example.taskmanagement.activity.model.Task;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskDetail extends AppCompatActivity {

    String title, date, time, description, day_of_week;
    Boolean status;
    @BindView(R.id.itemTitle)
    TextView itemTitle;
    @BindView(R.id.itemDateTime)
    TextView itemDateTime;
    @BindView(R.id.itemDescription)
    TextView itemDescription;
    @BindView(R.id.itemStatus)
    TextView itemStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);
        ButterKnife.bind(this);
        handleData();

    }

    private void handleData() {
        if (getIntent() != null) {
            Intent intent = getIntent();
            title = intent.getStringExtra("title");
            date = intent.getStringExtra("date");
            time = intent.getStringExtra("time");
            description = intent.getStringExtra("description");
            status = intent.getBooleanExtra("status", false);
            day_of_week = intent.getStringExtra("day_of_week");
            itemTitle.setText(title);
            itemDescription.setText(description);
            itemStatus.setText(status ? "Complete" : "Upcoming");
            itemDateTime.setText("Deadline : " + day_of_week + ", " + time + " " + date);


//            Toast.makeText(this, title + " " + date + " " + time, Toast.LENGTH_SHORT).show();
        }
    }


}