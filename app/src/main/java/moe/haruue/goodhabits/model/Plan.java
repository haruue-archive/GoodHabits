package moe.haruue.goodhabits.model;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 不要直接创建这个，请使用它的两个子类 {@link StepPlan} 和 {@link TaskPlan}
 * @author Haruue Icymoon haruue@caoyue.com.cn
 */

public class Plan<T extends BaseStep> implements Serializable {

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

    /**
     * 反 Json 序列化的时候用来区分是那种 Plan ，如 TaskPlan 还是 StepPlan。
     * 在子类的构造方法里初始化
     */
    protected String planType;


    @Override
    public boolean equals(Object obj) {
        return obj instanceof Plan && planId.equals(((Plan) obj).planId);
    }

    @Override
    public int hashCode() {
        return ("PLAN_FC7AFC_" + planId).hashCode();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static <PT extends Plan> Class<PT> getPlanTypeFromJson(String json) {
        Gson gson = new Gson();
        Plan plan = gson.fromJson(json, Plan.class);
        if (plan.planType.equals(StepPlan.PLAN_TYPE_STEP)) {
            return (Class<PT>) StepPlan.class;
        } else if (plan.planType.equals(TaskPlan.PLAN_TYPE_TASK)){
            return (Class<PT>) TaskPlan.class;
        } else {
            throw new IllegalArgumentException("Unknown Plan Type: " + plan.getClass().getSimpleName() + ", it must be one of StepPlan and TaskPlan, or insert new plan type to Plan#getPlanTypeFromJson");
        }
    }

    public static Plan newEmptyPlanWithPlanId(String planId) {
        Plan plan = new Plan();
        plan.planId = planId;
        return plan;
    }

}
