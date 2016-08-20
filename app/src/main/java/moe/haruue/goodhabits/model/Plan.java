package moe.haruue.goodhabits.model;

import java.util.ArrayList;

/**
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class Plan {

    // 用于显示的标题
    public String title = "";
    // 简略信息（“让你的生活更有规律”）
    public String hint = "";
    // 详细信息
    public String content = "";
    // plan 的唯一标识符 id ，用于在 Task 和 Step 中标志 Plan，不能含有 $
    public String planId = "";
    // 此 Plan 中拥有的 Steps
    public ArrayList<Step> steps;
    // 预留，以后可用的小图
    public String imageUrl = "";
    // 预留，以后的作者
    public String author = "";
    // 此 Plan 是否在进行
    public boolean isDoing;

}
