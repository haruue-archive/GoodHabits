package moe.haruue.goodhabits.ui.settings;

import android.app.ProgressDialog;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import moe.haruue.goodhabits.data.CurrentUser;
import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.network.RequestManager;
import rx.Subscriber;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

class ReloadSchoolCoursePresenter implements ReloadSchoolCourseContract.Presenter {

    ReloadSchoolCourseContract.View view;

    ReloadSchoolCoursePresenter(ReloadSchoolCourseContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void requestReloadSchoolCourse() {
        ProgressDialog dialog = view.onReloadingSchoolCourse();
        RequestManager.getInstance().reloadFullSchoolCourseAndStorageAsTask(new Subscriber<List<Task>>() {
            @Override
            public void onCompleted() {
                EventBus.getDefault().post(new CourseReloadedEvent());
                view.onReloadSchoolCourseSuccess(dialog);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("ReloadSchoolCourseP...", "requestReloadSchoolCourse", e);
                view.onReloadSchoolCourseFailure(dialog, e.getLocalizedMessage(), e);
            }

            @Override
            public void onNext(List<Task> tasks) {

            }
        }, CurrentUser.getInstance().getStuNum(), "0");
    }
}
