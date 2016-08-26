package moe.haruue.goodhabits.ui.settings;

import android.app.ProgressDialog;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import moe.haruue.goodhabits.config.Const;
import moe.haruue.goodhabits.data.CurrentUser;
import moe.haruue.goodhabits.data.database.task.func.DeleteTasksByTaskTypeOperateFunc;
import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.network.RequestManager;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class SettingsPresenter implements SettingsContract.Presenter {

    SettingsContract.View view;

    SettingsPresenter(SettingsContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public boolean isStuNumModified(String stuNum) {
        String origStuNum = CurrentUser.getInstance().getStuNum();
        if (origStuNum == null) {
            origStuNum = "";
        }
        return !stuNum.equals(origStuNum);
    }

    @Override
    public void applyStuNumModify(String stuNum) {
        ProgressDialog dialog = view.onReLoadingSchoolCourse();
        Subscriber<List<Task>> subscriber = new Subscriber<List<Task>>() {
            @Override
            public void onCompleted() {
                EventBus.getDefault().post(new CourseReloadedEvent());
                view.onReLoadSchoolCourseSuccess(dialog);
            }

            @Override
            public void onError(Throwable e) {
                Log.e("SettingsPresenter", "applyStuNumModify", e);
                view.onReLoadSchoolCourseFailure(dialog, e.getLocalizedMessage(), e);
            }

            @Override
            public void onNext(List<Task> tasks) {

            }
        };
        if (stuNum.equals("")) {
            CurrentUser.getInstance().setStuNum("");
            CurrentUser.getInstance().setIsCQUPT(false);
            ArrayList<Task> typeTasks = new ArrayList<>(0);
            typeTasks.add(Task.newEmptyTaskWithType(Const.TASK_TYPE_SCHOOL_COURSE));
            Observable.just(typeTasks)
                    .map(new DeleteTasksByTaskTypeOperateFunc())
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(subscriber);
        } else {
            CurrentUser.getInstance().setIsCQUPT(true);
            CurrentUser.getInstance().setStuNum(stuNum);
            RequestManager.getInstance().reloadFullSchoolCourseAndStorageAsTask(subscriber, stuNum, "0");
        }
    }

}
