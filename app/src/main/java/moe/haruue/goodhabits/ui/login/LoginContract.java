package moe.haruue.goodhabits.ui.login;


import android.support.annotation.NonNull;

import moe.haruue.goodhabits.BasePresenter;
import moe.haruue.goodhabits.BaseView;

/**
 * Created by simonla on 2016/8/13.
 * Have a good day.
 */

interface LoginContract {
    interface View extends BaseView<Presenter> {
        void noNetError();

        void userNameError(String error);

        void passwordError(String error);

        void stuNumError(String error);

        void showMakeSureDialog();

        void showProgress(int per);

        void startActivity();
    }

    interface Presenter extends BasePresenter {
        void login(@NonNull String userName, @NonNull String userPassword, @NonNull String stuNum);
        void signUp(@NonNull String userName, @NonNull String userPassword, @NonNull String stuNum);
    }
}
