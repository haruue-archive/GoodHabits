package moe.haruue.goodhabits.ui.task;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import moe.haruue.goodhabits.R;
import moe.haruue.goodhabits.model.Task;

/**
 * Created by simonla on 2016/8/20.
 * Have a good day.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private ArrayList<Task> mTasks;

    public TaskAdapter(ArrayList<Task> tasks) {
        mTasks = tasks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mTasks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvTitle;
        private TextView mTvHint;
        private TextView mTvClick;
        private ImageView mIvTask;

        public ViewHolder(View itemView) {
            super(itemView);
            mIvTask = (ImageView) itemView.findViewById(R.id.iv_task_hint);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_task_title);
            mTvHint = (TextView) itemView.findViewById(R.id.tv_task_hint);
            mTvClick = (TextView) itemView.findViewById(R.id.tv_task_finish);
        }
    }
}
