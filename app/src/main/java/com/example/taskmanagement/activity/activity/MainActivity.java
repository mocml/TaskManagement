package com.example.taskmanagement.activity.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.taskmanagement.R;
import com.example.taskmanagement.activity.adapter.TaskAdapter;
import com.example.taskmanagement.activity.broadcast.AlarmBroadcastReceiver;
import com.example.taskmanagement.activity.dao.DBClient;
import com.example.taskmanagement.activity.model.Task;
import com.example.taskmanagement.activity.sheet.CalendarBSheet;
import com.example.taskmanagement.activity.sheet.CreateTaskBSheet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CreateTaskBSheet.setRefreshListener {
    @BindView(R.id.taskRecycler)
    RecyclerView taskRecycler;
    @BindView(R.id.addTask)
    TextView addTask;
    @BindView(R.id.noDataImage)
    ImageView noDataImage;
    @BindView(R.id.calendar)
    ImageView calendar;
    TaskAdapter taskAdapter;
    List<Task> tasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpAdapter();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ComponentName receiver = new ComponentName(this, AlarmBroadcastReceiver.class);
        PackageManager pm = getPackageManager();
        pm.setComponentEnabledSetting(receiver, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        Glide.with(getApplicationContext()).load(R.drawable.first_note).into(noDataImage);

        addTask.setOnClickListener(view -> {
            CreateTaskBSheet createTaskBSheet = new CreateTaskBSheet();
            createTaskBSheet.setTaskId(0, false, this, MainActivity.this);
            createTaskBSheet.show(getSupportFragmentManager(), createTaskBSheet.getTag());
        });
        getSavedTasks();

        calendar.setOnClickListener(view -> {
            CalendarBSheet calendarBSheet = new CalendarBSheet();
            calendarBSheet.show(getSupportFragmentManager(), calendarBSheet.getTag());
        });
    }

    public void getSavedTasks() {
        class GetSavedTasks extends AsyncTask<Void, Void, List<Task>> {

            @Override
            protected List<Task> doInBackground(Void... voids) {
                tasks = DBClient.getInstance(getApplicationContext())
                        .getAppDatabase()
                        .dataBaseAction()
                        .getAllTasksList();
                return tasks;
            }

            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
                noDataImage.setVisibility(tasks.isEmpty() ? View.VISIBLE : View.GONE);
                setUpAdapter();
            }
        }
        GetSavedTasks savedTasks = new GetSavedTasks();
        savedTasks.execute();
    }

    public void setUpAdapter() {
        taskAdapter = new TaskAdapter(this, tasks, this);
        taskRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        taskRecycler.setAdapter(taskAdapter);
    }

    @Override
    public void refresh() {
        getSavedTasks();
    }
}