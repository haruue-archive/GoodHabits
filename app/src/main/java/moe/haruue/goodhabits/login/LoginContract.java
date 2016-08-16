package moe.haruue.goodhabits.login;

import moe.haruue.goodhabits.BasePresenter;
import moe.haruue.goodhabits.BaseViewWithPresenter;

/**
 * Created by simonla on 2016/8/13.
 * Have a good day.
 */

interface LoginContract {
    interface View extends BaseViewWithPresenter<Presenter> {

    }

    interface Presenter extends BasePresenter {
        void loadCourse();
    }
}
