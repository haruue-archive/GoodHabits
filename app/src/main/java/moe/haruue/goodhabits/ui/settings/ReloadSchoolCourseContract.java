package moe.haruue.goodhabits.ui.settings;

import android.app.ProgressDialog;

import moe.haruue.goodhabits.BasePresenter;
import moe.haruue.goodhabits.BaseView;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

interface ReloadSchoolCourseContract {
    interface View extends BaseView<Presenter> {
        ProgressDialog onReloadingSchoolCourse();
        void onReloadSchoolCourseSuccess(ProgressDialog dialog);
        void onReloadSchoolCourseFailure(ProgressDialog dialog, String message, Throwable t);
    }

    interface Presenter extends BasePresenter {
        void requestReloadSchoolCourse();
    }
}
