package com.example.android.todoapp.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.todoapp.Constants;
import com.example.android.todoapp.R;
import com.example.android.todoapp.db.Task;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private static final String TAG = TaskAdapter.class.getSimpleName();

    private List<Task> taskList;
    private ListItemClickListener listener;
    private Context context;

    public TaskAdapter(ListItemClickListener listener) {
        this.listener = listener;
        this.context = (Context) listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup recyclerView, int viewType) {
        boolean shouldAttachToParentImmediately = false;

        LayoutInflater inflater = LayoutInflater.from(recyclerView.getContext());
        View viewHierarchy = inflater.inflate(R.layout.item_task, recyclerView,
                shouldAttachToParentImmediately);

        return new TaskViewHolder(viewHierarchy);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bindData();
    }

    @Override
    public int getItemCount() {
        return taskList == null ? 0 : taskList.size();
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
        // How to Improve this one
        notifyDataSetChanged();
    }

    public Task getTaskAt(int position) {
        return taskList.get(position);
    }

    public void removeItem(int position) {
        taskList.remove(position);
        notifyItemRemoved(position);
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView description;
        private TextView date;
        private TextView priority;

        TaskViewHolder(View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.tv_task_description);
            date = itemView.findViewById(R.id.tv_task_date);
            priority = itemView.findViewById(R.id.tv_task_priority);
            itemView.setOnClickListener(this);
        }

        private void bindData() {
            Task task = taskList.get(getAdapterPosition());
            description.setText(task.getDescription());
            date.setText(task.getDate().toString());
            priority.setText(task.getPriority() + "");

            // Set background color of priority
            GradientDrawable priorityCircle = (GradientDrawable) priority.getBackground();
            priorityCircle.setColor(getPriorityColor(task.getPriority()));
        }

        private int getPriorityColor(int priority) {
            int priorityColor = 0;

            switch (priority) {
                case Constants.PRIORITY_HIGH:
                    priorityColor = ContextCompat.getColor(context, R.color.materialRed);
                    break;
                case Constants.PRIORITY_MEDIUM:
                    priorityColor = ContextCompat.getColor(context, R.color.materialOrange);
                    break;
                case Constants.PRIORITY_LOW:
                    priorityColor = ContextCompat.getColor(context, R.color.materialYellow);
                    break;
                default:
                    String message = "The color option is not 1-3";
                    Log.e(TAG, "getPriorityColor: " + message);
                    priorityColor = ContextCompat.getColor(context, R.color.materialWhite);
                    break;
            }

            return priorityColor;
        }

        @Override
        public void onClick(View view) {
            listener.onListItemClicked(getAdapterPosition());
        }
    }
}