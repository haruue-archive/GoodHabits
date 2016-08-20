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

        void closeLoadTasksProgressBar();

    }

    interface Presenter extends BasePresenter {
        ArrayList<Task> getTodayTasks();

        void refreshData();

        void setTaskFinish(int TaskId);
    }


}
