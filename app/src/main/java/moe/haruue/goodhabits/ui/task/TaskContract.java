package moe.haruue.goodhabits.ui.task;

import java.util.ArrayList;

import moe.haruue.goodhabits.BasePresenter;
import moe.haruue.goodhabits.BaseView;
import moe.haruue.goodhabits.model.Task;

/**
 * Created by simonla on 2016/8/18.
 * Have a good day.
 */
public class TaskContract {
    interface View extends BaseView<Presenter> {

        void onGetTodayTasks(ArrayList<Task> tasks, boolean isSuccess);
        void onSetTaskFinished(boolean isSuccess);

    }

    interface Presenter extends BasePresenter {

        void getTodayTasks();
        void setTaskFinish(int TaskId);

    }
}
