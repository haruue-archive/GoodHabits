package moe.haruue.goodhabits.ui.calendar;

/**
 * Created by simonla on 2016/8/18.
 * Have a good day.
 */
public class CalendarPresneter implements CalendarContract.Presenter {

    private CalendarContract.View mView;

    public CalendarPresneter(CalendarContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }
}
