package moe.haruue.goodhabits.model;

/**
 * Created by simonla on 2016/8/13.
 * Have a good day.
 */

public class Step {

    // 标题，将会直接传送给 Task
    public String title = "";
    // 这一步的内容，将会直接传送给 Task
    public String content = "";
    // 隶属的 Plan，将会是 Task 的 plan 属性的一部分
    public String planId = "";
    // 预留，图片 Url，将会直接传送给 Task
    public String imageUrl = "";
    // 第几步，将会是 Task 的 plan 属性的一部分
    public int index;
    // 完成这一步需要的最少时间，以秒计
    public long minTime;
    // 完成这一步需要的最多时间，以秒计
    public long maxTime;
    // 该活动不能早于这个时间（一天中的秒数），设置成 0 不考虑
    public long noEarlyTime;
    // 该活动不能晚于这个时间（一天中的秒数），设置成 86400 不考虑
    public long noLaterTime;
    // 该活动必须是晴天
    public boolean requireSunnyDay;

}
