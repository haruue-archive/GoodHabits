package moe.haruue.goodhabits.ui.GoalDetail;

/**
 * Created by simonla on 2016/8/25.
 * Have a good day.
 */
public class GoalDetailPresenter implements GoalDetailContract.Presenter {

    private GoalDetailContract.View mView;

    public GoalDetailPresenter(GoalDetailContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
