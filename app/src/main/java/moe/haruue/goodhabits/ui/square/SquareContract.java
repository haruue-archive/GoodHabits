package moe.haruue.goodhabits.ui.square;

import java.util.List;

import moe.haruue.goodhabits.BasePresenter;
import moe.haruue.goodhabits.BaseView;
import moe.haruue.goodhabits.model.Plan;

/**
 * Created by simonla on 2016/8/18.
 * Have a good day.
 */
public class SquareContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void getPlans(Callback callback);

        int getNowPlanId();

    }

    interface Callback {
        void onSuccess(List<Plan> plans);

        void onError(String error);
    }

}
