package moe.haruue.goodhabits.network.callback;

import com.avos.avoscloud.AVException;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public interface RegisterCallback {

    void onRegisterSuccess();
    void onRegisterFailure(AVException e, String message);

}
