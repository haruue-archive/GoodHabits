package moe.haruue.goodhabits.ui.taskdetail;

/**
 * Created by simonla on 2016/8/26.
 * Have a good day.
 */
public class TaskDetailPresenter implements TaskDetailContract.Presenter {

    private TaskDetailContract.Presenter mPresenter;
    private TaskDetailContract.View mView;

    public TaskDetailPresenter(TaskDetailContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void saveIsRead(int hashCode) {

    }

}
