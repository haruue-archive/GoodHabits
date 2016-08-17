package moe.haruue.goodhabits.model;

import com.avos.avoscloud.AVUser;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public enum CurrentUser {

    INSTANCE;

    //TODO 在model处理不当
    public static CurrentUser getInstance() {
        return INSTANCE;
    }

    public boolean isLogin() {
        return AVUser.getCurrentUser() != null;
    }

    public String getUserName() {
        return AVUser.getCurrentUser().getUsername();
    }

    public String getEmail() {
        return AVUser.getCurrentUser().getEmail();
    }

    public void setIsCQUPT(boolean isCQUPT) {
        AVUser.getCurrentUser().put("school", isCQUPT ? "CQUPT" : "");
        AVUser.getCurrentUser().saveInBackground();
    }

    public boolean getIsCQUPT() {
        return AVUser.getCurrentUser().get("school").equals("CQUPT");
    }

    public void setStuNum(String stuNum) {
        AVUser.getCurrentUser().put("stuNum", stuNum);
        AVUser.getCurrentUser().saveInBackground();
    }

    public String getStuNum() {
        return (String) AVUser.getCurrentUser().get("stuNum");
    }

}
