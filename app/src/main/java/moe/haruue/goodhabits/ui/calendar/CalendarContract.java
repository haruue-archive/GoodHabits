package moe.haruue.goodhabits.ui.calendar;

import moe.haruue.goodhabits.BasePresenter;
import moe.haruue.goodhabits.BaseView;

/**
 * Created by simonla on 2016/8/18.
 * Have a good day.
 */
public class CalendarContract {
    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
        // 总完成率 0..100 &
        void getFinishedPer(Callback callback);
    }

    interface Callback{
        void onFinish(int per);

        void onError(String error);
    }
}
