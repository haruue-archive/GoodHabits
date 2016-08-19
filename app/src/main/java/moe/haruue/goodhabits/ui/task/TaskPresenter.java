package moe.haruue.goodhabits.ui.task;

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
    public void getTodayTasks() {

    }

    @Override
    public void setTaskFinish(int TaskId) {

    }
}
