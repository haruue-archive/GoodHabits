package moe.haruue.goodhabits.ui.login;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.jude.utils.JUtils;

import java.util.List;

import moe.haruue.goodhabits.data.CurrentUser;
import moe.haruue.goodhabits.model.Task;
import moe.haruue.goodhabits.network.RequestManager;
import moe.haruue.goodhabits.network.callback.LoginCallback;
import moe.haruue.goodhabits.network.callback.RegisterCallback;
import rx.Subscriber;

/**
 * Created by simonla on 2016/8/13.
 * Have a good day.
 */

class LoginPresenter implements LoginContract.Presenter {

    public static final String TAG = "LoginPresenter";

    @NonNull
    private LoginContract.View mView;

    LoginPresenter(@NonNull LoginContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        if (!JUtils.isNetWorkAvilable()) {
            mView.noNetError();
        }
    }

    @Override
    public void login(@NonNull String userName, @NonNull String userPassword, @NonNull String stuNum) {
        mView.showProgress(50);
        Log.d(TAG, "login: " + userName);
        if ("".equals(userName)) {
            mView.userNameError("不能为空");
            mView.showProgress(0);
            return;
        }
        if (userPassword.length() < 7) {
            mView.passwordError("请输入7位以上密码");
            mView.showProgress(0);
            return;
        }
        if (!"".equals(stuNum)) {
            if (stuNum.length() != 10) {
                mView.stuNumError("请检查学号");
                mView.showProgress(0);
                return;
            }
        }

        RequestManager.getInstance().login(userName, userPassword, new LoginCallback() {
            @Override
            public void onLoginSuccess() {
                if (!"".equals(stuNum)) {
                    CurrentUser.getInstance().setIsCQUPT(true);
                    CurrentUser.getInstance().setStuNum(stuNum);
                    mView.showProgress(90);
                    loadSchoolCourse(stuNum);
                } else {
                    mView.showProgress(100);
                    mView.startActivity();
                }
            }

            @Override
            public void onLoginFailure(AVException e, String message) {
                if (e.getCode() == 211) {
                    mView.showProgress(0);
                    mView.showMakeSureDialog();
                } else {
                    mView.showProgress(0);
                    mView.passwordError(message);
                }
            }
        });
    }

    public void signUp(@NonNull String userName, @NonNull String userPassword, @Nullable String stuNum) {
        mView.showProgress(50);
        //TODO check sutNumber correct
        boolean isCQUPT = false;
        if (!"".equals(stuNum)) {
            isCQUPT = true;
        }
        RequestManager.getInstance().register(userName, userPassword, isCQUPT, stuNum, new RegisterCallback() {
            @Override
            public void onRegisterSuccess() {
                login(userName, userPassword, stuNum);
            }

            @Override
            public void onRegisterFailure(AVException e, String message) {
                mView.showProgress(-1);
                mView.passwordError(message);
            }
        });
    }

    private void loadSchoolCourse(String stuNum) {
        RequestManager.getInstance().reloadFullSchoolCourseAndStorageAsTask(new Subscriber<List<Task>>() {
            @Override
            public void onCompleted() {
                mView.showProgress(100);
                mView.startActivity();
            }

            @Override
            public void onError(Throwable e) {
                Log.e("LoginPresenter", "loadSchoolCourse", e);
            }

            @Override
            public void onNext(List<Task> tasks) {
                Log.d("CSE_SchoolCourse", tasks.toString());
            }
        }, stuNum, "0");
    }
}
