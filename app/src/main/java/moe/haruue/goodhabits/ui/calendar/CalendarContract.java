package moe.haruue.goodhabits.ui.calendar;

import java.util.HashMap;

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

        void getFinishOfMonth(FinishDayCallback callback);

        void getSkipPer(Callback callback);

        //先给个假数据好了= =。
        void getSkipMoreThanOthers(Callback callback);
    }

    interface Callback{
        void onFinish(int per);

        void onError(String error);
    }

    interface FinishDayCallback {
        //int k:day of this month, boole v:isFinish
        void onFinish(HashMap<Integer, Boolean> hashMap);

        void onError(String error);
    }

}
