package moe.haruue.goodhabits.ui.taskdetail;

import moe.haruue.goodhabits.BasePresenter;
import moe.haruue.goodhabits.BaseView;
import moe.haruue.goodhabits.model.Plan;

/**
 * Created by simonla on 2016/8/26.
 * Have a good day.
 */
public class TaskDetailContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void saveIsRead(int hashCode);
    }
}
