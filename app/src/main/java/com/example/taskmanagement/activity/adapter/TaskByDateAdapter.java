package com.example.taskmanagement.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmanagement.R;
import com.example.taskmanagement.activity.activity.TasksByDate;
import com.example.taskmanagement.activity.model.Task;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskByDateAdapter extends RecyclerView.Adapter<TaskByDateAdapter.MyViewHolder> {
    private TasksByDate context;
    private LayoutInflater inflater;
    private List<Task> taskList;

    public TaskByDateAdapter(TasksByDate context,List<Task> taskList) {
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskByDateAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_filter,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskByDateAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.day)
        TextView day;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.month)
        TextView month;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.status)
        TextView status;
        @BindView(R.id.options)
        ImageView options;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.itemView)
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
