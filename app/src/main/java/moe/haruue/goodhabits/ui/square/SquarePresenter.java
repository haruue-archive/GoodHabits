package moe.haruue.goodhabits.ui.square;

import java.util.ArrayList;

import moe.haruue.goodhabits.model.Plan;

/**
 * Created by simonla on 2016/8/18.
 * Have a good day.
 */
public class SquarePresenter implements SquareContract.Presenter {

    private SquareContract.View mView;

    public SquarePresenter(SquareContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

/*    @Override
    public void getPlans(SquareContract.Callback callback) {
        ArrayList<Plan> arrayList = new ArrayList<>();
        Plan plan = new Plan();
        plan.title = "感觉更有活力";
        arrayList.add(plan);
        Plan plan1 = new Plan();
        plan1.title = "瘦成一道闪电";
        arrayList.add(plan1);
        Plan plan2 = new Plan();
        plan2.title = "改善睡眠质量";
        arrayList.add(plan2);
        Plan plan3 = new Plan();
        plan3.title = "提高专注力";
        arrayList.add(plan3);
        callback.onSuccess(arrayList);
    }*/
}
