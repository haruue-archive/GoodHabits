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

        /**
         * 给新的过来就行了
         * @param isSomeNews 是否有新内容
         * @param newTasks 没有就 null
         */
        void onRefresh(boolean isSomeNews, ArrayList<Task> newTasks);

        void onGetTodayTasks(ArrayList<Task> tasks, boolean isSuccess);

        void onSetTaskFinished(boolean isSuccess);

    }

    interface Presenter extends BasePresenter {
        void getTodayTasks();

        void setTaskFinish(int TaskId);

        void refreshTasks();

        void saveNote(int id);

    }
}
