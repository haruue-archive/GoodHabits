package moe.haruue.goodhabits.ui.task;

import java.util.ArrayList;

import moe.haruue.goodhabits.model.Task;

/**
 * Created by simonla on 2016/8/18.
 * Have a good day.
 */
public class TaskPresenter implements TaskContract.Presenter {

    private TaskContract.View mView;

    public TaskPresenter(TaskContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public ArrayList<Task> getTodayTasks() {
        return null;
    }

    @Override
    public void refreshData() {

    }

    @Override
    public void setTaskFinish(int TaskId) {

    }
}
