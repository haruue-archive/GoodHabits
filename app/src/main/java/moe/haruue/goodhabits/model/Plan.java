package moe.haruue.goodhabits.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 不要直接创建这个，请使用它的两个子类 {@link StepPlan} 和 {@link TaskPlan}
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class Plan<T> implements Serializable {

    protected Plan() {

    }

    // 用于显示的标题
    public String title = "";
    // 简略信息（“让你的生活更有规律”）
    public String hint = "";
    // 详细信息
    public String content = "";
    // plan 的唯一标识符 id ，用于在 Task 和 Step 中标志 Plan，不能含有 $
    public String planId = "";
    // 预留，以后可用的小图
    public String imageUrl = "";
    // 预留，以后的作者
    public String author = "";
    // 此 Plan 是否在进行
    public boolean isDoing = false;

    // 此 Plan 中拥有的 Step/Task 这类东西
    public List<T> array = new ArrayList<>(0);
    // 以下在创建 Plan 的时候才要填，Plan 的总开始时间和总结束时间
    public long timeRangeStart;
    public long timeRangeEnd;



    @Override
    public boolean equals(Object obj) {
        return obj instanceof Plan && planId.equals(((Plan) obj).planId);
    }

    @Override
    public int hashCode() {
        return ("PLAN_FC7AFC_" + planId).hashCode();
    }
}
