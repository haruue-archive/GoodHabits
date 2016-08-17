package moe.haruue.goodhabits.login;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.SignUpCallback;
import com.jude.utils.JUtils;

import java.util.Objects;

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

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void login(@NonNull String userName, @NonNull String userPassword, @NonNull String stuNum) {
        mView.showProgress(50);
        Log.d(TAG, "login: " + userName);
        if (Objects.equals(userName, "")) {
            mView.userNameError("不能为空");
            mView.showProgress(0);
            return;
        }
        if (userPassword.length() < 7) {
            mView.passwordError("请输入7位以上密码");
            mView.showProgress(0);
            return;
        }
        if (!Objects.equals(stuNum, "")) {
            if (stuNum.length() != 10) {
                mView.stuNumError("请检查学号");
                mView.showProgress(0);
                return;
            }
        }
        AVUser.logInInBackground(userName, userPassword, new LogInCallback<AVUser>() {
            @Override
            public void done(AVUser avUser, AVException e) {
                if (e == null) {
                    Log.d(TAG, "done: ");
                    mView.showProgress(100);
                    mView.startActivity();
                    return;
                }
                if (e.getCode() == 211) {
                    mView.showProgress(0);
                    mView.showMakeSureDialog();
                } else {
                    mView.showProgress(0);
                    mView.passwordError(e.getMessage());
                }
            }
        });
    }

    public void signUp(@NonNull String userName, @NonNull String userPassword, @Nullable String stuNum) {
        mView.showProgress(50);
        //TODO check sutNumber correct
        AVUser user = new AVUser();
        user.setUsername(userName);
        user.setPassword(userPassword);
        user.put("stuNum", stuNum);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    Log.d(TAG, "done: ");
                    mView.showProgress(100);
                    mView.startActivity();
                } else {
                    mView.showProgress(-1);
                    mView.passwordError(e.getMessage());
                }
            }
        });
    }
}
