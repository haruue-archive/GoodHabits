package moe.haruue.goodhabits.network.callback;

import com.avos.avoscloud.AVException;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public interface ResetPasswordCallback {

    void onResetPasswordRequestSuccess();
    void onResetPasswordRequestFailure(AVException e, String message);

}
