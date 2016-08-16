package moe.haruue.goodhabits.network.callback;

import com.avos.avoscloud.AVException;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public interface LoginCallback {

    void onLoginSuccess();
    void onLoginFailure(AVException e, String message);

}
