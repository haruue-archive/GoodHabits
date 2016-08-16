package moe.haruue.goodhabits.network;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;
import com.avos.avoscloud.RequestPasswordResetCallback;
import com.avos.avoscloud.SignUpCallback;

import moe.haruue.goodhabits.model.CurrentUser;
import moe.haruue.goodhabits.network.callback.LoginCallback;
import moe.haruue.goodhabits.network.callback.RegisterCallback;
import moe.haruue.goodhabits.network.callback.ResetPasswordCallback;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public enum  RequestManager {

    INSTANCE;

    public static RequestManager getInstance() {
        return INSTANCE;
    }

    private RequestManager() {

    }

    public void putMessage() {

    }

    public void login(String username, String password, final LoginCallback callback) {
        AVUser.logInInBackground(username, password, new LogInCallback<AVUser>() {

            @Override
            public void done(AVUser avUser, AVException e) {
                if (e == null) {
                    callback.onLoginSuccess();
                } else {
                    // TODO: User friendly message
                    callback.onLoginFailure(e, e.getMessage());
                }
            }
        });
    }

    public void register(String username, String password, String email, final boolean isCQUPT, final String stuNum, final RegisterCallback callback) {
        final AVUser user = new AVUser();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    if (isCQUPT) {
                        CurrentUser.getInstance().setIsCQUPT(true);
                        CurrentUser.getInstance().setStuNum(stuNum);
                    } else {
                        CurrentUser.getInstance().setIsCQUPT(false);
                    }
                    callback.onRegisterSuccess();
                } else {
                    // TODO: User friendly message
                    callback.onRegisterFailure(e, e.getMessage());
                }
            }
        });

    }

    public void resetPassword(String email, final ResetPasswordCallback callback) {
        AVUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback() {
            @Override
            public void done(AVException e) {
                if (e == null) {
                    callback.onResetPasswordRequestSuccess();
                } else {
                    // TODO: User friendly message
                    callback.onResetPasswordRequestFailure(e, e.getMessage());
                }
            }
        });

    }
}
