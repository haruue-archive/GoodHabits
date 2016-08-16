package moe.haruue.goodhabits.login;

import android.support.annotation.NonNull;

/**
 * Created by simonla on 2016/8/13.
 * Have a good day.
 */

class LoginPresenter implements LoginContract.Presenter {

    @NonNull
    private LoginContract.View mView;

    LoginPresenter(@NonNull LoginContract.View view) {
        mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void loadCourse() {

    }
}
