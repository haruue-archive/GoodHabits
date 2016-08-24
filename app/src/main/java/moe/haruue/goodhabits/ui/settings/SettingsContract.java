package moe.haruue.goodhabits.ui.settings;

import android.app.ProgressDialog;

import moe.haruue.goodhabits.BasePresenter;
import moe.haruue.goodhabits.BaseView;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public interface SettingsContract {
    interface View extends BaseView<Presenter> {

        ProgressDialog onReLoadingSchoolCourse();
        void onReLoadSchoolCourseSuccess(ProgressDialog dialog);
        void onReLoadSchoolCourseFailure(ProgressDialog dialog, String message, Throwable t);

    }

    interface Presenter extends BasePresenter {

        boolean isStuNumModified(String stuNum);
        void applyStuNumModify(String stuNum);

    }
}
