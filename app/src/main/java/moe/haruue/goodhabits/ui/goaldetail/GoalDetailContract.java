package moe.haruue.goodhabits.ui.goaldetail;

import java.util.List;

import moe.haruue.goodhabits.BasePresenter;
import moe.haruue.goodhabits.BaseView;
import moe.haruue.goodhabits.model.BaseStep;

/**
 * Created by simonla on 2016/8/25.
 * Have a good day.
 */
public class GoalDetailContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void getSteps(String planId, Callback callback);

        void saveThePlan(String planId);

    }

    interface Callback {
        void onFinish(List<BaseStep> mSteps);

        void onError(String error);
    }

}
